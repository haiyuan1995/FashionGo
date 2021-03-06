package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haiyuan1995.fashiongo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单
 */

public class OrderFragment extends BaseFragment {

//    @BindView(R.id.id_order_recyclerView)
//    RecyclerView idOrderRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container,false);
        ButterKnife.bind(this, view);
        BaseFragment.initToolbar(toolbar,getActivity());
        initData();
        return view;
    }

    @Override
    void initData() {

    }
}
