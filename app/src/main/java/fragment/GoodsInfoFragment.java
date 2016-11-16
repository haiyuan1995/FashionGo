package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haiyuan1995.fashiongo.AppUrl;
import com.example.haiyuan1995.fashiongo.GoodsInfoListActivity;
import com.example.haiyuan1995.fashiongo.R;

import java.util.List;

import gsonbean.GoodsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.ToastAndSnakebarUtils;

/**
 * Created by haiyuan1995 on 2016/11/16.
 */

public class GoodsInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goods_info,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        int classifyThreeId= getArguments().getInt("ClassifyThreeId");
        int type=getArguments().getInt("Type");
        SearchGoods(null,classifyThreeId,0,type,1);

}
    private void SearchGoods(String keyword, int classifyThreeId, int difference, int type, int page) {
        GoodsInfoListActivity.GoodsListService service = AppUrl.retrofit.create(GoodsInfoListActivity.GoodsListService.class);
        Call<GoodsList> call = service.getGoodsList(keyword, classifyThreeId, difference, type, page);
        call.enqueue(new Callback<GoodsList>() {
            @Override
            public void onResponse(Call<GoodsList> call, Response<GoodsList> response) {
                ToastAndSnakebarUtils.showToast(getActivity(), response.body().getMessage());
                List<GoodsList.DataBean> data = response.body().getData();
            }

            @Override
            public void onFailure(Call<GoodsList> call, Throwable t) {
                ToastAndSnakebarUtils.showToast(getActivity(), "搜索失败:" + t.getMessage());
            }
        });
    }
}
