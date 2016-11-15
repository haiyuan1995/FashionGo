package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haiyuan1995.fashiongo.R;

import java.util.List;

import gsonbean.ClassifyOne;

/**
 * 一级分类recyclerView的适配器
 */

public class ClassifyOneAdapter extends RVBaseAdapter {
    public ClassifyOneAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=sLayoutInflater.inflate(R.layout.classify_one_rcy_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        ClassifyOne.DataBean dataBean= (ClassifyOne.DataBean) sList.get(position);
        myViewHolder.tv_classifyName.setText(dataBean.getClassifyName());
    }



    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_classifyName;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_classifyName= (TextView) itemView.findViewById(R.id.id_classify_one_tv);
        }
    }

}