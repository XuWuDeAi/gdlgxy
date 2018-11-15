package main.zm.gdlgxy.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import main.zm.gdlgxy.MainActivity;

import main.zm.gdlgxy.R;
import main.zm.gdlgxy.service.ScheduleService;

public class WeekAdapter extends BaseQuickAdapter<JSONObject, BaseViewHolder> {

    public MainActivity mainActivity;

    public WeekAdapter(@Nullable List<JSONObject> data) {
        super(R.layout.list_item_week, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JSONObject item) {
        try {
            Boolean isHave = item.getBoolean("isHave");
            if (isHave) {
                helper.setText(R.id.tv_context, item.getString("title"));
                helper.setText(R.id.tv_time, item.getString("time"));
                helper.setText(R.id.tv_location, item.getString("location"));
                LinearLayout ll_weekitem = helper.getView(R.id.ll_weekitem);
                ll_weekitem.setVisibility(View.VISIBLE);
                ll_weekitem.setBackgroundColor(ScheduleService.getColor(mainActivity));
            } else {
                LinearLayout ll_weekitem = helper.getView(R.id.ll_weekitem);
                ll_weekitem.setVisibility(View.INVISIBLE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
