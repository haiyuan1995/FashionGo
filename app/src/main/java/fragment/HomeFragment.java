package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.haiyuan1995.fashiongo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import utils.ImageBannerView;


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
    ArrayList<String> imageUrlList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        //初始化toolbar
        toolbar.setLogo(R.mipmap.logo);
        BaseFragment.initToolbar(toolbar, getActivity());
        toolbar.findViewById(R.id.id_toolbar_searchLayout).setVisibility(View.VISIBLE);

        initData();
        initBanner();
        return view;
    }
public interface BannerService{
    @GET("getThreeGoodsADInfo")
    Call<ResponseBody> getBannerImages();
}
    private void initBanner() {




//        urlList.add("http://image2.sina.com.cn/dy/c/p/2007-01-18/U1565P1T1D12074936F21DT20070118224118.jpg");
//        urlList.add("http://www.wokeji.com/shouye/shipin/201605/W020160524632769348038.png");
//        urlList.add("http://www.ecns.cn/hd/2016/05/25/1d0dccb881dd49289ba931e41de81665.jpg");
//        urlList.add("http://img.sootuu.com/Exchange/2010-5/20105191031898859.jpg");
//        String[] strings = {"现代快报", "科技日报", "北京晨报", "人民法院报"};
//
//        idHomeImageBannerView.setImageResources(urlList, strings, new ImageBannerView.ImageCycleViewListener() {
//            @Override
//            public void displayImage(String imageURL, ImageView imageView) {
//                Glide.with(getActivity()).load(imageURL).into(imageView);
//            }
//
//            @Override
//            public void onImageClick(int position, View imageView) {
//                switch (position) {
//                    case 0:
//                        break;
//                }
//            }
//        });
    }

    @Override
    void initData() {
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("http://112.124.118.133:9065/ssgApp/")
//                .build();
//        BannerService bannerService=retrofit.create(BannerService.class);
//        Call<ResponseBody> call=bannerService.getBannerImages();
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    System.out.println(response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

    }


}
