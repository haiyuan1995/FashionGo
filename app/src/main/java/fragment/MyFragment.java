package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.haiyuan1995.fashiongo.PersonActivity;
import com.example.haiyuan1995.fashiongo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.CollapsingToolbarLayoutState;

/**
 * 我的个人中心
 * **/

public class MyFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.id_user_nickname)
    TextView idUserNickname;
    @BindView(R.id.id_user_phone)
    TextView idUserPhone;
    @BindView(R.id.id_user_picture)
    ImageView idUserPicture;
    @BindView(R.id.id_toolbar_user_picture)
    ImageView idToolbarUserPicture;
    @BindView(R.id.id_toolbar_title)
    TextView idToolbarTitle;
    @BindView(R.id.toolbar_layout)
    LinearLayout toolbarLayout;
    @BindView(R.id.id_toolbar)
    Toolbar idToolbar;
    @BindView(R.id.id_collapsingToolbarLayout)
    CollapsingToolbarLayout idCollapsingToolbarLayout;
    @BindView(R.id.id_appBarLayout)
    AppBarLayout idAppBarLayout;
    @BindView(R.id.id_person_details)
    CardView idPersonDetails;
    @BindView(R.id.id_order)
    CardView idOrder;
    @BindView(R.id.id_address)
    CardView idAddress;
    @BindView(R.id.id_collection)
    CardView idCollection;
    @BindView(R.id.id_share)
    CardView idShare;
    private CollapsingToolbarLayoutState state;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        idToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(idToolbar);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initView() {

        /**
         * 判断verticalOffset的绝对位移量来分辨展开还是缩放
         *
         * 完成CollapsingToolbarLayout设置之后再调用Toolbar的setTitle()等方法将没有效果，
         * 我们需要改为调用CollapsingToolbarLayout的setTitle()等方法来对工具栏进行修改。
         * **/
        idAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        toolbarLayout.setVisibility(View.GONE);
                    }
                } else if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {//折叠的时候显示Toolbar的布局
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        toolbarLayout.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {//由折叠变为中间状态
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {//折叠
                            toolbarLayout.setVisibility(View.VISIBLE);
                        }
                        //中间过渡状态
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });

    }

    @Override
    void initData() {
        idUserPicture.setOnClickListener(this);
        idPersonDetails.setOnClickListener(this);
        idOrder.setOnClickListener(this);
        idAddress.setOnClickListener(this);
        idCollection.setOnClickListener(this);
        idShare.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_user_picture:
                startActivity(new Intent(getActivity(), PersonActivity.class));
                break;
            case R.id.id_person_details:
                startActivity(new Intent(getActivity(), PersonActivity.class));
                break;
            case R.id.id_order:
                break;
            case R.id.id_address:
                break;
            case R.id.id_collection:
                break;
            case R.id.id_share:
                break;

        }
    }
}
