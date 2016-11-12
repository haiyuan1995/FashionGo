package com.example.haiyuan1995.fashiongo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gsonbean.Area;
import gsonbean.City;
import gsonbean.Province;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import utils.ToastAndSnakebarUtils;

/**
 * 选择省份，城市和地区
 */

public class SelectAddressActivity extends BaseActivity {
    @BindView(R.id.id_select_pName)
    MaterialSpinner idSelectPName;
    @BindView(R.id.id_select_cName)
    MaterialSpinner idSelectCName;
    @BindView(R.id.id_select_aName)
    MaterialSpinner idSelectAName;
    @BindView(R.id.id_toolbar)
    Toolbar idToolbar;
    @BindView(R.id.id_address_save)
    TextView idAddressSave;

    private HashMap<String ,String> addressInfo=new HashMap<>();//存放选择信息

    private List<String> provinceList = new ArrayList<>();//省份列表

    private List<String> cityList=new ArrayList<>();//城市列表

    private List<String> areaList=new ArrayList<>();//区域列表


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        ButterKnife.bind(this);


        initToolbar();
        initData();


    }

    private void initToolbar() {
        idToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        idToolbar.setTitle("选择地址");

        setSupportActionBar(idToolbar);
        idToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    void initData() {

        getProvinceList();

        idAddressSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(addressInfo.get("pCode"))
                        ||TextUtils.isEmpty(addressInfo.get("cCode"))
                        ||TextUtils.isEmpty(addressInfo.get("aCode"))){
                        ToastAndSnakebarUtils.showSnakebar(idAddressSave
                                ,"所选择的信息有问题，无法保存"
                                ,Snackbar.LENGTH_LONG,null,null);
                }else{
                    Intent data=new Intent();

                    Bundle bundle=new Bundle();
                    bundle.putSerializable("addressInfo",addressInfo);

                    data.putExtra("addressBundle",bundle);

                    setResult(PersonActivity.RESULT_SELECT_ADDRESS,data);
                    finish();//一定要finish，否则不会返回result
                }
            }
        });

    }

    private void getProvinceList() {


        ProvinceListService service = AppUrl.retrofit.create(ProvinceListService.class);
        Call<Province> call = service.getProvinceList();
        call.enqueue(new Callback<Province>() {
            @Override
            public void onResponse(Call<Province> call, final Response<Province> response) {
                for (int i = 0; i < response.body().getData().size(); i++) {

                    provinceList.add(response.body().getData().get(i).getPName());//添加省份名到list中

                }

                idSelectPName.setItems(provinceList);
                //初始化默认省份和城市为北京
                if (TextUtils.isEmpty(addressInfo.get("pCode"))){
                    addressInfo.put("pName",response.body().getData().get(0).getPName());
                    addressInfo.put("pCode",response.body().getData().get(0).getPCode());
                    getCityList(response.body().getData().get(0).getPCode());
                }else{
                    getCityList(response.body().getData().get(0).getPCode());
                }
                idSelectPName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        String provinceCode=response.body().getData().get(position).getPCode();
                        //存放所选择的省份名和code
                        addressInfo.put("pName",response.body().getData().get(position).getPName());
                        addressInfo.put("pCode",provinceCode);
                        addressInfo.remove("cName");
                        addressInfo.remove("cCode");
                        getCityList(provinceCode);
                    }
                });
            }

            @Override
            public void onFailure(Call<Province> call, Throwable t) {
                ToastAndSnakebarUtils.showSnakebar(idSelectAName,"拉取省份失败:"+t.getMessage(), Snackbar.LENGTH_LONG,null,null);
            }
        });

        provinceList.clear();

    }


    private void getCityList(final String provinceCode) {
        CityListService service=AppUrl.retrofit.create(CityListService.class);
        Call<City> call=service.getCityList(provinceCode);
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, final Response<City> response) {
                for (int i=0;i<response.body().getData().size();i++){
                    String cityName=response.body().getData().get(i).getCName();
                        cityList.add(cityName);
                }
                idSelectCName.setItems(cityList);
                idSelectCName.setSelectedIndex(0);
                //初始化为默认值

                if (TextUtils.isEmpty(addressInfo.get("cCode"))){
                    addressInfo.put("cName",response.body().getData().get(0).getCName());
                    addressInfo.put("cCode",response.body().getData().get(0).getCCode());
                    getAreaList(response.body().getData().get(0).getCCode());

                }else{
                    //更换了省份默认选第一个城市
                    addressInfo.put("cName",response.body().getData().get(0).getCName());
                    addressInfo.put("cCode",response.body().getData().get(0).getCCode());
                    getAreaList(response.body().getData().get(0).getCCode());

                }
                idSelectCName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        String cityCode=response.body().getData().get(position).getCCode();
                        String cityName=response.body().getData().get(position).getCName();


                        if (TextUtils.equals(cityName,"北京市")//如果选择了直辖市，直接传cCode
                                ||TextUtils.equals(cityName,"天津市")
                                ||TextUtils.equals(cityName,"上海市")
                                ||TextUtils.equals(cityName,"重庆市")){
                            addressInfo.put("cName",cityName);
                            addressInfo.put("cCode",cityCode);
                            getAreaList(addressInfo.get("cCode"));

                        }else {
                            addressInfo.put("cName",cityName);
                            addressInfo.put("cCode",cityCode);
                            getAreaList(cityCode);
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                ToastAndSnakebarUtils.showSnakebar(idSelectAName,"拉取城市失败:"+t.getMessage(), Snackbar.LENGTH_LONG,null,null);
            }
        });

        cityList.clear();

    }

    private void getAreaList(String cityCode) {
        AreaListService service=AppUrl.retrofit.create(AreaListService.class);
        Call<Area> call=service.getAreaList(cityCode);
        call.enqueue(new Callback<Area>() {
            @Override
            public void onResponse(Call<Area> call, final Response<Area> response) {
                for (int i=0;i<response.body().getData().size();i++){
                    areaList.add(response.body().getData().get(i).getArea());
                }
                idSelectAName.setItems(areaList);
                idSelectAName.setSelectedIndex(0);
                if (TextUtils.isEmpty(addressInfo.get("aName"))){
                    addressInfo.put("aName",response.body().getData().get(0).getArea());
                    addressInfo.put("aCode",response.body().getData().get(0).getACode());
                }else{//默认第一个城市的第一个区域
                    addressInfo.put("aName",response.body().getData().get(0).getArea());
                    addressInfo.put("aCode",response.body().getData().get(0).getACode());
                }

                idSelectAName.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                        addressInfo.put("aName",response.body().getData().get(position).getArea());
                        addressInfo.put("aCode",response.body().getData().get(position).getACode());
                        ToastAndSnakebarUtils.showToast(SelectAddressActivity.this,addressInfo.get("pName")+addressInfo.get("cName")+addressInfo.get("aName"));
                    }
                });
               ToastAndSnakebarUtils.showToast(SelectAddressActivity.this,addressInfo.get("pName")+addressInfo.get("cName")+addressInfo.get("aName"));

            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                ToastAndSnakebarUtils.showSnakebar(idSelectAName,"拉取区域失败:"+t.getMessage(), Snackbar.LENGTH_LONG,null,null);
            }
        });
        areaList.clear();

    }

    public interface AreaListService{
        @GET("getAreaList")
        Call<Area> getAreaList(@Query("cCode")String cityCode);
    }

    public interface CityListService{
        @GET("getCityList")
        Call<City> getCityList(@Query("pCode")String provinceCode);
    }
    public interface ProvinceListService {
        @GET("getProvinceList")
        Call<Province> getProvinceList();
    }

}
