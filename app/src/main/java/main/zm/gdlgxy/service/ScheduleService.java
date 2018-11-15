package main.zm.gdlgxy.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import cn.carbs.android.library.MDDialog;
import main.zm.gdlgxy.MainActivity;
import main.zm.gdlgxy.R;
import main.zm.gdlgxy.adapter.WeekAdapter;
import main.zm.gdlgxy.fragment.NewsFragment;
import main.zm.gdlgxy.fragment.ScheduleFragment;
import main.zm.gdlgxy.unit.MainUnit;


/**
 * Created by zm on 2018/8/2.
 */

public class ScheduleService {

    ScheduleFragment scheduleFragment;
    public static  MainActivity mainActivity;

    public void attach(ScheduleFragment scheduleFragment) {
        this.scheduleFragment = scheduleFragment;
        mainActivity=scheduleFragment.mainActivity;
    }

    // 回调接口
    public interface ScheduleListener {

        void updateSchedule(List<JSONObject> data);

    }

    public ScheduleListener scheduleListener;

    public void setScheduleListener(ScheduleListener scheduleListener) {
        this.scheduleListener = scheduleListener;
    }

    public static void initView(ScheduleFragment scheduleFragment) throws Exception {
        setTVMonth(scheduleFragment);
        setTVWeekDate(scheduleFragment);
        setDateColor(scheduleFragment);

    }


    /* 设置当天颜色*/
    public static void setDateColor(ScheduleFragment scheduleFragment) {
        int nowDate = getNowDate();
        LinearLayout[] listLL = new LinearLayout[7];
        listLL[0] = scheduleFragment.contentView.findViewById(R.id.ll_1);
        listLL[1] = scheduleFragment.contentView.findViewById(R.id.ll_2);
        listLL[2] = scheduleFragment.contentView.findViewById(R.id.ll_3);
        listLL[3] = scheduleFragment.contentView.findViewById(R.id.ll_4);
        listLL[4] = scheduleFragment.contentView.findViewById(R.id.ll_5);

        if (nowDate <= 7 && nowDate >= 6) {
            return;
        }

        listLL[nowDate - 1].setBackgroundColor(Color.parseColor("#008577"));

    }


    /* 设置月*/
    public static void setTVMonth(ScheduleFragment scheduleFragment) {
        TextView tv_month = scheduleFragment.contentView.findViewById(R.id.tv_month);
        tv_month.setText(getMonth() + "月");
    }

    /* 设置日期*/
    public static void setTVWeekDate(ScheduleFragment scheduleFragment) {
        TextView[] listTV = new TextView[7];
        listTV[0] = scheduleFragment.contentView.findViewById(R.id.tv_weekdate_1);
        listTV[1] = scheduleFragment.contentView.findViewById(R.id.tv_weekdate_2);
        listTV[2] = scheduleFragment.contentView.findViewById(R.id.tv_weekdate_3);
        listTV[3] = scheduleFragment.contentView.findViewById(R.id.tv_weekdate_4);
        listTV[4] = scheduleFragment.contentView.findViewById(R.id.tv_weekdate_5);
        List<Integer> list = getWeekDate();
        Iterator<Integer> iter = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            Integer s = list.get(i);
            listTV[i].setText(s + "日");
        }
    }

    /*获取这月*/
    public static int getMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    /*获取这周日期集合*/
    public static List<Integer> getWeekDate() {
        List<Integer> result = new ArrayList<Integer>();
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int n = cal.get(Calendar.DAY_OF_WEEK);
        if (n == 1) {
            n = 7;
        } else {
            n = n - 1;
        }
        // 日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        for (int i = 1; i <= 5; i++) {
            cal.set(Calendar.DAY_OF_MONTH, date + i - n);
            result.add(new Integer(sdf.format(cal.getTime())));
        }
        return result;
    }

    /*获取今天是本周的第几天*/
    public static int getNowDate() {

        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int n = cal.get(Calendar.DAY_OF_WEEK);
        if (n == 1) {
            n = 7;
        } else {
            n = n - 1;
        }
        return n;
    }

    public static int getColor(MainActivity mainActivity) {
        Resources resources = mainActivity.getResources();
        int colors[] = {resources.getColor(R.color.md_red_500), resources.getColor(R.color.md_pink_500), resources.getColor(R.color.md_purple_500), resources.getColor(R.color.md_deep_purple_500), resources.getColor(R.color.md_indigo_500), resources.getColor(R.color.md_blue_500), resources.getColor(R.color.md_light_blue_500), resources.getColor(R.color.md_cyan_500), resources.getColor(R.color.md_teal_500), resources.getColor(R.color.md_green_500), resources.getColor(R.color.md_light_green_500), resources.getColor(R.color.md_lime_500), resources.getColor(R.color.md_amber_500), resources.getColor(R.color.md_orange_500), resources.getColor(R.color.md_deep_orange_500), resources.getColor(R.color.md_brown_500)};

        return colors[(int) (0 + Math.random() * ((colors.length - 1) - 0 + 1))];
    }


    public void showDeleteItemDialog(final int position, final WeekAdapter weekAdapter) {
        final String[] messages = new String[]{
        };
        new MDDialog.Builder(scheduleFragment.mainActivity)
                .setMessages(messages)
                .setTitle("是否删除该项")
                .setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JSONObject jsop = new JSONObject();
                        try {
                            jsop.put("isHave", false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        weekAdapter.remove(position);
                        weekAdapter.getData().add(position, jsop);
                        saveweek(scheduleFragment.mainActivity);
                    }
                })
                .setOnItemClickListener(new MDDialog.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int index) {
                    }
                })
                .setWidthMaxDp(600)
                .setShowTitle(true)
                .setShowButtons(true)
                .create()
                .show();
    }

    public void saveweek(MainActivity mainActivity) {
        List<JSONArray> data = getNowListDate();
        // 打开SharedPreference对象，这个对象相当于一个配置文件，里面可以存放多个配置项
        SharedPreferences sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE);
        // 如果要修改配置项，需要先获取一个editor对象
        SharedPreferences.Editor editor = sharedPref.edit();
        JSONObject saveJson = new JSONObject();
        for (int i = 0; i < data.size(); i++) {
            JSONArray s = data.get(i);
            try {
                saveJson.put("week" + (i + 1), s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // 把配置项存进去
        editor.putString("week", saveJson.toString());
        // 提交保存
        editor.commit();
    }

    public static void saveWeekString(String data) {

        // 打开SharedPreference对象，这个对象相当于一个配置文件，里面可以存放多个配置项
        SharedPreferences sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE);
        // 如果要修改配置项，需要先获取一个editor对象
        SharedPreferences.Editor editor = sharedPref.edit();
        // 把配置项存进去
        editor.putString("week",data);
        // 提交保存
        editor.commit();
    }

    public List<JSONArray> getNowListDate() {
        List<JSONArray> list = new ArrayList<>();
        list.add(MainUnit.getList(scheduleFragment.weekAdapter1.getData()));
        list.add(MainUnit.getList(scheduleFragment.weekAdapter2.getData()));
        list.add(MainUnit.getList(scheduleFragment.weekAdapter3.getData()));
        list.add(MainUnit.getList(scheduleFragment.weekAdapter4.getData()));
        list.add(MainUnit.getList(scheduleFragment.weekAdapter5.getData()));
        return list;
    }

    public static String getSpWeek(MainActivity mainActivity) {
        // 打开SharedPreference对象，这个对象相当于一个配置文件，里面可以存放多个配置项
        SharedPreferences sharedPref = mainActivity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString("week", null);
    }

    public void showCoverItemDaialog(MainActivity mainActivity, final WeekAdapter weekAdapter, final JSONObject jsop) {

        final String[] messages = new String[]{
        };
        new MDDialog.Builder(mainActivity)
                .setMessages(messages)
                .setTitle("该位置已有日程项，是否覆盖？")
                .setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            weekAdapter.getData().set(jsop.getInt("position") - 1, jsop);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        weekAdapter.notifyDataSetChanged();
                    }
                })
                .setOnItemClickListener(new MDDialog.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int index) {
                    }
                })
                .setWidthMaxDp(600)
                .setShowTitle(true)
                .setShowButtons(true)
                .create()
                .show();
    }
}
