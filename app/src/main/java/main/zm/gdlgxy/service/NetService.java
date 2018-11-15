package main.zm.gdlgxy.service;

import android.text.style.UpdateAppearance;
import android.text.style.UpdateLayout;
import android.util.Log;

import com.blankj.utilcode.util.EncodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import main.zm.gdlgxy.fragment.NewsFragment;
import main.zm.gdlgxy.unit.HttpUnit;
import main.zm.gdlgxy.unit.MainUnit;
import okhttp3.Call;
import okhttp3.Request;

public class NetService {

    private NewsFragment newsFragment;

    public static HashMap<String, String> strs;

    // 回调接口
    public interface NewsListener {
        void updateAnnouncement(ArrayList<String> strs);

        void updateNews(List<JSONObject> data);

    }

    public NewsListener newsListener;

    public void setNewsListener(NewsListener newsListener) {
        this.newsListener = newsListener;
    }

    public void attach(NewsFragment newsFragment) {
        this.newsFragment = newsFragment;
    }

    public void getNews() {
        String url = "https://mp.weixin.qq.com/mp/homepage?__biz=MjM5MTQwMzc0OA==&hid=5&sn=3e2bdf1f3210510b585b24d6bf77d916&scene=18&uin=&key=&devicetype=Windows+10&version=62060526&lang=zh_CN&ascene=7&winzoom=1";
        final ArrayList<String> strs = new ArrayList<>();
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Document doc = Jsoup.parse(response);
                        try {


                            Element element = doc.select("script").get(10);
                            /*取得JS变量数组*/
                            String[] vars = element.data().split("var");
                            String data = vars[6];
                            String[] a = data.split(";");
                            data = a[0].split("data=")[1];
                            JSONObject json = new JSONObject(data);
                            JSONArray listdata = json.getJSONArray("appmsg_list");
                            List<JSONObject> array = new ArrayList<>();
                            for (int i = 0; i < listdata.length(); i++) {
                                JSONObject item = listdata.getJSONObject(i);
                                String title = item.getString("title");
                                String href = item.getString("link");
                                String pic = item.getString("cover");
                                String status = item.getString("author");
                                JSONObject jsonp = new JSONObject();
                                try {
                                    jsonp.put("title", title);
                                    jsonp.put("pic", pic);
                                    jsonp.put("status", status);
                                    jsonp.put("href", href);
                                    array.add(jsonp);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            newsListener.updateNews(array);
//                        List<Element> mastheads = masthead.select("a");
//                        List<JSONObject> array = new ArrayList<>();
//                        for (int i = 0; i < mastheads.size(); i++) {
//                            Element item = mastheads.get(i);
//                            String href = item.attr("href");
//                            String pic = item.select("img").first().attr("src");
//                            String title = item.select("h2").text();
//                            String status = item.select("p").text();
//
//                            JSONObject jsonp = new JSONObject();
//                            try {
//                                jsonp.put("title", title);
//                                jsonp.put("pic", pic);
//                                jsonp.put("status", status);
//                                jsonp.put("href", href);
//                                array.add(jsonp);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        newsListener.updateNews(array);
                            newsFragment.mainActivity.toast("asd");
                        } catch (Exception e) {
                        }
                    }

                });
    }

    public void getAnnouncement() {
        String url = "http://www.gdlgxy.com/bumenwangzhan/jiaowuchu/";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {


                        Document doc = Jsoup.parse(response.toString());
                        Element element = doc.select("ul[class$=news_list]").first();
                        List<Element> elements = element.select("a");

                        strs = new HashMap();

                        for (int i = 0; i < 3; i++) {
                            Element item = elements.get(i);
                            //	System.out.print(item.attr("href"));
                            //  System.out.print(item.text());
                            Log.e("__________________", item.html());
                            strs.put("通知" + (i + 1), item.attr("href"));

                            //strs.put(MainUnit.readInputStream(new ByteArrayInputStream(item.text().getBytes())), item.attr("href"));
                        }
                        Set<String> keys = strs.keySet();// 得到全部的key
                        ArrayList<String> list = new ArrayList<>(keys);
                        newsListener.updateAnnouncement(list);
                    }

                });
    }

    public String getHref(String key) {
        return strs.get(key);
    }


}
