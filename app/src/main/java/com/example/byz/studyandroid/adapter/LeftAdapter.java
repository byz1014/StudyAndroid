package com.example.byz.studyandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.byz.studyandroid.R;
import com.example.byz.studyandroid.bean.LeftInfo;

import java.util.List;

/**
 * Created by byz on 2017/11/10.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyHolder> {
    private List<LeftInfo> list;
    private Context mContext;

    public LeftAdapter(List<LeftInfo> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_left_item,parent,false);
        MyHolder myHolder = new MyHolder(view);
        myHolder.iv_item_logo = (ImageView)view.findViewById(R.id.iv_item_logo);
        myHolder.tv_item_message = (TextView)view.findViewById(R.id.tv_item_message);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        LeftInfo leftInfo = list.get(position);
        holder.iv_item_logo.setImageResource(leftInfo.getResources());
        holder.tv_item_message.setText(leftInfo.getMessage());
        if(onItemClickListener != null){
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnClickListener(position);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        public ImageView iv_item_logo;
        public TextView tv_item_message;
        public View view;
        public MyHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void OnClickListener(int index);
    }


}
