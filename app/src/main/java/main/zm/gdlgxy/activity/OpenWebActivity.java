package main.zm.gdlgxy.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.IAgentWebSettings;

import butterknife.BindView;
import main.zm.gdlgxy.R;


public class OpenWebActivity extends AppCompatActivity {

    WebView myWebView;

    @BindView(R.id.ll_web)
    LinearLayout ll_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_openweb);
    }

    @Override
    protected void onStart() {
        super.onStart();

        AgentWeb agentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) ll_web, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(getIntent().getStringExtra("weburl"));

        IAgentWebSettings iAgentWebSettings = agentWeb.getAgentWebSettings();
        iAgentWebSettings.getWebSettings().setSupportZoom(true);
        iAgentWebSettings.getWebSettings().setBuiltInZoomControls(true);
    }


}
