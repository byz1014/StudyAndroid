package com.example.byz.studyandroid;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.baselib.DialogUtils;
import com.example.baselib.adapter.AdapterOclicLinstener;
import com.example.baselib.adapter.MyViewHolder;
import com.example.baselib.adapter.TotalRecycleAdapter;
import com.example.baselib.base.BaseActivity;
import com.example.baselib.builder.DialogMessage;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byz on 2017/11/6.
 */

public class SmallSkillActivity extends BaseActivity implements View.OnClickListener{
    RecyclerView rv_skill_body;
    String names[]={"loading","Dialog","算法","Imageloder","Glide","Fresco"};
    List<String> list;

    @Override
    public int onLayout() {
        return R.layout.layout_loading;
    }

    @Override
    public void init() {
        Bundle bundle = getIntent().getBundleExtra("data");
        String title = bundle.getString("title");
        setTitleMessage(title);
        setLeftListener(new OnTitleLeftListener() {
            @Override
            public void onLeft() {
                finish();
            }
        });
        setRightListener(new OnTitleRightListener() {
            @Override
            public void onRight() {
                Toast.makeText(getActivity(),"敬请期待",Toast.LENGTH_LONG).show();
            }
        });
        addView();
        rv_skill_body = (RecyclerView)findViewById(R.id.rv_skill_body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_skill_body.setLayoutManager(layoutManager);
        totalRecycleAdapter = new TotalRecycleAdapter(getActivity(),list) {
            @Override
            public int onLayout() {
                return R.layout.layout_skill_item;
            }

            @Override
            protected void convert(MyViewHolder vHolder, Object o, int position) {

                vHolder.setText(R.id.tv_skill_item, list.get(position));
            }
        };
        rv_skill_body.setAdapter(totalRecycleAdapter );
        totalRecycleAdapter.setOnClickItemListener(adapterOclicLinstener);
    }

    TotalRecycleAdapter totalRecycleAdapter ;

    private void addView(){
        list = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            list.add(names[i]);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }

    @Override
    public void onClick(View view) {
    }


    AdapterOclicLinstener adapterOclicLinstener = new AdapterOclicLinstener() {
        @Override
        public void OnClickItemListener(View view, int position) {
            switch (position){
                case 0://loading
                    onLoddingShow(getActivity());
                    break;
                case 1://Dialog
                    DialogMessage dialogMessage =new  DialogMessage.Builder()
                            .message("你给权限了吗？？？？")
                            .left("取消")
                            .right("去设置")
                            .build();
                    DialogUtils.getDialogUtils().AlerIntence(getActivity(),dialogMessage, new DialogUtils.DialogListener() {
                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onNext() {

                        }
                    });
                    break;
                case 2://属性动画
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("title","算法");
                    goActivity(AlgorithmActivity.class,bundle1);

                    break;
                case 3://Imageloder
                case 4://Glide
                case 5://Fresco
                    Bundle bundle = new Bundle();
                    bundle.putString("title","图片页");
                    goActivity(ImageActivity.class,bundle);
                    break;
            }
        }
    };

}
