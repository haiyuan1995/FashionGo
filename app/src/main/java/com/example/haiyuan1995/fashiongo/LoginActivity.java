package com.example.haiyuan1995.fashiongo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import utils.ToastAndSnakebarUtils;

import static com.example.haiyuan1995.fashiongo.R.id.et_name;
import static com.example.haiyuan1995.fashiongo.R.id.et_password;

/**
 * 登录界面
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.id_toolbar_searchLayout)
    LinearLayout idToolbarSearchLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(et_name)
    MaterialEditText etName;
    @BindView(et_password)
    MaterialEditText etPassword;
    @BindView(R.id.btn_ok)
    CardView btnOk;
    @BindView(R.id.btn_zhuce)
    CardView btnZhuce;
    @BindView(R.id.cb_remeber)
    CheckBox cbRemeber;
    @BindView(R.id.btn_find_password)
    Button btnFindPassword;

    private String userName;
    private String passWord;
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);

        preferences= getSharedPreferences("config",MODE_PRIVATE);
        edit=preferences.edit();

        initToolbar();
        initData();
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

        btnOk.setOnClickListener(this);
        btnZhuce.setOnClickListener(this);

        btnFindPassword.setOnClickListener(this);

        //是否记住了密码
        boolean isChecked=preferences.getBoolean("ischeck", false);
        if(isChecked)
        {
            etName.setText(preferences.getString("user_name", ""));
            etPassword.setText(preferences.getString("user_password", ""));
        }
        cbRemeber.setChecked(isChecked);//设置checkbox是否打钩
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                UserLogin();
                break;
            case R.id.btn_zhuce:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.btn_find_password:
                startActivity(new Intent(LoginActivity.this,FindPasswordActivity.class));
                break;
        }
    }

    private void UserLogin() {
        LoginService service=AppUrl.retrofit.create(LoginService.class);
        if (TextUtils.isEmpty(etName.getText().toString())){
            etName.setError("账户不能为空!");
        }else{
            if (etName.validateWith(new RegexpValidator("请输入正确的手机号!", "[1][3587]\\d{9}"))) {
                userName = etName.getText().toString();
            }
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError("密码不能为空!");
        }else{
            passWord=etPassword.getText().toString();
        }



        if (!TextUtils.isEmpty(passWord)&&!TextUtils.isEmpty(userName)) {
            boolean isChecked=cbRemeber.isChecked();//得到选中状态
            edit.putBoolean("ischeck", isChecked);

            if(isChecked) {
                edit.putString("user_name",userName );
                edit.putString("user_password", passWord);
            }
            else {
                edit.remove("user_name").remove("user_password");
            }
            edit.apply();


            Call<ResponseBody> call = service.getLogin(userName,passWord,AppUrl.APP_SYSTEM);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().string());
                        String accessToken=jsonObject.getJSONObject("data").getString("accessToken");

                        //把accessToken值存入本地

                        edit.putString("accessToken",accessToken);
                        edit.apply();

                        if (!TextUtils.isEmpty(preferences.getString("accessToken",""))) {
                            ToastAndSnakebarUtils.
                                    showToast(LoginActivity.this,
                                            jsonObject.getString("message"));
                            //发送广播通知settingfragment更新UI
                                LoginActivity.this.sendBroadcast(new Intent().setAction("LoginActivity"));
//                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        }

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    ToastAndSnakebarUtils.showToast(LoginActivity.this,"登录失败:"+t.getMessage());
                }
            });
        }
    }

    public interface LoginService{
        @GET("login")
        Call<ResponseBody> getLogin(@Query("userName") String userName,
                                    @Query("password") String passWord,
                                    @Query("system") String system);
    }
}
