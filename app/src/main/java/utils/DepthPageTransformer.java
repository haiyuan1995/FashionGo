package utils;


import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @SuppressLint("NewApi") 
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // 从屏幕左边退出
            view.setAlpha(0);
            
            

        } else if (position <= 0) { // [-1,0]
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);
        	


        } else if (position <= 1) { // (0,1]
            // 淡出页面
            view.setAlpha(1 - position);
        	


            view.setTranslationX(pageWidth * -position);
            

            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            


        } else { // (1,+Infinity]
            // 从右边退出
            view.setAlpha(0);

        }
    }
}
