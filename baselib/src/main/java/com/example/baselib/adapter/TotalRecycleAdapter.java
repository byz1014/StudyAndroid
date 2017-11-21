package com.example.baselib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by byz on 2017/11/13.
 */

public abstract class TotalRecycleAdapter<T>  extends RecyclerView.Adapter<MyViewHolder>{
    private Context mContext;
    private List<T> list;
    private AdapterOclicLinstener adapterOclicLinstener;


    public TotalRecycleAdapter(Context mContext, List list  ) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(onLayout(), parent, false);
        MyViewHolder myViewHolder = new   MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapterOclicLinstener!=null){
                    adapterOclicLinstener.OnClickItemListener(view,position);
                }
            }
        });

        convert(holder,list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract int onLayout();

    protected abstract void convert(MyViewHolder vHolder, T t, int position);

    public void setOnClickItemListener(AdapterOclicLinstener mAdapterOclicLinstener){
        this.adapterOclicLinstener =  mAdapterOclicLinstener;
    }

}
