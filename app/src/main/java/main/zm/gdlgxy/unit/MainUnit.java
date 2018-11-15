package main.zm.gdlgxy.unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainUnit {
    public static List<JSONObject> getList(JSONArray array) {
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject s = null;
            try {
                s = array.getJSONObject(i);
                list.add(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static JSONArray getList(List<JSONObject> list) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject s = list.get(i);
            array.put(s);
        }
        return array;
    }

    /**
     * 把输入流的内容转化成字符串
     *
     * @param is
     * @return
     */
    public static String readInputStream(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.close();
            byte[] result = baos.toByteArray();
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
