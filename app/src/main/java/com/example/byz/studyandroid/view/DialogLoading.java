package com.example.byz.studyandroid.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.byz.studyandroid.R;

/**
 * Created by byz on 2017/11/6.
 */

public class DialogLoading extends AlertDialog {
    private Context context;
    private TextView tv_lodding_message;

    public DialogLoading(@NonNull Context context) {
        super(context,R.style.dialog_me);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lodding);
        tv_lodding_message = (TextView)findViewById(R.id.tv_lodding_message) ;
    }

    public void setMessage(String message){
        if(tv_lodding_message!=null){
            tv_lodding_message.setText(message);
        }
    }

    public void setMessage(int IdRes){
        if(tv_lodding_message!=null){
            tv_lodding_message.setText(IdRes);
        }
    }

    public void show(String message){
        try{
            super.show();
            tv_lodding_message.setText(message);
        }catch (Exception e){

        }
    }

    public void dismiss() {
        try{
            super.dismiss();
        }catch (Exception e){

        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == event.KEYCODE_BACK&&keyCode == event.ACTION_DOWN){
            dismiss();
        }
        return super.onKeyDown(keyCode, event);
    }
}
