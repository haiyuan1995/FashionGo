package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import adapter.ClassifyOneAdapter;
import adapter.ClassifyTwoAdapter;
import adapter.RVBaseAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import gsonbean.ClassifyOne;
import gsonbean.ClassifyTwo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import utils.ToastAndSnakebarUtils;

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
                     initClassifyOneAdapter(response.body());
            }

            @Override
            public void onFailure(Call<ClassifyOne> call, Throwable t) {
                ToastAndSnakebarUtils.showToast(ClassifyActivity.this,"拉取信息失败:"+t.getMessage());
            }
        });

    }

    private void initClassifyOneAdapter(final ClassifyOne data) {
        final ClassifyOneAdapter adapter=new ClassifyOneAdapter(this,data.getData());
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        idClassifyOne.setLayoutManager(layoutManager);

        adapter.setRecyclerViewItemOnClick(new RVBaseAdapter.RecyclerViewItemOnClick() {
            @Override
            public void onItemClick(View view, int position) {
                int classifyOneId=data.getData().get(position).getClassifyId();
                initClassifyTwo(classifyOneId);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        idClassifyOne.setAdapter(adapter);


    }

    private void initClassifyTwo(int classifyOneId) {
        ClassifyTwoService service=AppUrl.retrofit.create(ClassifyTwoService.class);
        Call<ClassifyTwo> call=service.getClassifyTwo(classifyOneId);
        call.enqueue(new Callback<ClassifyTwo>() {
            @Override
            public void onResponse(Call<ClassifyTwo> call, Response<ClassifyTwo> response) {
                initTwoAdapter(response.body().getData());
            }

            @Override
            public void onFailure(Call<ClassifyTwo> call, Throwable t) {
                ToastAndSnakebarUtils.showToast(ClassifyActivity.this,"拉取信息失败:"+t.getMessage());
            }
        });

    }

    private void initTwoAdapter(final List<ClassifyTwo.DataBean> data) {
        ClassifyTwoAdapter adapter=new ClassifyTwoAdapter(this,data);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false);
        idClassifyTow.setLayoutManager(gridLayoutManager);
        adapter.setRecyclerViewItemOnClick(new RVBaseAdapter.RecyclerViewItemOnClick() {
            @Override
            public void onItemClick(View view, int position) {
                ToastAndSnakebarUtils.showToast(ClassifyActivity.this,
                        "商品名和id:"+data.get(position).getClassifyName()+"\n"+data.get(position).getClassifyTwoId());
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        idClassifyTow.setAdapter(adapter);
    }

    public interface ClassifyTwoService{
        @GET("getClassifyTwo")
        Call<ClassifyTwo> getClassifyTwo(@Query("classifyId")int classifyOneId);
    }
    public interface ClassifyOneService {
        @GET("getClassifyone")
        Call<ClassifyOne> getClassifyOne();
    }
}
