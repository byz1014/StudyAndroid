package com.example.byz.studyandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baselib.DialogUtils;
import com.example.baselib.builder.DialogMessage;
import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.utils.QRCodeCreateUtil;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by byz on 2017/11/10.
 */

public class PasswordIocnActivity extends BaseActivity implements View.OnClickListener{
    TextView tv_ping, tv_sao_1_sao;
    ImageView iv_xing;
    EditText et_info;

    @Override
    public int onLayout() {
        return R.layout.layout_pswd;
    }

    @Override
    public void init() {
        tv_ping = (TextView) findViewById(R.id.tv_ping);
        iv_xing = (ImageView) findViewById(R.id.iv_xing);
        tv_sao_1_sao = (TextView) findViewById(R.id.tv_sao_1_sao);
        et_info = (EditText) findViewById(R.id.et_info);
        tv_ping.setOnClickListener(this);
        tv_sao_1_sao.setOnClickListener(this);
        Bundle bundle = getIntent().getBundleExtra("data");
        String title = bundle.getString("title");
        setTitleMessage(title);
        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });
//        new IntentIntegrator(this).initiateScan();
        iv_xing.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DialogMessage dialogMessage = new DialogMessage.Builder()
                        .left("copy")
                        .right("cancel")
                        .message("Are you sure ?  copy!!!")
                        .build();
                DialogUtils.getDialogUtils().AlerIntence(getActivity(), dialogMessage, new DialogUtils.DialogListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onNext() {
                        savePic2Phone(getActivity(), iv_xing.getDrawingCache());
                        iv_xing.setDrawingCacheEnabled(false);
                    }
                });
                return true;
            }
        });


    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ping:
                if (et_info.getText().toString().trim().equals("")) {
                    onToastText("Empty content ?", getActivity());
                }
                iv_xing.setDrawingCacheEnabled(true);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
                iv_xing.setImageBitmap(QRCodeCreateUtil.createHaveLogoQRCode(et_info.getText().toString(), 600, 600, bitmap));
                break;
            case R.id.tv_sao_1_sao:
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setPrompt("请扫描"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
                integrator.setCaptureActivity(ScanActivity.class);
                integrator.initiateScan();
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

//        result.

        if (result != null) {
            if (result.getContents() == null) {
                Log.e("byz", "Cancelled");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.e("byz", "Scanned: " + result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        }


    }


    /**
     * 处理扫描结果
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
//        inactivityTimer.onActivity();
//        playBeepSoundAndVibrate();
        String resultString = result.getText();
        if (resultString.equals("")) {
            Toast.makeText(getActivity(), "Scan failed!", Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", resultString);
            bundle.putParcelable("bitmap", barcode);
            resultIntent.putExtras(bundle);
            this.setResult(RESULT_OK, resultIntent);
        }
    }


    private void savePic2Phone(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "dsh");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        onToastText("保存成功", getActivity());
    }

}
