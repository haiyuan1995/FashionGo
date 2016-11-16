package com.example.haiyuan1995.fashiongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import adapter.ClassifyOneAdapter;
import adapter.ClassifyThreeAdapter;
import adapter.ClassifyTwoAdapter;
import adapter.RVBaseAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import gsonbean.ClassifyOne;
import gsonbean.ClassifyThree;
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
                initClassifyThree(data.get(position).getClassifyTwoId());//传入2级分类ID
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        idClassifyTow.setAdapter(adapter);
    }

    private void initClassifyThree(final int classifyTwoId) {
        //请求分类3的数据
        ClassifyThreeService service=AppUrl.retrofit.create(ClassifyThreeService.class);
        Call<ClassifyThree> call=service.getClassifyThree(classifyTwoId);
        call.enqueue(new Callback<ClassifyThree>() {
            @Override
            public void onResponse(Call<ClassifyThree> call, final Response<ClassifyThree> response) {
                ClassifyThreeAdapter adapter=new ClassifyThreeAdapter(ClassifyActivity.this,response.body().getData());
                LinearLayoutManager layoutManager=new LinearLayoutManager(ClassifyActivity.this,LinearLayoutManager.VERTICAL,false);

                AlertDialog alert = new AlertDialog.Builder(ClassifyActivity.this).create();
                alert.show();
                Window window = alert.getWindow();
                if (window != null) {
                    window.setContentView(R.layout.dialog_classify_three);
                    window.setGravity(Gravity.BOTTOM);//设置弹窗位置
//                window.getDecorView().setPadding(0,0,0,0);//设置弹窗的padding
                    WindowManager.LayoutParams params = window.getAttributes();
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(params);//调整窗口大小
                    window.setWindowAnimations(R.style.dialog_select_picture_anim);

                    RecyclerView recyclerView = (RecyclerView) window.findViewById(R.id.id_classify_three_rcy);
                    adapter.setRecyclerViewItemOnClick(new RVBaseAdapter.RecyclerViewItemOnClick() {
                        @Override
                        public void onItemClick(View view, int position) {
                            startActivity(new Intent(ClassifyActivity.this,GoodsInfoListActivity.class)
                                    .setAction("ClassifyActivity")
                                    .putExtra("ClassifyThreeId",response.body().getData()
                                            .get(position).getClassifyThreeId()));
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    });
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ClassifyThree> call, Throwable t) {
                ToastAndSnakebarUtils.showToast(ClassifyActivity.this,"拉取信息失败:"+t.getMessage());
            }
        });


    }

    public interface ClassifyThreeService{
        @GET("getClassifyThree")
        Call<ClassifyThree> getClassifyThree(@Query("ClassifyTwoId")int classifyTwoId);

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
