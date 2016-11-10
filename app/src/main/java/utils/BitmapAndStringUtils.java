package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 可以将bitmap转为base64 编码的字符串，用以上传图片
 * 或将base64字符串转为bitmap
 */

public class BitmapAndStringUtils {

    /**
     * 图片转成string
     *
     * @param bitmap
     * @return 返回base64的字符串
     */
    public static String BitmapToBase64(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = null;
        String result = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();// outputstream
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();// 转为byte数组
                result= Base64.encodeToString(bitmapBytes, Base64.DEFAULT);//转为base64字符串
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (baos!=null){
                try {
                    baos.flush();
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

    }

    /**
     * string转成bitmap
     * @param base64String base64编码的字符串
     * @return 返回bitmap对象
     */
    public static Bitmap Base64ToBitmap(String base64String)
    {
        // OutputStream out;
        Bitmap bitmap = null;

        // out = new FileOutputStream("/sdcard/aa.jpg");
        byte[] bitmapArray= Base64.decode(base64String, Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                bitmapArray.length);
        // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        return bitmap;

    }
}
