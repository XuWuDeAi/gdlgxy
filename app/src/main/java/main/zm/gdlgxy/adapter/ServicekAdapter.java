package main.zm.gdlgxy.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import main.zm.gdlgxy.MainActivity;
import main.zm.gdlgxy.R;
import main.zm.gdlgxy.service.ScheduleService;

public class ServicekAdapter extends BaseQuickAdapter<JSONObject, BaseViewHolder> {

    public MainActivity mainActivity;

    public ServicekAdapter(@Nullable List<JSONObject> data) {
        super(R.layout.item_service, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JSONObject item) {
        try {
            helper.setText(R.id.tv_service, item.getString("title"));
            helper.setImageDrawable(R.id.iv_service, mainActivity.getDrawable(item.getInt("img")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
