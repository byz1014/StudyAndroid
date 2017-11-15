package com.example.baselib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by byz on 2017/11/13.
 */

public abstract class TotalRecycleAdapter<T>  extends RecyclerView.Adapter<MyViewHolder>{
    private Context mContext;
    private List<T> list;

    public TotalRecycleAdapter(Context mContext, List list  ) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = MyViewHolder.getMyViewHolder(mContext,parent,viewType,onLayout());
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        convert(holder,list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract int onLayout();

    protected abstract void convert(MyViewHolder vHolder, T t, int position);
}
