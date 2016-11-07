package com.example.haiyuan1995.fashiongo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.ToastAndSnakebarUtils;

/**
 * Created by haiyuan on 2016/11/8.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.id_toolbar_searchLayout)
    LinearLayout idToolbarSearchLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_name)
    MaterialEditText etName;
    @BindView(R.id.et_password)
    MaterialEditText etPassword;
    @BindView(R.id.btn_ok)
    CardView btnOk;
    @BindView(R.id.btn_zhuce)
    CardView btnZhuce;
    @BindView(R.id.cb_remeber)
    CheckBox cbRemeber;
    @BindView(R.id.btn_find_password)
    Button btnFindPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        if (toolbar != null) {
            toolbar.setTitle("登录");
            toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            idToolbarSearchLayout.setVisibility(View.GONE);
            setSupportActionBar(toolbar);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastAndSnakebarUtils.showToast(LoginActivity.this,"返回");
                    finish();
                }
            });
        }
    }

    @Override
    void initData() {

    }
}
