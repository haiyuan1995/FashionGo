package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haiyuan1995.fashiongo.R;

import java.util.List;

import gsonbean.GoodsADInfo;

/**
 * 热门商品推荐
 */

public class ThreeGoodsADInfoAdapter extends RVBaseAdapter {
    private Context mContext;
    private List<GoodsADInfo.DataBean> mGoodsADInfo;
    private LayoutInflater mLayoutInflater;

    public ThreeGoodsADInfoAdapter(Context context, List<GoodsADInfo.DataBean> goodsADInfo) {
        super(context,goodsADInfo);
        this.mContext=context;
        this.mGoodsADInfo=goodsADInfo;
        this.mLayoutInflater=LayoutInflater.from(context);
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        MyViewHolder myViewHolder= (MyViewHolder) holder;

        if (position==mGoodsADInfo.size()-1) {//position为第4个的时候
            myViewHolder.tv_title.setText("更多");
            myViewHolder.tv_context.setText("More");


            Glide.with(mContext)
                    .load(R.drawable.ic_goodsinfo_more)
                    .crossFade()
                    .into(myViewHolder.iv_goods);
        }else{
            myViewHolder.tv_title.setText("商品:"+position);
            myViewHolder.tv_context.setText("商品内容:"+position);

            Glide.with(mContext)
                    .load(mGoodsADInfo.get(position).getADImage())
                    .crossFade()
                    .into(myViewHolder.iv_goods);
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.home_rv_threegoodsinfo_item,parent,false);
        return new MyViewHolder(view);
    }


    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private TextView tv_context;
        private ImageView iv_goods;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.id_title);
            tv_context= (TextView) itemView.findViewById(R.id.id_context);
            iv_goods= (ImageView) itemView.findViewById(R.id.id_goodsImage);
        }
    }
}
