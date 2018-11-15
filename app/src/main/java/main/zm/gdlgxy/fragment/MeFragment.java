package main.zm.gdlgxy.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.zm.gdlgxy.MainActivity;
import main.zm.gdlgxy.R;
import main.zm.gdlgxy.activity.AddScheduleActivity;
import main.zm.gdlgxy.activity.OpenWebActivity;
import main.zm.gdlgxy.unit.AlipayZeroSdk;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    @BindView(R.id.bt_baba)
    Button bt_baba;
    @BindView(R.id.bt_gayhub)
    Button bt_gayhub;
    @BindView(R.id.bt_gayhubcode)
    Button bt_gayhubcode;
    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    public MainActivity mainActivity;
    public View ConTenView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ConTenView = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, ConTenView);
        bt_baba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // 捐赠开发者
                // HTTPS://QR.ALIPAY.COM/FKX04844O7GX5QDURDKJ09      --------2快\
                //HTTPS://QR.ALIPAY.COM/FKX089840DT0DYAC0370B6       ---------自定义金额
                if (AlipayZeroSdk.hasInstalledAlipayClient(mainActivity)) {
                    AlipayZeroSdk.startAlipayClient(mainActivity, "fkx04675ylfnbbug1par622?t=1539334167074");
                } else {
                    mainActivity.toast("谢谢，您没有安装支付宝客户端");
                }
            }
        });
        bt_gayhub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, OpenWebActivity.class);
                intent.putExtra("weburl", "https://github.com/XuWuDeAi");
                mainActivity.startActivity(intent);
            }
        });

        bt_gayhubcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, OpenWebActivity.class);
                intent.putExtra("weburl", "https://github.com/XuWuDeAi/gdlgxy");
                mainActivity.startActivity(intent);
            }
        });


        return ConTenView;
    }

}
