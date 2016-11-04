package com.example.haiyuan1995.fashiongo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragment.HomeFragment;
import fragment.MyFragment;
import fragment.OrderFragment;
import fragment.SettingFragment;
import fragment.ShoppingFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.id_viewpager)
    ViewPager idViewpager;
    @BindView(R.id.id_tab_home_iv)
    ImageView idTabHomeIv;
    @BindView(R.id.id_tab_home_tv)
    TextView idTabHomeTv;
    @BindView(R.id.id_tab_home)
    LinearLayout idTabHome;
    @BindView(R.id.id_tab_order_iv)
    ImageView idTabOrderIv;
    @BindView(R.id.id_tab_order_tv)
    TextView idTabOrderTv;
    @BindView(R.id.id_tab_order)
    LinearLayout idTabOrder;
    @BindView(R.id.id_tab_shopping_iv)
    ImageView idTabShoppingIv;
    @BindView(R.id.id_tab_shopping_tv)
    TextView idTabShoppingTv;
    @BindView(R.id.id_tab_shopping)
    LinearLayout idTabShopping;
    @BindView(R.id.id_tab_my_iv)
    ImageView idTabMyIv;
    @BindView(R.id.id_tab_my_tv)
    TextView idTabMyTv;
    @BindView(R.id.id_tab_my)
    LinearLayout idTabMy;
    @BindView(R.id.id_tab_setting_iv)
    ImageView idTabSettingIv;
    @BindView(R.id.id_tab_setting_tv)
    TextView idTabSettingTv;
    @BindView(R.id.id_tab_setting)
    LinearLayout idTabSetting;

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initEvent();

    }

    private void initEvent() {
        idTabHome.setOnClickListener(this);
        idTabOrder.setOnClickListener(this);
        idTabShopping.setOnClickListener(this);
        idTabMy.setOnClickListener(this);
        idTabSetting.setOnClickListener(this);
    }

    @Override
    void initData() {
        mFragmentList = new ArrayList<>();
        Fragment homeFragment = new HomeFragment();
        Fragment orderFragment = new OrderFragment();
        Fragment shoppingFragment = new ShoppingFragment();
        Fragment myFragment = new MyFragment();
        Fragment settingFragment = new SettingFragment();

        mFragmentList.add(homeFragment);
        mFragmentList.add(orderFragment);
        mFragmentList.add(shoppingFragment);
        mFragmentList.add(myFragment);
        mFragmentList.add(settingFragment);

        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };

        idViewpager.setAdapter(mFragmentPagerAdapter);

        idViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = idViewpager.getCurrentItem();
                setTabColor(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_home:
                setSelect(0);
                break;
            case R.id.id_tab_order:
                setSelect(1);
                break;
            case R.id.id_tab_shopping:
                setSelect(2);
                break;
            case R.id.id_tab_my:
                setSelect(3);
                break;
            case R.id.id_tab_setting:
                setSelect(4);
                break;
        }
    }

    private void setSelect(int i) {
        setTabColor(i);
        idViewpager.setCurrentItem(i);   // 切换内容区域
    }

    // 设置图片为亮色

    private void setTabColor(int currentItem) {
        resetImages();

        switch (currentItem) {
            case 0:
                idTabHomeIv.setImageResource(R.drawable.ic_home_on);
                idTabHomeTv.setTextColor(getColor(R.color.colorAccent));
                break;
            case 1:
                idTabOrderIv.setImageResource(R.drawable.ic_order_on);
                idTabOrderTv.setTextColor(getColor(R.color.colorAccent));
                break;
            case 2:
                idTabShoppingIv.setImageResource(R.drawable.ic_shopping_cart_on);
                idTabShoppingTv.setTextColor(getColor(R.color.colorAccent));
                break;
            case 3:
                idTabMyIv.setImageResource(R.drawable.ic_my_on);
                idTabMyTv.setTextColor(getColor(R.color.colorAccent));
                break;
            case 4:
                idTabSettingIv.setImageResource(R.drawable.ic_settings_on);
                idTabSettingTv.setTextColor(getColor(R.color.colorAccent));
                break;
        }

    }

    /**
     * 切换图片和文字至暗色
     */
    private void resetImages() {
        idTabHomeIv.setImageResource(R.drawable.ic_home_off);
        idTabOrderIv.setImageResource(R.drawable.ic_order_off);
        idTabShoppingIv.setImageResource(R.drawable.ic_shopping_cart_off);
        idTabMyIv.setImageResource(R.drawable.ic_my_off);
        idTabSettingIv.setImageResource(R.drawable.ic_settings_off);

        idTabHomeTv.setTextColor(getColor(R.color.colorBlack));
        idTabOrderTv.setTextColor(getColor(R.color.colorBlack));
        idTabShoppingTv.setTextColor(getColor(R.color.colorBlack));
        idTabMyTv.setTextColor(getColor(R.color.colorBlack));
        idTabSettingTv.setTextColor(getColor(R.color.colorBlack));
    }
}
