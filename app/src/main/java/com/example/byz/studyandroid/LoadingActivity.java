package com.example.byz.studyandroid;

import android.view.View;
import android.widget.TextView;

import com.example.byz.studyandroid.base.BaseActivity;
import com.example.byz.studyandroid.utils.DialogUtils;

/**
 * Created by byz on 2017/11/6.
 */

public class LoadingActivity extends BaseActivity implements View.OnClickListener{
    TextView tv_loading,tv_dialog_yes_or_no;

    @Override
    public int onLayout() {
        return R.layout.layout_loading;
    }

    @Override
    public void init() {
        tv_loading = (TextView)findViewById(R.id.tv_loading);
        tv_dialog_yes_or_no = (TextView)findViewById(R.id.tv_dialog_yes_or_no);
        tv_loading.setOnClickListener(this);
        tv_dialog_yes_or_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_dialog_yes_or_no:
                DialogUtils.getDialogUtils().AlerIntence(getActivity());
                break;
            case R.id.tv_loading:
                onLoddingShow(getActivity());
                break;
        }
    }
}
