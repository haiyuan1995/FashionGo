package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haiyuan1995.fashiongo.AboutUsActivity;
import com.example.haiyuan1995.fashiongo.R;
import com.example.haiyuan1995.fashiongo.TipsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 更多设置
 */

public class SettingFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_about_us)
    CardView idAboutUs;
    @BindView(R.id.id_clear_cache)
    CardView idClearCache;
    @BindView(R.id.id_tips)
    CardView idTips;
    @BindView(R.id.id_check_version)
    CardView idCheckVersion;
    @BindView(R.id.id_logout)
    CardView idLogout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        BaseFragment.initToolbar(toolbar, getActivity());
        toolbar.setTitle("设置");
        toolbar.setTitleTextColor(Color.WHITE);
        initData();
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    void initData() {
        idAboutUs.setOnClickListener(this);
        idClearCache.setOnClickListener(this);
        idCheckVersion.setOnClickListener(this);
        idTips.setOnClickListener(this);
        idLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_about_us:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.id_clear_cache:
                break;
            case R.id.id_tips:
                startActivity(new Intent(getActivity(), TipsActivity.class));
                break;
            case R.id.id_check_version:
                break;
            case R.id.id_logout:
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
