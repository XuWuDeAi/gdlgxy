package main.zm.gdlgxy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.blankj.utilcode.util.AppUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import main.zm.gdlgxy.MainActivity;
import main.zm.gdlgxy.R;


import static main.zm.gdlgxy.base.BaseActivity.getHandler;

public class SplashActivity extends Activity {

    @BindView(R.id.tv_app_version)
    TextView tv_app_version;
    @BindView(R.id.shade_bg)
    TextView shadeBg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

//        int currentSkinType = SkinManager.getCurrentSkinType(this);
//        if (currentSkinType == SkinManager.THEME_NIGHT) {
//            shadeBg.setVisibility(View.VISIBLE);
//        }

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        }, 2500);

        tv_app_version.setText(String.valueOf("V " + AppUtils.getAppVersionName()));


    }

}
