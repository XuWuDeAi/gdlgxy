package main.zm.gdlgxy.base;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    private static Handler mHandler;

    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        return mHandler;
    }

    public void toast( String it) {
        Toast.makeText(this, it, Toast.LENGTH_LONG).show();
    }

}
