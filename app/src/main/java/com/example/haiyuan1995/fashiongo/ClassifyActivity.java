package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gsonbean.ClassifyOne;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * 商品分类页面
 */

public class ClassifyActivity extends BaseActivity {
    @BindView(R.id.id_classify_one)
    RecyclerView idClassifyOne;
    @BindView(R.id.id_classify_tow)
    RecyclerView idClassifyTow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    void initData() {
        ClassifyOneService service=AppUrl.retrofit.create(ClassifyOneService.class);
        Call<ClassifyOne> call=service.getClassifyOne();
        call.enqueue(new Callback<ClassifyOne>() {
            @Override
            public void onResponse(Call<ClassifyOne> call, Response<ClassifyOne> response) {

            }

            @Override
            public void onFailure(Call<ClassifyOne> call, Throwable t) {

            }
        });

    }

    public interface ClassifyOneService {
        @GET("getClassifyone")
        Call<ClassifyOne> getClassifyOne();
    }
}
