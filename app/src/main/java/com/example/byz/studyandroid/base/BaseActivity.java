package com.example.byz.studyandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byz.studyandroid.R;
import com.example.byz.studyandroid.view.DialogLoading;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by byz on 2017/11/6.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        EventBus.getDefault().register(this);
        initViewActionBar();
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
        result.setGravity(Gravity.CENTER, 0, height / 3);
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

    public void onLoddingShow(Context context) {
        alertDialog = new DialogLoading(context);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public void onLoddingHint() {
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        }
    }

    @Subscribe
    public abstract void onEventMainThread(String str);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public View mCustomView;
    public ActionBar mActionBar;
    private ImageView iv_left, iv_right;
    private TextView tv_title;
    private LinearLayout ll_body_title;

    public void initViewActionBar() {
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View viewTitleBar = getLayoutInflater().inflate(
                R.layout.layout_title, null);
        getSupportActionBar().setElevation(0);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setCustomView(viewTitleBar, lp);
        Toolbar parent = (Toolbar) viewTitleBar.getParent();
        parent.setContentInsetsAbsolute(0, 0);
        mCustomView = mActionBar.getCustomView();
        iv_left = (ImageView) mCustomView.findViewById(R.id.iv_left);
        iv_right = (ImageView) mCustomView.findViewById(R.id.iv_right);
        tv_title = (TextView) mCustomView.findViewById(R.id.tv_title);
        ll_body_title = (LinearLayout) mCustomView.findViewById(R.id.ll_body_title);
        iv_left.setOnClickListener(onClickListener);
        iv_right.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_left:
                   if(onTitleLeftListener !=null){
                       onTitleLeftListener.onLeft();
                   }
                    break;
                case R.id.iv_right:
                    if(onTitleRightListener !=null){
                        onTitleRightListener.onRight();
                    }
                    break;
            }
        }
    };


    public void TitleHint(){
        mActionBar.hide();
    }
    public void TitleShow(){
        mActionBar.show();
    }

    public void setBackground(int ground) {
        ll_body_title.setBackgroundResource(ground);
    }

    OnTitleLeftListener onTitleLeftListener;
    OnTitleRightListener onTitleRightListener;

    public void setLeftListener(OnTitleLeftListener leftListener) {
        this.onTitleLeftListener = leftListener;
    }

    public void setRightListener(OnTitleRightListener rightListener) {
        this.onTitleRightListener = rightListener;
    }

     public ImageView getLeftView(){
         return iv_left;
     }

     public ImageView getRightView(){
         return iv_right;
     }

    public interface OnTitleLeftListener {
        void onLeft();
    }

    public interface OnTitleRightListener {
        void onRight();
    }

}
