package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragment.GoodsInfoFragment;
import gsonbean.GoodsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import utils.ToastAndSnakebarUtils;

/**
 * 商品信息或搜索结果列表
 */

public class GoodsInfoListActivity extends BaseActivity {
    @BindView(R.id.id_toolbar_search)
    EditText idToolbarSearch;
    @BindView(R.id.id_toolbar_searchLayout)
    LinearLayout idToolbarSearchLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<Fragment> mFragmentList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idToolbarSearchLayout.setVisibility(View.VISIBLE);
        idToolbarSearch.setFocusable(true);
        idToolbarSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //如果actionId是搜索的id，则进行下一步的操作
                    String keyword=idToolbarSearch.getText().toString();//搜索商品，若没有值为“”
                    int ClassifyThreeId=0;//三级分类id，若从广告接口、首页商品搜索，默认值0，
                    int difference=0;//默认0 ，分类，首页搜索 1广告接口进入（新品推荐） 2广告接口进入（热销单品）
                    int type=0;//默认0 相关，1销量，2价格，3新品，
                    int page=0;
                    SearchGoods(keyword,ClassifyThreeId,difference,type,page);
                }
                return false;
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    void initData() {

        if (getIntent().getAction().equals("ClassifyActivity")) {
            int classifyThreeId = getIntent().getIntExtra("ClassifyThreeId", 0);
            if (classifyThreeId != 0) {
                ToastAndSnakebarUtils.showToast(this, String.valueOf(getIntent().getIntExtra("ClassifyThreeId",0)));
                initFragment(getIntent().getIntExtra("ClassifyThreeId", 0));
            }
        }

    }

    private void initFragment(int classifyThreeId) {
        //type	int	默认0 相关，1销量，2价格，3新品

        mFragmentList=new ArrayList<>();
        GoodsInfoFragment xiangguan=new GoodsInfoFragment();
        Bundle xgbundle=new Bundle();
        xgbundle.putInt("ClassifyThreeId",classifyThreeId);
        xgbundle.putInt("Type",0);
        xiangguan.setArguments(xgbundle);
//
//        GoodsInfoFragment xiaoliang=new GoodsInfoFragment();
//
//
//        GoodsInfoFragment jiangge=new GoodsInfoFragment();
//        GoodsInfoFragment xinping=new GoodsInfoFragment();

        mFragmentList.add(xiangguan);
//        mFragmentList.add(xiaoliang);
//        mFragmentList.add(jiangge);
//        mFragmentList.add(xinping);
    }

    private void SearchGoods(String keyword, int classifyThreeId, int difference, int type, int page) {
        GoodsListService service = AppUrl.retrofit.create(GoodsListService.class);
        Call<GoodsList> call = service.getGoodsList(keyword, classifyThreeId, difference, type, page);
        call.enqueue(new Callback<GoodsList>() {
            @Override
            public void onResponse(Call<GoodsList> call, Response<GoodsList> response) {
                ToastAndSnakebarUtils.showToast(GoodsInfoListActivity.this, response.body().getMessage());
                List<GoodsList.DataBean> data = response.body().getData();
            }

            @Override
            public void onFailure(Call<GoodsList> call, Throwable t) {
                ToastAndSnakebarUtils.showToast(GoodsInfoListActivity.this, "搜索失败:" + t.getMessage());
            }
        });
    }

    public interface GoodsListService {
        @FormUrlEncoded
        @POST("getGoodsList")
        Call<GoodsList> getGoodsList(@Field("keyword") String keyword, @Field("ClassifyThreeId") int classifyThreeId
                , @Field("difference") int difference, @Field("type") int type, @Field("page") int page);

    }

}
