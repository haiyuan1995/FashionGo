package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 关于我们页面
 */

public class AboutUsActivity extends WebBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initData();
        webview.loadUrl(AppUrl.ABOUT_US_URL);
    }
}
