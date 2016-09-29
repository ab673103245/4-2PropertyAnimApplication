package qianfeng.a4_2propertyanimapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class MyImageView {

    // 聚合关系
    private ImageView iv;



    public MyImageView(ImageView iv) {
        this.iv = iv;
    }

    public int getWidth() { // 这个get方法也重写
        int width = iv.getLayoutParams().width;
        return width;

    }

    public void setWidth(int width) {
                         // getLayoutParams():得到该控件的布局参数，这个转折很重要，
                                            // 这是从xml中获取控件的基本信息的最好的方法
//        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
//        layoutParams.width = width; // 这个width是外界传进来的
//        iv.requestLayout(); // 重新测量高度并显示

        // 上面的3行代码，实际上两行代码就够了。
          iv.getLayoutParams().width = width; // 设置iv在布局参数中的值，使其等于你传进来的那个值。
          iv.requestLayout(); // 重新测量高度并显示
        //set方法里，赋值完之后，记得要重新测量高度
    }


    // 往SD卡的私有目录的Cache中写入Bitmap图片
    private void saveBitmap(Bitmap bitmap, Context context, String filename)
    {
        FileOutputStream fos = null;
        File externalCacheDir = context.getExternalCacheDir();
        File file = new File(externalCacheDir, filename);

        try {
            fos = new FileOutputStream(file);
            if(filename.endsWith(".png")||filename.endsWith(".PNG"))
            {
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            }else if(filename.endsWith(".jpg")||filename.endsWith(".JPG"))
            {
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
