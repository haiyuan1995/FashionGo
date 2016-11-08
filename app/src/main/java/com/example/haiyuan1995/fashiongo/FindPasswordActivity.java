package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 找回密码页面
 */

public class FindPasswordActivity extends UserProtocolActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprotocol);
       webview.loadUrl(AppUrl.FINDPASSWORD_URL);
    }
}
