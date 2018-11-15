package main.zm.gdlgxy.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.zm.gdlgxy.MainActivity;
import main.zm.gdlgxy.R;
import main.zm.gdlgxy.activity.OpenWebActivity;
import main.zm.gdlgxy.adapter.ServicekAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment {

    public View ContenView;

    @BindView(R.id.ll_Curriculum)
    LinearLayout ll_Curriculum;

    @BindView(R.id.rv_news)
    RecyclerView rv_news;

    public MainActivity mainActivity;
    ServicekAdapter servicekAdapter;

    public CurriculumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ContenView = inflater.inflate(R.layout.fragment_curriculum, container, false);
        ButterKnife.bind(this, ContenView);

        GridLayoutManager layoutManager = new GridLayoutManager(mainActivity, 5);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_news.setLayoutManager(layoutManager);
        servicekAdapter = new ServicekAdapter(initDate());
        servicekAdapter.mainActivity = mainActivity;
        servicekAdapter.notifyDataSetChanged();
        rv_news.setAdapter(servicekAdapter);

        servicekAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                try {
                    String href = servicekAdapter.getData().get(position).getString("href");
                    Intent intent = new Intent(mainActivity, OpenWebActivity.class);
                    intent.putExtra("weburl", href);
                    mainActivity.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return ContenView;
    }


    public List<JSONObject> initDate() {
        List<JSONObject> list = new ArrayList<>();


        try {
//            JSONObject jsop1 = new JSONObject();
//            jsop1.put("title", "查充电费");
//            jsop1.put("img", R.drawable.ic_electricity);
//            jsop1.put("href", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc205af3eae045ea5&redirect_uri=http%3a%2f%2fwww.gyruibo2.cn%2f&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");

//            JSONObject jsop2 = new JSONObject();
//            jsop2.put("title", "热水卡充值");
//            jsop2.put("img", R.drawable.ic_water);
//            jsop2.put("href", "http://wokecp.gdlgxy.net/WeChat/LowBattery/index2");


//            JSONObject jsop3 = new JSONObject();
//            jsop3.put("title", "公物报修");
//            jsop3.put("img", R.drawable.ic_service2);
//            jsop3.put("href", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa8d5eb0c6f1605c2&redirect_uri=http%3a%2f%2frepair.gdlgxy.net%2f_woke666%2fmodules%2frepair%2fcreate.html&response_type=code&scope=snsapi_userinfo&state=0913#wechat_redirect");

            JSONObject jsop4 = new JSONObject();
            jsop4.put("title", "图书查询");
            jsop4.put("img", R.drawable.ic_book);
            jsop4.put("href", "http://lib.gdlgxy.com:88/sms/opac/search/showiphoneSearch.action");

//            JSONObject jsop5 = new JSONObject();
//            jsop5.put("title", "实时公交");
//            jsop5.put("img", R.drawable.ic_bus);
//            jsop5.put("href", "http://0775.mygolbs.com:8081/zqh5/");

            JSONObject jsop6 = new JSONObject();
            jsop6.put("title", "来校导航");
            jsop6.put("img", R.drawable.ic_come);
            jsop6.put("href", "https://map.baidu.com/mobile/webapp/place/linesearch/foo=bar/from=place&end=word%3D%25E5%25B9%25BF%25E4%25B8%259C%25E7%2590%2586%25E5%25B7%25A5%25E5%25AD%25A6%25E9%2599%25A2%2528%25E9%25AB%2598%25E8%25A6%2581%25E6%25A0%25A1%25E5%258C%25BA%2529%26point%3D12521988.88%252C2616132.04%26uid%3D85e90f33b05937f5725e8039&tab=line?qq-pf-to=pcqq.group");

            JSONObject jsop7 = new JSONObject();
            jsop7.put("title", "在线咨询");
            jsop7.put("img", R.drawable.ic_phone);
            jsop7.put("href", "http://static.microyan.com/gdlgxy/dialog-mobile.html");


          //  list.add(jsop1);
          //  list.add(jsop2);
          //  list.add(jsop3);
            list.add(jsop4);
        //    list.add(jsop5);
            list.add(jsop6);
            list.add(jsop7);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }
}
