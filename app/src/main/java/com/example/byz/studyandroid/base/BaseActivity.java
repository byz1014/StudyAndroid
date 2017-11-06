package com.example.byz.studyandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byz.studyandroid.R;
import com.example.byz.studyandroid.view.DialogLoading;

/**
 * Created by byz on 2017/11/6.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        init();

    }

    public abstract int onLayout();

    public abstract void init();

    public Activity getActivity() {
        return this;
    }

    public void onToast(String message) {

        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    public void onToastText(String message, Context mContext) {


        // 根据传进来的上下文，创建一个吐司对象
        Toast result = new Toast(mContext);

        // 布局填充器
        LayoutInflater inflate = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 填充系统布局文件，转换为View对象
        View v = inflate.inflate(R.layout.layout_toast, null);

        // 获取TextView文本对象，设置显示的文字
        TextView tv = (TextView) v.findViewById(R.id.tv_toast);
        tv.setText(message);
        result.setView(v);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        result.setGravity(Gravity.CENTER,0,height/3);
        result.setDuration(Toast.LENGTH_LONG);
        result.show();

//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_toast,null);
//        TextView textView = (TextView)view.findViewById(R.id.tv_toast);
//        textView.setText(message);
//        toast.setView(view);
//        toast.setGravity(Gravity.CENTER,100,100);
//        toast.show();
    }

    DialogLoading alertDialog;

    public void onLoddingShow(Context context){
        alertDialog = new DialogLoading(context);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public void onLoddingHint(){
        if(alertDialog!=null){
            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }
        }
    }

}
