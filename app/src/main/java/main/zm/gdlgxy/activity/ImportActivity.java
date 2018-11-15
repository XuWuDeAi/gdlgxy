package main.zm.gdlgxy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.zm.gdlgxy.R;
import main.zm.gdlgxy.base.BaseActivity;
import main.zm.gdlgxy.service.ScheduleService;
import main.zm.gdlgxy.unit.ClipboardUtils;

public class ImportActivity extends BaseActivity {

    @BindView(R.id.bt_ok)
    Button bt_ok;
    @BindView(R.id.et_import)
    EditText et_import;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);
        ButterKnife.bind(this);

        et_import.setText(ClipboardUtils.getText());

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (et_import.getText().toString().equals(""))
                        throw new Exception("请导入有效的数据源");
                    JSONObject jsop = new JSONObject(et_import.getText().toString());
                    ScheduleService.saveWeekString(jsop.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    toast("请导入有效的数据源");
                    return;
                }
                finish();
            }

        });
    }

    public void onClickeBlack(View view) {
        finish();
    }
}
