package qianfeng.a4_2propertyanimapplication;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = ((ImageView) findViewById(R.id.iv));
    }

    public void alpha(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", 1, 0.5f, 1, 0);
        alpha.setDuration(3000);
        alpha.start();

    }

    public void scale(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv, "scaleX", 0, 1, 0, 2);
        scaleX.setDuration(3000);
        scaleX.start(); // 开启动画效果
    }

    public void translate(View view) {
//        ObjectAnimator.ofFloat()
    }

    public void rotate(View view) {
    }

    public void width(View view) { // 点击。宽度改变，但是在View里，只有getWidth，没有setWidth。就不能直接使用Property

        MyImageView myImageView = new MyImageView(iv);
                                                                // 这个可变参数代表你可以在这个int... 如果参数很多，那就是一个动画效果，0-->300--->0--->100,
                                                                // 最后控件会停留在最后一个设置属性的值上，即100，最后的width就是100
        ObjectAnimator width = ObjectAnimator.ofInt(myImageView, "width", 0, 300,0,100);// 这个属性动画，会自动调用属性里面的get、set方法，前提是，两个方法都缺一不可才可以调用属性动画成功。
        width.setDuration(3000);
        width.start();

    }

    public void width2(View view) {  // 采用另一种方法解决

                                    // 在真机上测试 只能显示 0-300这一段动画，不能有两段动画！！真机测试结果
        ValueAnimator va = ValueAnimator.ofInt(0, 300, 800); // 这里有两段动画，会平分掉动画的3秒，即各自1.5秒    0-300：1.5秒， 300-400：1.5秒

        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                Object animatedValue = animation.getAnimatedValue();


                if(Integer.parseInt(animatedValue.toString())==300)
                {
                    Log.d("google-my:", "onAnimationUpdate: animatedFraction == 0.5?--->  " + animatedFraction);
                }

                iv.getLayoutParams().width = (int)animatedValue;

                iv.requestLayout(); // 重新测量宽度



            }
        });

        va.setDuration(3000);

        va.start();


    }

    public void property_animation(View view) {

        // 属性动画的代码实现方式：
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX",0,300,100);
        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation", 0, 359, 270, 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(iv, translationX, rotation);
        objectAnimator.start();


    }




    public void property_animation2(View view) {    // xml属性动画的实现方式

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.set);

        animator.setTarget(iv);

        animator.start();

    }

    public void property_animation_single(View view) {  // 属性动画的单个实现，xml

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.translation);

        animator.setTarget(iv);

        animator.start();

    }

}
