package com.example.byz.studyandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.byz.studyandroid.R;

import java.util.List;

/**
 * Created by byz on 2017/11/6.
 */

public abstract class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context mContext;
    List list;
    public MyAdapter(Context mContext, List list) {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(onLayout(), parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        onVersonUtil(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);

        }
    }

    public abstract void onVersonUtil(Object o);
    public abstract int onLayout();

}
