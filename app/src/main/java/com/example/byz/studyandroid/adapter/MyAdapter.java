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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context mContext;
    List list;

    public MyAdapter(Context mContext, List list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String item = (String) list.get(position);
        holder.tv_item.setText(item);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tv_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);

        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener OnIte ) {
        this.onItemClickListener = OnIte;
    }

    public interface OnItemClickListener {
        void OnItemClick(View v, int position);
    }

}
