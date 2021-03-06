package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 继承自WebBaseActivity类，直接加载用户协议的URL就好
 */

public class UserProtocolActivity extends WebBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //调用父类的初始化方法
        initToolbar();
        initData();

        toolbar.setTitle("用户协议");//修改toolbar标题
        webview.loadUrl(AppUrl.USER_PROTOCOL_URL);
    }
}
