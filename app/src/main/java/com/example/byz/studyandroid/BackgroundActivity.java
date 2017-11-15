package com.example.byz.studyandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.baselib.base.BaseActivity;
import com.example.byz.studyandroid.utils.BitmapUtil;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by byz on 2017/11/8.
 * //模糊效果
 *
 */

public class BackgroundActivity extends BaseActivity implements View.OnClickListener{
    TextView test;
    ImageView iv_back;
    SeekBar sb_bar;

    @Override
    public int onLayout() {
        return R.layout.layout_back_round;
    }
    int progress;
    @Override
    public void init() {
        test = (TextView) findViewById(R.id.test);
        iv_back = (ImageView)findViewById(R.id.iv_back) ;
        sb_bar = (SeekBar)findViewById(R.id.sb_bar);
        test.setOnClickListener(this);
        image = BitmapFactory.decodeResource(getResources(),R.mipmap.back_test2);
        Bundle bundle = getIntent().getBundleExtra("data");
        String title = bundle.getString("title");
        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });
        setTitleMessage(title);
        sb_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i<=1){
                    i = 1;
                }
                progress = i;
                Message message = new Message();
                message.arg1 = progress;
                handler.sendMessage(message);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    Bitmap image;
    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test:

                break;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            iv_back.setImageBitmap( BitmapUtil.getBitmapUtil().fastblur(getActivity(),image,msg.arg1));
        }
    };

}

