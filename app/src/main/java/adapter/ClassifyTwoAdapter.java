package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haiyuan1995.fashiongo.R;

import java.util.List;

import gsonbean.ClassifyTwo;

/**
 * 二级分类recyclerView的适配器
 */

public class ClassifyTwoAdapter extends RVBaseAdapter {
    public ClassifyTwoAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = sLayoutInflater.inflate(R.layout.classify_two_rcy_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);//父类方法中实现了点击事件
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        ClassifyTwo.DataBean dataBean= (ClassifyTwo.DataBean) sList.get(position);
         myViewHolder.tv_goodsName.setText(dataBean.getClassifyName());
        Glide.with(sContext).load(dataBean.getClassifyImage()).crossFade().into(myViewHolder.iv_goodsImage);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_goodsImage;
        private TextView tv_goodsName;
        MyViewHolder(View itemView) {
            super(itemView);
            iv_goodsImage= (ImageView) itemView.findViewById(R.id.id_classify_tow_goodsImage);
            tv_goodsName= (TextView) itemView.findViewById(R.id.id_classify_tow_goodsName);
        }
    }
}
