package com.example.haiyuan1995.fashiongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import utils.HideKeyBoard;
import utils.ToastAndSnakebarUtils;

/**
 * 注册页面
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.et_re_verificationCode)
    MaterialEditText etReVerificationCode;
    @BindView(R.id.et_re_inviteCode)
    MaterialEditText etReInviteCode;
    @BindView(R.id.et_re_password)
    MaterialEditText etRePassword;
    @BindView(R.id.et_re_checkPassword)
    MaterialEditText etReCheckPassword;
    @BindView(R.id.btn_registers)
    CardView btnRegisters;
    @BindView(R.id.tv_user_agreement)
    TextView tvUserAgreement;
    @BindView(R.id.et_re_phone)
    MaterialEditText etRePhone;
    @BindView(R.id.id_cv_getPhoneCaptcha)
    CardView idCvGetPhoneCaptcha;


    private String userPhone;
    private String password;
    private String invitecode = "";//邀请码默认88888888
    private String verificationCode;//验证码
    //registerSys  注册系统 ,SIM 接口参数必填，可为空


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    void initData() {
        btnRegisters.setOnClickListener(this);
        idCvGetPhoneCaptcha.setOnClickListener(this);
        tvUserAgreement.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_registers:
                userRegister(v);
                break;
            case R.id.id_cv_getPhoneCaptcha:
                HideKeyBoard.hide(RegisterActivity.this);
                getPhoneCaptcha(v);
                break;
            case R.id.tv_user_agreement:
                startActivity(new Intent(RegisterActivity.this, UserProtocolActivity.class));
                break;
        }
    }

    private void getPhoneCaptcha(final View v) {
        PhoneCaptchaService service = AppUrl.retrofit.create(PhoneCaptchaService.class);

        if (TextUtils.isEmpty(etRePhone.getText())) {
            etRePhone.setError("手机号不能为空!");
        } else {
            if (etRePhone.validateWith(new RegexpValidator("请输入正确的手机号!", "[1][3587]\\d{9}"))) {
                Call<ResponseBody> call = service.getPhoneCaptcha(etRePhone.getText().toString(), 0);//0注册
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        verificationCode = "123456";//默认验证码
                        String result = "";
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            result = jsonObject.getString("message");
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                        ToastAndSnakebarUtils.showSnakebar(v, result, Toast.LENGTH_SHORT, null, null);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ToastAndSnakebarUtils.showSnakebar(v, "发送失败:" + t.getMessage(), Toast.LENGTH_SHORT, null, null);
                    }
                });
            }
        }

    }

    private void userRegister(final View v) {
        RegisterService service = AppUrl.retrofit.create(RegisterService.class);

        if (TextUtils.isEmpty(etRePhone.getText())) {
            etRePhone.setError("手机号不能为空!");
        } else {
            if (etRePhone.validateWith(new RegexpValidator("请输入正确的手机号!", "[1][3587]\\d{9}"))) {
                userPhone = etRePhone.getText().toString();
            }
        }

        if (TextUtils.isEmpty(etReVerificationCode.getText())) {
            etReVerificationCode.setError("请填写验证码!");
        }

        if (TextUtils.isEmpty(etReInviteCode.getText())) {
            etReInviteCode.setError("请填写邀请码!");
        } else {
            invitecode = etReInviteCode.getText().toString();
        }

        if (TextUtils.isEmpty(etRePassword.getText())) {
            etRePassword.setError("请填写密码!");
        } else if (TextUtils.isEmpty(etReCheckPassword.getText())) {
            etReCheckPassword.setError("请确认密码!");
        } else {
            if (etRePassword.getText().toString().equals(etReCheckPassword.getText().toString())) {
                password = etRePassword.getText().toString();
            } else {
                etRePassword.setError("两个密码不一致!");
                etReCheckPassword.setError("两个密码不一致!");
            }
        }


        //创建注册请求参数集合
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(userPhone) && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(verificationCode)) {
            params.put("userPhone", userPhone);
            params.put("password", password);
            params.put("invitecode", invitecode);
            params.put("verificationCode", verificationCode);
            params.put("registerSys", AppUrl.APP_SYSTEM);
            String SIM = "";
            params.put("SIM", SIM);
            String IMEI = "";
            params.put("IMEI", IMEI);
        }
        //参数都不为空时才请求网络
        if (!TextUtils.isEmpty(params.get("userPhone")) && !TextUtils.isEmpty(params.get("password"))
                && !TextUtils.isEmpty(params.get("verificationCode"))) {
            Call<ResponseBody> call = service.postRegister(params);//传入请求参数
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().string());
                        if(!TextUtils.isEmpty(jsonObject.getString("accessToken"))){
                            ToastAndSnakebarUtils.showSnakebar(v,"注册成功！",Toast.LENGTH_LONG,null,null);
                        }else{
                            ToastAndSnakebarUtils.showSnakebar(v,"注册失败:"+jsonObject.getString("message"),Toast.LENGTH_LONG,null,null);
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    ToastAndSnakebarUtils.showSnakebar(v,"注册失败:"+t.getMessage(),Toast.LENGTH_LONG,null,null);
                }
            });
        }
    }


    public interface RegisterService {
        @FormUrlEncoded
        @POST("register")
        Call<ResponseBody> postRegister(@FieldMap Map<String, String> fields);
    }

    /**
     * 获取类型
     * 0注册 判数据库唯一
     * 1找回密码 检查数据库存在
     * 2手机号码修改 判数据库唯一
     * 3修改登录密码 检查数据库存在
     */
    public interface PhoneCaptchaService {//获取验证码的API

        @GET("getPhoneCaptcha")
        Call<ResponseBody> getPhoneCaptcha(@Query("phone") String phone, @Query("type") int type);
    }
}
