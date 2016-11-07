package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * RecyclerView的基础Adapter
 */

public abstract class RVBaseAdapter<T> extends RecyclerView.Adapter{
    public Context sContext;
    private RecyclerViewItemOnClick recyclerViewItemOnClick;
    public List<T> sList;
    public LayoutInflater sLayoutInflater;

    public RVBaseAdapter(Context mContext, List<T> list) {
        this.sContext = mContext;
        this.sList = list;
        this.sLayoutInflater=LayoutInflater.from(mContext);
    }

    public interface RecyclerViewItemOnClick{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }

    @Override
    public int getItemCount() {
        return sList==null?0:sList.size();
    }

    public void setRecyclerViewItemOnClick(RecyclerViewItemOnClick listener){
        this.recyclerViewItemOnClick=listener;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (recyclerViewItemOnClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewItemOnClick.onItemClick(v,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewItemOnClick.onItemLongClick(v,position);
                    return true;//返回true代表事件结束
                }
            });
        }
    }
}
