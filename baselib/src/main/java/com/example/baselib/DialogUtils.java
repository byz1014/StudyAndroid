package com.example.baselib;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baselib.builder.DialogMessage;


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

    TextView tv_yes, tv_no,tv_message;
    LinearLayout ll_body;
    Dialog dialog;
    Context mContext;

    public void AlerIntence(Context mContext, DialogMessage dialogMessage, DialogListener dialogListener) {
        this.dialogListener = dialogListener;

        this.mContext = mContext;
        dialog = new Dialog(mContext, R.style.Godinsec_Diglog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(R.layout.layout_dialog);
        ll_body = (LinearLayout)window.findViewById(R.id.ll_body);
        tv_no = (TextView) window.findViewById(R.id.tv_no);
        tv_yes = (TextView) window.findViewById(R.id.tv_yes);
        tv_message = (TextView)window.findViewById(R.id.tv_message);
        if(dialogMessage.getLeft()!=null&&!dialogMessage.getLeft().equals("")){
            tv_yes.setText(dialogMessage.getLeft());
            tv_yes.setVisibility(View.VISIBLE);
        }else{
            tv_yes.setVisibility(View.GONE);
        }

        if(dialogMessage.getRight()!=null&&!dialogMessage.getRight().equals("")){
            tv_no.setText(dialogMessage.getRight());
            tv_no.setVisibility(View.VISIBLE);
        }else{
            tv_no.setVisibility(View.GONE);
        }
        if(dialogMessage.getMessage()!=null&&!dialogMessage.getMessage().equals("")){
            tv_message.setText(dialogMessage.getMessage());
            tv_message.setVisibility(View.VISIBLE);
        }else{
            tv_message.setVisibility(View.GONE);
        }


        ObjectAnimator.ofFloat(ll_body, "rotation", 0, 3,0,-3,0 ).setDuration(300).start();
        tv_yes.setOnClickListener(this);
        tv_no.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tv_no){
            dialogListener.onCancel();
            dialog.dismiss();
        }else if(view.getId() == R.id.tv_yes){
            dialogListener.onNext();
            Toast.makeText(mContext, "yes", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

    }
private DialogListener dialogListener;

    /**
     * 权限提示
     * @param mContext
     */
   public void onSetting(final Activity mContext,DialogListener dialogListener){
       this.dialogListener = dialogListener;
       AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
       builder.setTitle("提示");
       builder.setMessage("当前应用缺少必要权限。\\n\\n请点击\\\"设置\\\"-\\\"权限\\\"-打开所需权限。");
       // 拒绝, 退出应用
       builder.setNegativeButton("取消",
               new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                   }
               });

       builder.setPositiveButton("设置",
               new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
       builder.setCancelable(false);
       builder.show();

   }


   public interface  DialogListener{
       void onCancel();
       void onNext();

   }


}
