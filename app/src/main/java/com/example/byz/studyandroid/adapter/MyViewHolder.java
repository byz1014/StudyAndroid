package com.example.byz.studyandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by byz on 2017/11/13.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    View mView;

    private SparseArray<View> myViews;
    private MyViewHolder(View itemView) {
        super(itemView);
        this.myViews = new SparseArray<View>();
        this.mView = itemView;
    }

    public static MyViewHolder  getMyViewHolder(Context mContext, ViewGroup parent, int viewType, int layout) {
        View view = LayoutInflater.from(mContext).inflate(layout, parent, false);
        return new MyViewHolder(view);
    }

    public TextView getTextView(int id, String text){
        TextView textView = (TextView)mView.findViewById(id);
        textView.setText(text);
        return textView;
    }



    public <T extends View> T getView(int viewId){
        View view = myViews.get(viewId);

        if(view ==null){
            view = mView.findViewById(viewId);
            myViews.put(viewId, view);
        }
        return (T)view;
    }




    public View getConvertView() {
        // TODO Auto-generated method stub
        return mView;
    }

    public MyViewHolder setImageDraw(int viewId,int imgId){
        ImageView mImageView = getView(viewId);
        mImageView.setImageResource(imgId);
        return this;
    }
    public MyViewHolder setImageHeadUrl(int viewId,String url){
        ImageView mImageView = getView(viewId);
        ImageLoader.getInstance().displayImage(url, mImageView);
        return this;
    }

    public MyViewHolder setText(int viewId,String txt){

        TextView mTextView = getView(viewId);

        mTextView.setText(txt);
        return this;
    }

    public MyViewHolder setText(int viewId,CharSequence txt){

        TextView mTextView = getView(viewId);

        mTextView.setText(txt);
        return this;
    }

}