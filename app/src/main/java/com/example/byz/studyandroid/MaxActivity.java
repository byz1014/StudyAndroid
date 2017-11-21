package com.example.byz.studyandroid;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.example.baselib.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by byz on 2017/11/17.
 */

public class MaxActivity extends BaseActivity {
    ImageView iv_max_img;
    String url,type;
    @Override
    public int onLayout() {
        return R.layout.layout_max_image;
    }

    @Override
    public void init() {
        onTitle();
        iv_max_img = (ImageView)findViewById(R.id.iv_max) ;
        url = bundle.getString("url");
        type = bundle.getString("type");
        if(type.equals("gif")){
            Glide.with(getActivity())
                    .load(url)
                    .into(iv_max_img);

        }else{
            Glide.with(getActivity())
                    .load(url)
                    .bitmapTransform(new Transformation<Bitmap>() {
                        @Override
                        public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
                            return null;
                        }

                        @Override
                        public String getId() {
                            return null;
                        }
                    })
                    .into(iv_max_img);
        }


    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }
    Bundle bundle;
    private void onTitle() {
        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });

          bundle = getIntent().getBundleExtra("data");
        setTitleMessage(bundle.getString("title"));

    }

}
