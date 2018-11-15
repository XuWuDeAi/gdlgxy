package main.zm.gdlgxy.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import main.zm.gdlgxy.R;

public class NewsAdapter extends BaseQuickAdapter<JSONObject, BaseViewHolder> {


    public NewsAdapter(@Nullable List<JSONObject> data) {
        super(R.layout.item_list_v, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JSONObject item) {
        try {
            helper.setText(R.id.tv_fanname, item.getString("title"));
            Glide.with(mContext)
                    .load(item.getString("pic"))
                    .placeholder(R.drawable.placeholder_disk_300)//图片加载出来前，显示的图片
                    .error(R.drawable.placeholder_disk_300)//图片加载失败后，显示的图片
                    .into((ImageView) helper.getView(R.id.down_img));
            helper.setText(R.id.tv_status, item.getString("status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
