package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by haiyuan on 2016/11/15.
 */

public class ClassifyOneAdapter extends RVBaseAdapter {
    public ClassifyOneAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

}