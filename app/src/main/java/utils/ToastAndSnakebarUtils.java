package utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Toast和snakebar的封装类
 */

public class ToastAndSnakebarUtils {
  private static Toast toast;
    private static Snackbar snackbar;

    public static void showToast(Context context,String message){
        if (toast==null){
            toast=Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }else{
            toast.setText(message);
        }
        toast.show();
    }

    public static void showSnakebar(View view, String message,int duration, String actionText,View.OnClickListener clickListener){
        if (snackbar==null){
            snackbar=Snackbar.make(view,message,duration).setAction(actionText,clickListener);
        }
        snackbar.show();
    }
}
