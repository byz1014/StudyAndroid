package com.example.byz.studyandroid.utils;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byz.studyandroid.R;

/**
 * Created by byz on 2017/11/6.
 */

public class DialogUtils implements View.OnClickListener {
    private static DialogUtils dialogUtils;

    private DialogUtils() {
    }

    public static DialogUtils getDialogUtils() {
        if (dialogUtils == null) {
            synchronized (DialogUtils.class) {
                if (dialogUtils == null) {
                    dialogUtils = new DialogUtils();
                }
            }
        }
        return dialogUtils;
    }

    TextView tv_yes, tv_no;
    LinearLayout ll_body;
    Dialog dialog;
    Context mContext;

    public void AlerIntence(Context mContext) {
        this.mContext = mContext;
        dialog = new Dialog(mContext, R.style.Godinsec_Diglog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Window window = dialog.getWindow();
        window.setContentView(R.layout.layout_dialog);
        ll_body = (LinearLayout)window.findViewById(R.id.ll_body);
        tv_no = (TextView) window.findViewById(R.id.tv_no);
        tv_yes = (TextView) window.findViewById(R.id.tv_yes);
        ObjectAnimator.ofFloat(ll_body, "rotation", 0, 3,0,-3,0 ).setDuration(300).start();
        tv_yes.setOnClickListener(this);
        tv_no.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_no:
                dialog.dismiss();
                break;
            case R.id.tv_yes:
                Toast.makeText(mContext, "yes", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                break;
        }
    }
}
