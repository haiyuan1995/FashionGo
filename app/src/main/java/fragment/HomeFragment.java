package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.haiyuan1995.fashiongo.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 主页
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.id_search_searchView)
    SearchView idSearchSearchView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container,false);
        ButterKnife.bind(this, view);
        //初始化toolbar
        toolbar.setLogo(R.mipmap.logo);
        BaseFragment.initToolbar(toolbar, getActivity());
        toolbar.findViewById(R.id.id_search_searchView).setVisibility(View.VISIBLE);

        initData();
        return view;
    }

    @Override
    void initData() {

    }



}
