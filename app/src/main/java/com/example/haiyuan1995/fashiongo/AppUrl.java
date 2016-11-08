package com.example.haiyuan1995.fashiongo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 程序接口地址
 */

public class AppUrl {
    public static final String  APP_URL="http://112.124.118.133:9065/ssgApp/";

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppUrl.APP_URL)
            //GsonConverterFactory.create()表示调用Gson库来解析json返回值
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final String USER_PROTOCOL_URL=APP_URL+"protocol.html";//用户协议
    public static final String FINDPASSWORD_URL=APP_URL+"findPassword.html";//找回密码
    public static final String ABOUT_US_URL=APP_URL+"aboutUs.html";//关于
    public static final String TIPS_URL=APP_URL+"tips.html";//意见反馈

    public static final String APP_SYSTEM="android";
}
