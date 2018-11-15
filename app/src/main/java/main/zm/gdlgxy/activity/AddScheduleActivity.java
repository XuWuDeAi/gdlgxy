package main.zm.gdlgxy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.blankj.rxbus.RxBus;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import main.zm.gdlgxy.R;
import main.zm.gdlgxy.dialog.AddPosition;
import main.zm.gdlgxy.dialog.AddWeekDialog;
import main.zm.gdlgxy.dialog.AddtimeDialog;


public class AddScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        LinearLayout ll_position = findViewById(R.id.ll_position);
        final TextView tv_position = findViewById(R.id.tv_position);
        final MaterialEditText et_context = findViewById(R.id.et_context);
        final MaterialEditText et_location = findViewById(R.id.et_location);
        final TextView tv_week = findViewById(R.id.tv_week);
        final TextView tv_time = findViewById(R.id.tv_time);
        Button bt_add = findViewById(R.id.bt_add);


        ll_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPosition dlg = new AddPosition();
                dlg.listener = new AddPosition.OnDialogListener() {
                    @Override
                    public void onDialogInput(String option) {
                        tv_position.setText(option);
                    }
                };
                dlg.show(getSupportFragmentManager(), "hh");
            }
        });

        LinearLayout ll_week = findViewById(R.id.ll_week);
        ll_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWeekDialog dlg = new AddWeekDialog();
                dlg.listener = new AddWeekDialog.OnDialogListener() {
                    @Override
                    public void onDialogInput(String option) {
                        tv_week.setText(option);
                    }
                };
                dlg.show(getSupportFragmentManager(), "hh");
            }
        });

        LinearLayout ll_time = findViewById(R.id.ll_time);
        ll_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddtimeDialog dlg = new AddtimeDialog();
                dlg.listener = new AddtimeDialog.OnDialogListener() {
                    @Override
                    public void onDialogInput(String option, String option2) {
                        String value = option + ":" + option2;
                        tv_time.setText(value);
                    }
                };
                dlg.show(getSupportFragmentManager(), "hh");
            }
        });
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 取得用户输入
                // 返回结果给调用者

                String context = et_context.getText().toString();
                String position = tv_position.getText().toString();
                String week = tv_week.getText().toString();
                String location = "@" + et_location.getText().toString();
                String time = tv_time.getText().toString();

                if (context.length() > 15) {
                    toast("日程内容超过长度15");
                    return;
                }
                if (context.length() <= 0) {
                    toast("日程内容不能为空");
                    return;
                }
                if (location.length() > 6) {
                    toast("地点长度超过5");
                    return;
                }
                if (location.length() <= 0) {
                    toast("地点内容不能为空");
                    return;
                }

                if (week.equals("")) {
                    toast("星期不能为空");
                    return;
                }
                if (position.length() <= 0) {
                    toast("位置不能为空");
                    return;
                }
                JSONObject jsop = new JSONObject();
                try {
                    jsop.put("isHave", true);
                    jsop.put("title", context);
                    jsop.put("location", location);
                    jsop.put("position", Integer.parseInt(position));
                    jsop.put("week", week);
                    jsop.put("time", tv_time.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // 发送 JsonObject 类型事件
                RxBus.getDefault().post(jsop);

                AddScheduleActivity.this.finish();
            }

        });

    }

    public void onClickeBlack(View view) {
        finish();
    }

    public void toast(String it) {
        Toast.makeText(this, it, Toast.LENGTH_LONG).show();
        return;
    }
}
