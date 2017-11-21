package com.example.byz.studyandroid;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.baselib.base.BaseActivity;
import com.example.byz.studyandroid.bean.NumBean;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byz on 2017/11/20.
 */

public class AlgorithmActivity extends BaseActivity {
    private EditText et_num, et_num_2, et_num_3_2, et_num_3_1, et_num_4, et_num_6, et_num_7;
    private TextView tv_check, tv_return, tv_return_2, tv_show_2, tv_max_num, tv_min_num,
            tv_calculator, tv_suan_4, tv_result_4, tv_suan_5, tv_result_5, tv_return_6, tv_test_6, tv_play_7, tv_result_7;


    @Override
    public int onLayout() {
        return R.layout.layout_algorithm;
    }

    @Override
    public void init() {
        et_num = (EditText) findViewById(R.id.et_num);
        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_return = (TextView) findViewById(R.id.tv_return);
        et_num_2 = (EditText) findViewById(R.id.et_num_2);
        tv_return_2 = (TextView) findViewById(R.id.tv_return_2);
        tv_show_2 = (TextView) findViewById(R.id.tv_show_2);
        et_num_3_2 = (EditText) findViewById(R.id.et_num_3_2);
        et_num_3_1 = (EditText) findViewById(R.id.et_num_3_1);
        tv_max_num = (TextView) findViewById(R.id.tv_max_num);
        tv_min_num = (TextView) findViewById(R.id.tv_min_num);
        et_num_4 = (EditText) findViewById(R.id.et_num_4);
        tv_suan_4 = (TextView) findViewById(R.id.tv_suan_4);
        tv_result_4 = (TextView) findViewById(R.id.tv_result_4);
        tv_suan_5 = (TextView) findViewById(R.id.tv_suan_5);
        tv_result_5 = (TextView) findViewById(R.id.tv_result_5);
        tv_return_6 = (TextView) findViewById(R.id.tv_return_6);
        tv_test_6 = (TextView) findViewById(R.id.tv_test_6);
        et_num_6 = (EditText) findViewById(R.id.et_num_6);
        et_num_7 = (EditText) findViewById(R.id.et_num_7);
        tv_play_7 = (TextView) findViewById(R.id.tv_play_7);
        tv_result_7 = (TextView) findViewById(R.id.tv_result_7);


        tv_calculator = (TextView) findViewById(R.id.tv_calculator);
        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_num.getText().toString().trim().equals("")) {
                    return;
                }
                text(Integer.parseInt(et_num.getText().toString().trim()));
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    sb.append(list.get(i) + "  ");
                }
                tv_return.setText(sb.toString());

            }
        });

        tv_show_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_num_2.getText().toString().trim().equals("")) {
                    return;
                }
                tv_return_2.setText(onProblem_2(Integer.parseInt(et_num_2.getText().toString().trim())));
            }
        });


        tv_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_num_3_1.getText().toString().trim().equals("")) {
                    return;
                }
                onChangeGreatestAndLest(Integer.parseInt(et_num_3_1.getText().toString().trim()), Integer.parseInt(et_num_3_2.getText().toString().trim()));
            }
        });
        tv_suan_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_num_4.getText().toString().trim().equals("")) {
                    return;
                }
                onFenjie(Integer.parseInt(et_num_4.getText().toString().trim()));
                String str = "";
                for (int i = 0; i < list4.size(); i++) {
                    str += list4.get(i) + "*";
                }
                tv_result_4.setText(et_num_4.getText().toString() + " = " + str.substring(0, str.length() - 1));
            }
        });

        tv_suan_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onYingbi(100);
                String message = list5.size() + "\n";

                for (int i = 0; i < list5.size(); i++) {
                    if (i == 0) {
                        message += list5.get(i).getA() + "," + list5.get(i).getB() + "," + list5.get(i).getC() + "    ";
                    } else {
                        if (i % 3 == 0) {
                            message += list5.get(i).getA() + "," + list5.get(i).getB() + "," + list5.get(i).getC() + "\n";
                        } else {
                            message += list5.get(i).getA() + "," + list5.get(i).getB() + "," + list5.get(i).getC() + "    ";
                        }
                    }

                }
                tv_result_5.setText(message);
            }
        });

        tv_test_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_num_6.getText().toString().trim().equals("")) {
                    return;
                }
                tv_return_6.setText("");
                Math_6(Integer.parseInt(et_num_6.getText().toString().trim()));
                String message = "";

                if (list_6.size() == 0) {
                    tv_return_6.setText("NONE");
                    return;
                }

                for (int i = 0; i < list_6.size(); i++) {
                    if (i != 0) {
                        if (i % 3 == 0) {
                            message += "   " + list_6.get(i) + "\n";
                        } else {
                            message += "   " + list_6.get(i);
                        }

                    } else {
                        message += "   " + list_6.get(i);
                    }
                }

                tv_return_6.setText(message);
            }
        });

        tv_play_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_num_7.getText().toString().trim().equals("")) {
                    return;
                }
                tv_result_7.setText(onProblem_7(Integer.parseInt(et_num_7.getText().toString().trim())));

                for (int i = 0; i < list_7.size(); i++) {
                    Log.e("byz1121", list_7.get(i) + "---------OJMO");
                }

            }
        });
    }


    @Override
    @Subscribe
    public void onEventMainThread(String str) {

    }

    List<Integer> list = new ArrayList<>();

    private int selectZHISHU(int num) {
        int a = 1;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                a = 0;
                break;
            }
        }
        return a;
    }

    private void text(int index) {
        list.clear();
        for (int i = 2; i <= index; i++) {
            if (selectZHISHU(i) == 1) {
                list.add(i);
            }
        }
    }


    private String onProblem_2(int num) {
        String message = "";
        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) {
                continue;
            }
            String str = "";
            for (int a = num; a > i; a -= 2) {
                if (a == num) {
                    str += "    ";
                } else {
                    str += "   ";
                }

            }

            for (int q = 0; q < i; q++) {

                str += " *";
            }
            message += str + "\n";
        }

        return message;
    }


    private int yueshu = 0;
    private int beishu = 0;

    private void onChangeGreatestAndLest(int first, int second) {

        for (int i = 1; i <= first; i++) {
            if (first % i != 0) {
                continue;
            }

            for (int j = 1; j <= second; j++) {
                if (second % j != 0) {
                    continue;
                }
                if (j == i) {
                    yueshu = j;
                }
            }
        }

        for (int b = 1; b < Integer.MAX_VALUE; b++) {

            if (b % first == 0 && b % second == 0) {
                beishu = b;
                break;
            }
        }
        tv_max_num.setText(yueshu + "");
        tv_min_num.setText(beishu + "");
    }

    List<Integer> list4 = new ArrayList<>();

    private void onFenjie(int index) {
        list4.clear();
        text(index);
        Log.e("byz", index + "----" + selectZHISHU(index));
        while (selectZHISHU(index) == 0) {
            for (int i = 0; i < list.size(); i++) {
                if (index % list.get(i) == 0) {
                    index = index / list.get(i);
                    list4.add(list.get(i));
                    break;
                }
            }
        }
        list4.add(index);

    }

    List<NumBean> list5 = new ArrayList<>();

    private void onYingbi(int num) {
        for (int a = 1; a < num / 5; a++) {
            for (int b = 1; b < (num - a) / 2; b++) {
                for (int c = 1; c < (num - b); c++) {
                    int index = a * 5 + b * 2 + c;
                    if (index == 100) {
                        list5.add(new NumBean(a, b, c));
                    }
                }
            }
        }
    }

    private String Yanghui() {
        String message = "";
        int n = 10;
        int num[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            num[i][0] = num[i][i] = 1;
            for (int j = 1; j < i; j++) {
                num[i][j] = num[i - 1][j - 1] + num[i - 1][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                message += num[i][j] + " ";
            }//
            message += message + "\n";
        }
        return message;
    }

    List<String> list_6 = new ArrayList<>();
    List<Integer> list_item_6 = new ArrayList<>();

    private void Math_6(int index) {
        list_6.clear();
        int sum = 0;
        for (int i = 1; i < index; i++) {
            sum = 0;
            list_item_6.clear();
            for (int a = i; a < index; a++) {
                sum += a;
                list_item_6.add(a);
                if (sum == index) {
                    String str = "";
                    for (int v = 0; v < list_item_6.size(); v++) {
                        str += list_item_6.get(v) + " ";
                    }

                    list_6.add(str);
                }
            }
        }
    }

    String names[] = {"A", "B", "C"};
    List<Integer> list_7 = new ArrayList<>();

    private String onProblem_7(int sum) {
        int num = 0;
        int index = 0;
        list_7 .clear();
        while (true){
            index ++;
            if(index > sum){
                index = 1;
            }
         if(onCleck(index)){
             continue;
         }


            num ++ ;
            if(num % 3 == 0){
                list_7.add(index);
            }




            if(list_7.size() == (sum - 1)){
                break;
            }

        }


        return index +"";
    }

    private boolean onCleck(int num) {
        boolean state = false;
        for (int a = 0; a < list_7.size(); a++) {
            if (list_7.get(a) == num) {
                state = true;
                break;
            }
        }
        return state;
    }


}
