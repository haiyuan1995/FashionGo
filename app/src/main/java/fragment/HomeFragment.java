package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.haiyuan1995.fashiongo.ClassifyActivity;
import com.example.haiyuan1995.fashiongo.GoodsInfoListActivity;
import com.example.haiyuan1995.fashiongo.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RVBaseAdapter;
import adapter.RecommendationAdapter;
import adapter.ThreeGoodsADInfoAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import gsonbean.Banner;
import gsonbean.GoodsADInfo;
import gsonbean.Recommendation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import utils.ImageBannerView;
import utils.ToastAndSnakebarUtils;

import static com.example.haiyuan1995.fashiongo.AppUrl.retrofit;


/**
 * 主页
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_home_imageBannerView)
    ImageBannerView idHomeImageBannerView;
    @BindView(R.id.id_toolbar_search)
    EditText idToolbarSearch;
    @BindView(R.id.id_home_rv_threeGoodsInfo)
    RecyclerView idHomeRvThreeGoodsInfo;
    @BindView(R.id.id_home_rv_recommendation)
    RecyclerView idHomeRvRecommendation;
    @BindView(R.id.id_home_refresh)
    MaterialRefreshLayout idHomeRefresh;


    ArrayList<String> imageUrlList = new ArrayList<>();
    @BindView(R.id.id_goodsClassifyList)
    ImageView idGoodsClassifyList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        //初始化toolbar
        initEvent();
        return view;
    }

    private void initEvent() {
        toolbar.setLogo(R.mipmap.logo);
        BaseFragment.initToolbar(toolbar, getActivity());
        toolbar.findViewById(R.id.id_toolbar_searchLayout).setVisibility(View.VISIBLE);
        toolbar.findViewById(R.id.id_goodsClassifyList).setVisibility(View.VISIBLE);
        idHomeRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageUrlList.clear();//刷新时先清空之前的图片地址集合

                        initData();
                        initThreeGoodsAD();
                        initRecommendation();
                        idHomeRefresh.finishRefresh();

                    }
                }, 2000);
            }
        });
        idGoodsClassifyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ClassifyActivity.class));
            }
        });


        idToolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoodsInfoListActivity.class).setAction("HomeFragment"));
            }
        });
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initThreeGoodsAD();
        initRecommendation();
    }




    public interface BannerService {
//    @GET("getThreeGoodsADInfo")
//    Call<ResponseBody> getBannerImages();

        @GET("getBannerList")
        Call<Banner> getBannerImages();
    }

    public interface ThreeGoodsADInfoService {
        @GET("getThreeGoodsADInfo")
        Call<GoodsADInfo> getThreeGoodsADInfo();
    }

    public interface RecommendationService {
        @GET("getRecommendation")
//page页码=1
        Call<Recommendation> getRecommendation(@Query("page") int page);
    }

    private void initRecommendation() {
        RecommendationService service = retrofit.create(RecommendationService.class);
        Call<Recommendation> call = service.getRecommendation(1);
        call.enqueue(new Callback<Recommendation>() {
            @Override
            public void onResponse(Call<Recommendation> call, Response<Recommendation> response) {
                initRecommendationAdapter(response.body().getData());
            }

            @Override
            public void onFailure(Call<Recommendation> call, Throwable t) {
                ToastAndSnakebarUtils.showToast(getContext(), "刷新失败:" + t.getMessage());
            }
        });


    }

    private void initRecommendationAdapter(List<Recommendation.DataBean> data) {
        final RecommendationAdapter adapter = new RecommendationAdapter(getActivity(), data);
        adapter.setRecyclerViewItemOnClick(new RVBaseAdapter.RecyclerViewItemOnClick() {
            @Override
            public void onItemClick(View view, int position) {
                ToastAndSnakebarUtils.showToast(getActivity(), "单击" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastAndSnakebarUtils.showToast(getActivity(), "长按" + position);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        idHomeRvRecommendation.setLayoutManager(linearLayoutManager);
        idHomeRvRecommendation.setAdapter(adapter);
        //数据加载完成
        idHomeRefresh.finishRefresh();
        ToastAndSnakebarUtils.showToast(getContext(), "刷新成功!");
    }

    private void initThreeGoodsAD() {
        ThreeGoodsADInfoService adInfoService = retrofit.create(ThreeGoodsADInfoService.class);
        Call<GoodsADInfo> call = adInfoService.getThreeGoodsADInfo();
        call.enqueue(new Callback<GoodsADInfo>() {
            @Override
            public void onResponse(Call<GoodsADInfo> call, Response<GoodsADInfo> response) {
                GoodsADInfo.DataBean more = new GoodsADInfo.DataBean();
                more.setADImage("R.drawable.ic_goodsinfo_more");
                response.body().getData().add(3, more);//手动添加一个推荐商品，3个太难看

                initGoodsAdapter(response.body().getData());
            }

            @Override
            public void onFailure(Call<GoodsADInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void initGoodsAdapter(List<GoodsADInfo.DataBean> data) {
        ThreeGoodsADInfoAdapter adapter = new ThreeGoodsADInfoAdapter(getActivity(), data);
        adapter.setRecyclerViewItemOnClick(new RVBaseAdapter.RecyclerViewItemOnClick() {
            @Override
            public void onItemClick(View view, int position) {
                ToastAndSnakebarUtils.showToast(getActivity(), "单击" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastAndSnakebarUtils.showToast(getActivity(), "长按" + position);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        idHomeRvThreeGoodsInfo.setLayoutManager(gridLayoutManager);

        idHomeRvThreeGoodsInfo.setAdapter(adapter);
    }


    private void initBanner() {

        String[] strings = {"1", "2", "3", "4", "5"};
        if (!imageUrlList.isEmpty()) {
            idHomeImageBannerView.setImageResources(imageUrlList, strings, new ImageBannerView.ImageCycleViewListener() {
                @Override
                public void displayImage(String imageURL, ImageView imageView) {
                    Glide.with(getActivity()).load(imageURL).into(imageView);
                }

                @Override
                public void onImageClick(int position, View imageView) {
                    switch (position) {
                        case 0:
                            break;
                    }
                }
            });
        }
    }

    @Override
    void initData() {


        BannerService bannerService = retrofit.create(BannerService.class);
        Call<Banner> call = bannerService.getBannerImages();
        call.enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                System.out.println(response.body().getData().get(0).getBannerImage() + "\n" + response.body().getData().get(0).getGoodsCode());
                for (int i = 0; i < response.body().getData().size(); i++) {
                    imageUrlList.add(response.body().getData().get(i).getBannerImage());
                }
                initBanner();
            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_main, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_goodslist:
//                ToastAndSnakebarUtils.showToast(getActivity(), "点击菜单");
//                startActivity(new Intent(getActivity(), ClassifyActivity.class));
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
