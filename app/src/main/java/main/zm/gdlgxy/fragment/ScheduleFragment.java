package main.zm.gdlgxy.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.rxbus.RxBus;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.zm.gdlgxy.MainActivity;
import main.zm.gdlgxy.R;
import main.zm.gdlgxy.adapter.WeekAdapter;
import main.zm.gdlgxy.service.ScheduleService;
import main.zm.gdlgxy.unit.MainUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment implements ScheduleService.ScheduleListener {


    public View contentView;
    public MainActivity mainActivity;
    public ScheduleService scheduleService = new ScheduleService();
    public WeekAdapter weekAdapter1;
    public WeekAdapter weekAdapter2;
    public WeekAdapter weekAdapter3;
    public WeekAdapter weekAdapter4;
    public WeekAdapter weekAdapter5;

    @BindView(R.id.lv_week1)
    RecyclerView lv_week1;
    @BindView(R.id.lv_week2)
    RecyclerView lv_week2;
    @BindView(R.id.lv_week3)
    RecyclerView lv_week3;
    @BindView(R.id.lv_week4)
    RecyclerView lv_week4;
    @BindView(R.id.lv_week5)
    RecyclerView lv_week5;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) getActivity();
    }

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // 创建界面
        contentView = inflater.inflate(R.layout.fragment_schedule, container, false);
        try {//初始化界面
            ScheduleService.initView(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ButterKnife.bind(this, contentView);
        scheduleService.attach(this);
        scheduleService.setScheduleListener(this);


        // 注册 String 类型事件
        RxBus.getDefault().subscribe(this, new RxBus.Callback<JSONObject>() {

            @Override
            public void onEvent(JSONObject jsop) {
                updateWeekItem(jsop);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_week1.setLayoutManager(layoutManager);
        weekAdapter1 = new WeekAdapter(new ArrayList<JSONObject>());
        weekAdapter1.mainActivity = mainActivity;
        weekAdapter1.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                try {
                    Boolean isHave = weekAdapter1.getData().get(position).getBoolean("isHave");
                    if (isHave) {
                        scheduleService.showDeleteItemDialog(position, (WeekAdapter) adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
//        lv_week1.setNestedScrollingEnabled(false);
        lv_week1.setAdapter(weekAdapter1);
        //星期二
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mainActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_week2.setLayoutManager(layoutManager2);
        weekAdapter2 = new WeekAdapter(new ArrayList<JSONObject>());
        weekAdapter2.mainActivity = mainActivity;
        weekAdapter2.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                try {
                    Boolean isHave = weekAdapter2.getData().get(position).getBoolean("isHave");
                    if (isHave) {
                        scheduleService.showDeleteItemDialog(position, (WeekAdapter) adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
//        lv_week2.setNestedScrollingEnabled(false);
        lv_week2.setAdapter(weekAdapter2);
        //星期三
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(mainActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_week3.setLayoutManager(layoutManager3);
        weekAdapter3 = new WeekAdapter(new ArrayList<JSONObject>());
        weekAdapter3.mainActivity = mainActivity;
        weekAdapter3.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    Boolean isHave = weekAdapter3.getData().get(position).getBoolean("isHave");
                    if (isHave) {
                        scheduleService.showDeleteItemDialog(position, (WeekAdapter) adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        lv_week3.setNestedScrollingEnabled(false);
        lv_week3.setAdapter(weekAdapter3);
        //星期四
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(mainActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_week4.setLayoutManager(layoutManager4);
        weekAdapter4 = new WeekAdapter(new ArrayList<JSONObject>());
        weekAdapter4.mainActivity = mainActivity;
        weekAdapter4.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {


                try {
                    Boolean isHave = weekAdapter4.getData().get(position).getBoolean("isHave");
                    if (isHave) {
                        scheduleService.showDeleteItemDialog(position, (WeekAdapter) adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return true;
            }
        });
        lv_week4.setNestedScrollingEnabled(false);
        lv_week4.setAdapter(weekAdapter4);
        //星期四
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(mainActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_week5.setLayoutManager(layoutManager5);
        weekAdapter5 = new WeekAdapter(new ArrayList<JSONObject>());
        weekAdapter5.mainActivity = mainActivity;
        weekAdapter5.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {


                try {
                    Boolean isHave = weekAdapter5.getData().get(position).getBoolean("isHave");
                    if (isHave) {
                        scheduleService.showDeleteItemDialog(position, (WeekAdapter) adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return true;
            }
        });
        lv_week5.setNestedScrollingEnabled(false);
        lv_week5.setAdapter(weekAdapter5);
        InitDate();

        //  scheduleService.getSchedule();
        return contentView;
    }

    public List<JSONObject> getInitDate() {
        List<JSONObject> strs = new ArrayList<>();
        //初始化数据
        for (int i = 0; i < 6; i++) {
            try {
                JSONObject jsonp = new JSONObject();
                jsonp.put("isHave", false);//是否有效数据
                strs.add(jsonp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return strs;
    }


    //程序开始时的初始化数据
    public void InitDate() {
        String spData = scheduleService.getSpWeek(mainActivity);
        if (spData != null) {
            try {
                JSONObject jsop = new JSONObject(spData);


                JSONArray array = jsop.getJSONArray("week1");
                List<JSONObject> list = MainUnit.getList(array);
                weekAdapter1.getData().addAll(list);


                JSONArray array2 = jsop.getJSONArray("week2");
                List<JSONObject> list2 = MainUnit.getList(array2);
                weekAdapter2.getData().addAll(list2);

                JSONArray array3 = jsop.getJSONArray("week3");
                List<JSONObject> list3 = MainUnit.getList(array3);
                weekAdapter3.getData().addAll(list3);

                JSONArray array4 = jsop.getJSONArray("week4");
                List<JSONObject> list4 = MainUnit.getList(array4);
                weekAdapter4.getData().addAll(list4);

                JSONArray array5 = jsop.getJSONArray("week5");
                List<JSONObject> list5 = MainUnit.getList(array5);
                weekAdapter5.getData().addAll(list5);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            weekAdapter1.getData().addAll(getInitDate());
            weekAdapter2.getData().addAll(getInitDate());
            weekAdapter3.getData().addAll(getInitDate());
            weekAdapter4.getData().addAll(getInitDate());
            weekAdapter5.getData().addAll(getInitDate());
        }
        weekAdapter1.notifyDataSetChanged();
        weekAdapter2.notifyDataSetChanged();
        weekAdapter3.notifyDataSetChanged();
        weekAdapter4.notifyDataSetChanged();
        weekAdapter5.notifyDataSetChanged();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销
//        RxBus.getDefault().unregister(this);
    }


    @Override
    public void updateSchedule(List<JSONObject> data) {

        for (int i = 0; i < data.size(); i++) {
            JSONObject s = data.get(i);
            try {
                Boolean isHave = s.getBoolean("isHave");
                if (isHave) {
                    weekAdapter1.getData().add(s.getInt("postion"), s);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        weekAdapter1.notifyDataSetChanged();
    }

    public void updateWeekItem(JSONObject jsop) {
        try {
            String week = jsop.getString("week");
            if (week.equals("周一")) {
                int positon = jsop.getInt("position") - 1;
                if (weekAdapter1.getData().get(positon).getBoolean("isHave")) {
                    scheduleService.showCoverItemDaialog(mainActivity, weekAdapter1, jsop);
                } else {
                    weekAdapter1.getData().set(jsop.getInt("position") - 1, jsop);
                }
                weekAdapter1.notifyDataSetChanged();
            } else if (week.equals("周二")) {

                int positon = jsop.getInt("position") - 1;
                if (weekAdapter2.getData().get(positon).getBoolean("isHave")) {
                    scheduleService.showCoverItemDaialog(mainActivity, weekAdapter2, jsop);
                } else {
                    weekAdapter2.getData().set(jsop.getInt("position") - 1, jsop);
                }
                weekAdapter2.notifyDataSetChanged();
            } else if (week.equals("周三")) {
                int positon = jsop.getInt("position") - 1;
                if (weekAdapter3.getData().get(positon).getBoolean("isHave")) {
                    scheduleService.showCoverItemDaialog(mainActivity, weekAdapter3, jsop);
                } else {
                    weekAdapter3.getData().set(jsop.getInt("position") - 1, jsop);
                }
                weekAdapter3.notifyDataSetChanged();
            } else if (week.equals("周四")) {
                int positon = jsop.getInt("position") - 1;
                if (weekAdapter4.getData().get(positon).getBoolean("isHave")) {
                    scheduleService.showCoverItemDaialog(mainActivity, weekAdapter4, jsop);
                } else {
                    weekAdapter4.getData().set(jsop.getInt("position") - 1, jsop);
                }
                weekAdapter4.notifyDataSetChanged();
            } else {
                int positon = jsop.getInt("position") - 1;
                if (weekAdapter5.getData().get(positon).getBoolean("isHave")) {
                    scheduleService.showCoverItemDaialog(mainActivity, weekAdapter5, jsop);
                } else {
                    weekAdapter5.getData().set(jsop.getInt("position") - 1, jsop);
                }
                weekAdapter5.notifyDataSetChanged();
            }
            scheduleService.saveweek(mainActivity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
