package fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haiyuan1995.fashiongo.AppUrl;
import com.example.haiyuan1995.fashiongo.LoginActivity;
import com.example.haiyuan1995.fashiongo.PersonActivity;
import com.example.haiyuan1995.fashiongo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import gsonbean.PersonInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.CollapsingToolbarLayoutState;
import utils.RoundImageUtils;

/**
 * 我的个人中心
 * **/

public class MyFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.id_user_nickname)
    TextView idUserNickname;
    @BindView(R.id.id_user_email)
    TextView idUserEmail;
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
    private String accessToken;
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
        initEvent();
        initData();
    }

    private void initEvent() {
        idUserPicture.setOnClickListener(this);
        idPersonDetails.setOnClickListener(this);
        idOrder.setOnClickListener(this);
        idAddress.setOnClickListener(this);
        idCollection.setOnClickListener(this);
        idShare.setOnClickListener(this);

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
        final SharedPreferences preferences=getActivity().getSharedPreferences("config",
                Context.MODE_PRIVATE);
        accessToken =preferences.getString("accessToken","");
        if (!TextUtils.isEmpty(accessToken)){
            PersonActivity.PersonalDetailsService service= AppUrl.retrofit.create(PersonActivity.PersonalDetailsService.class);
            Call<PersonInfo> call=service.getUserInfo(accessToken);
            call.enqueue(new Callback<PersonInfo>() {
                @Override
                public void onResponse(Call<PersonInfo> call, Response<PersonInfo> response) {

                    if (response.body().getData() == null || response.body().getMessage().equals("你的帐号已在其它终端登录，请重新登录")) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        alert.setTitle("通知")
                                .setMessage(response.body().getMessage())
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //清空旧的accessToken
                                        preferences.edit().remove("accessToken").apply();
                                        startActivity(new Intent(getActivity(), LoginActivity.class));
                                    }
                                })
                                .setCancelable(false)
                                .create().show();
                    } else {

                        PersonInfo.DataBean databean = response.body().getData();
                        if (!TextUtils.isEmpty(databean.getImage())) {
                            Glide.with(getActivity()).load(databean.getImage()).asBitmap().centerCrop()
                                    .into(RoundImageUtils.getRoundedImageView(idUserPicture, getContext()));

                            Glide.with(getActivity()).load(databean.getImage()).asBitmap().centerCrop()
                                    .into(RoundImageUtils.getRoundedImageView(idToolbarUserPicture, getContext()));
                        }
                        idUserNickname.setText(databean.getName());
                        idUserEmail.setText(databean.getEmail());
                    }
                }

                @Override
                public void onFailure(Call<PersonInfo> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

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
