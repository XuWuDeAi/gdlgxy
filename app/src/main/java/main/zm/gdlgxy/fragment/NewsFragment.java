package main.zm.gdlgxy.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.agentweb.AgentWeb;
import com.maning.library.SwitcherView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.zm.gdlgxy.MainActivity;

import main.zm.gdlgxy.R;
import main.zm.gdlgxy.activity.OpenWebActivity;
import main.zm.gdlgxy.adapter.NewsAdapter;
import main.zm.gdlgxy.service.NetService;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NetService.NewsListener {


    @BindView(R.id.tv_loading_headline)
    TextView tv_loading_headline;

    @BindView(R.id.switcherView)
    SwitcherView switcherView;
    @BindView(R.id.rv_news)
    RecyclerView rv_news;
    @BindView(R.id.ll_news)
    LinearLayout ll_news;
    NewsAdapter newsAdapter;
    public View ConTenView;
    public MainActivity mainActivity;
    NetService newsService = new NetService();

    public NewsFragment() {
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
        ConTenView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, ConTenView);
        newsService.attach(this);
        newsService.setNewsListener(this);

        return ConTenView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switcherView = ConTenView.findViewById(R.id.switcherView);
        //监听点击事件
        switcherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, OpenWebActivity.class);
                intent.putExtra("weburl", newsService.getHref(switcherView.getCurrentItem()));
                mainActivity.startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_news.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(new ArrayList<JSONObject>());

        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                Intent intent = new Intent(mainActivity, OpenWebActivity.class);
                try {

                    intent.putExtra("weburl", newsAdapter.getData().get(position).getString("href"));
                    mainActivity.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        rv_news.setAdapter(newsAdapter);
        newsService.getNews();
        newsService.getAnnouncement();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        switcherView.destroySwitcher();
    }


    @Override
    public void updateAnnouncement(ArrayList<String> strs) {
        //设置-修改数据源
        switcherView.setResource(strs);
        //开始滚动
        switcherView.startRolling();
        tv_loading_headline.setVisibility(View.GONE);

    }

    @Override
    public void updateNews(List<JSONObject> data) {
        newsAdapter.getData().addAll(data);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {

            if (ConTenView==null)
                return;
            //界面可见
            if (newsAdapter.getData().size() <= 0) {
                newsService.getNews();
            }

        } else {
            //界面不可见
        }
    }
}
