package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 找回密码页面,继承自用户协议界面
 */

public class FindPasswordActivity extends WebBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initToolbar();
        toolbar.setTitle("找回密码");
        initData();

        webview.loadUrl(AppUrl.FINDPASSWORD_URL);
    }
}
