package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 意见反馈界面
 */

public class TipsActivity extends WebBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initData();
        webview.loadUrl(AppUrl.TIPS_URL);
    }
}
