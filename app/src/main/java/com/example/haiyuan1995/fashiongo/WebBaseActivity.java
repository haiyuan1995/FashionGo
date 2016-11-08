package com.example.haiyuan1995.fashiongo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 4个HTML页面的基类，继承后直接修改URL加载页面
 */

public class WebBaseActivity extends AppCompatActivity {
    @BindView(R.id.id_toolbar_searchLayout)
    LinearLayout idToolbarSearchLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_web_content_ProgressBar)
    ProgressBar idWebContentProgressBar;
    @BindView(R.id.webview)
    WebView webview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprotocol);
        ButterKnife.bind(this);
        initToolbar();
        initData();

        webview.loadUrl(AppUrl.USER_PROTOCOL_URL);
    }

    public void initData() {
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {//不使用系统浏览器浏览
                webview.loadUrl(url);
                return true;
            }
        });

        WebSettings webSettings=webview.getSettings();
        webSettings.setSupportZoom(true);//支持缩放
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);//缓存


        //设置进度条
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    idWebContentProgressBar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == idWebContentProgressBar.getVisibility()) {
                        idWebContentProgressBar.setVisibility(View.VISIBLE);
                    }
                    idWebContentProgressBar.setProgress(newProgress);//设置进度
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public void initToolbar() {
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        idToolbarSearchLayout.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
