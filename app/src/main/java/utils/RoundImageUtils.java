package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * 把imageview控件转为圆形imageview
 */

public class RoundImageUtils {
    /**
     * 转换为圆角图片的方法
     * @param imageView 需要转换的imageview控件
     * */
    public static BitmapImageViewTarget getRoundedImageView(final ImageView imageView, final Context context)
    {
        return new BitmapImageViewTarget(imageView)
        {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                roundedBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(roundedBitmapDrawable);
            }
        };
    }
}
