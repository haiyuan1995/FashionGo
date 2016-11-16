package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haiyuan1995.fashiongo.R;

import java.util.List;

import butterknife.BindView;
import gsonbean.ClassifyThree;

/**
 * 三级分类弹窗适配器
 */

public class ClassifyThreeAdapter extends RVBaseAdapter {
    @BindView(R.id.id_classify_one_tv)
    TextView idClassifyOneTv;

    public ClassifyThreeAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = sLayoutInflater.inflate(R.layout.classify_one_rcy_item, parent, false);//重用分类1的item布局
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.tv_name.setText(((ClassifyThree.DataBean)sList.get(position)).getClassifyName());
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        MyViewHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.id_classify_one_tv);
        }
    }
}
