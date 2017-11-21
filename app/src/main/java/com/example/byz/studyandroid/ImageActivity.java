package com.example.byz.studyandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.example.baselib.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by byz on 2017/11/17.
 */

public class ImageActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_test_img,iv_test_img1,iv_test_img2;
    private TextView tv_load,tv_load_1,tv_load_2;

    private String names[] = {
            "引入Fresco以及相关注意事项.",
            "PlaceHolderImage占位图",
            "FailureImage加载失败时显示的图片",
            "RetryImage重新加载的图片",
            "ProgressBarImage加载时显示的进度图片",
            "BackgroundImage背景图",
            "OverlayImage叠加图",
            "多种效果结合加载图片",
            "圆形头像，圆角头像以及背景叠加",
            "图像边框"};



    @Override
    public int onLayout() {
        return R.layout.layout_image;
    }

    @Override
    public void init() {
        onTitle();
        Glide.with(getActivity());// 绑定Context
//        Glide.with(Activity activity);// 绑定Activity
//        Glide.with(FragmentActivity activity);// 绑定FragmentActivity
//        Glide.with(Fragment fragment);// 绑定Fragment
        iv_test_img = (ImageView) findViewById(R.id.iv_test_img);
        iv_test_img1 = (ImageView) findViewById(R.id.iv_test_img1);
        iv_test_img2 = (ImageView) findViewById(R.id.iv_test_img2);
        tv_load = (TextView)findViewById(R.id.tv_load);
        tv_load_1 = (TextView)findViewById(R.id.tv_load_1);
        tv_load_2 = (TextView)findViewById(R.id.tv_load_2);
        tv_load.setOnClickListener(this);
        tv_load_1.setOnClickListener(this);
        tv_load_2.setOnClickListener(this);
        iv_test_img.setOnClickListener(this);
        iv_test_img2.setOnClickListener(this);
        iv_test_img1.setOnClickListener(this);

    }





    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_load:
                Glide.with(getActivity()).load("http://img1.imgtn.bdimg.com/it/u=410955806,4164577389&fm=27&gp=0.jpg").into(iv_test_img);
                break;
            case R.id.tv_load_1:
                Glide.with(getActivity()).load("http://p0.ifengimg.com/pmop/2017/0629/E03E2C56AEABAE863BBFB7C65A6138C53C3CA21C_size761_w480_h282.gif").into(iv_test_img1);
                break;
            case R.id.tv_load_2:
                DrawableTypeRequest<String> stringDrawableTypeRequest =
                Glide.with(getActivity()).load("http://img1.imgtn.bdimg.com/it/u=410955806,4164577389&fm=27&gp=0.jpg");
                Transformation<Bitmap> transformation = new Transformation<Bitmap>() {
                    @Override
                    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
                       BitmapPool bitmapPool = Glide.get(getActivity()).getBitmapPool();
                        Bitmap source = resource.get();
                        int width = source.getWidth();
                        int height = source.getHeight();
                        Bitmap result = bitmapPool.get(width, height, Bitmap.Config.ARGB_8888);
                        if (result == null) {
                            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                        }
                        Canvas canvas = new Canvas(result);
                        //以上已经算是教科书式写法了
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                        canvas.drawRoundRect(new RectF(0, 0, width, height), Integer.MAX_VALUE,  Integer.MAX_VALUE, paint);


                        return  BitmapResource.obtain(result, bitmapPool);
                    }

                    @Override
                    public String getId() {
                        return "RoundedTransformation(radius=" + Integer.MAX_VALUE + ")";
                    }
                };
                stringDrawableTypeRequest.bitmapTransform(transformation );
                stringDrawableTypeRequest.into(iv_test_img2);

                break;
            case R.id.iv_test_img:
                Bundle bundle = new Bundle();
                bundle.putString("title","大图片");
                bundle.putString("url","http://img1.imgtn.bdimg.com/it/u=410955806,4164577389&fm=27&gp=0.jpg");
                bundle.putString("type","jpg");
                goActivity(MaxActivity.class,bundle);
                break;
            case R.id.iv_test_img1:
                Bundle bundle1 = new Bundle();
                bundle1.putString("title","大图片");
                bundle1.putString("url","http://p0.ifengimg.com/pmop/2017/0629/E03E2C56AEABAE863BBFB7C65A6138C53C3CA21C_size761_w480_h282.gif");
                bundle1.putString("type","gif");
                goActivity(MaxActivity.class,bundle1);
                break;
            case R.id.iv_test_img2:
                Bundle bundle2 = new Bundle();
                bundle2.putString("title","大图片");
                bundle2.putString("url","http://img1.imgtn.bdimg.com/it/u=410955806,4164577389&fm=27&gp=0.jpg");
                bundle2.putString("type","jpg");
                goActivity(MaxActivity.class,bundle2);
                break;
        }
    }

    private void onTitle() {
        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });

        Bundle bundle = getIntent().getBundleExtra("data");
        setTitleMessage(bundle.getString("title"));

    }



}


