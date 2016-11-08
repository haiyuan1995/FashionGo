package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.haiyuan1995.fashiongo.R;

import java.util.List;

import gsonbean.Recommendation;

/**
 * 推荐商品recyclerview的适配器
 */

public class RecommendationAdapter extends RVBaseAdapter {

    public RecommendationAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        Recommendation.DataBean dataBean= (Recommendation.DataBean) sList.get(position);

        Glide.with(sContext)
                .load(dataBean.getRecommenImage())
                .animate(android.R.anim.slide_in_left)
                .into(myViewHolder.imageView);
    }

    @Override
    public RecommendationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=sLayoutInflater.inflate(R.layout.home_rv_recommendation_item,parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.id_recommendationImage);
        }
    }
}
