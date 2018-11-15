package main.zm.gdlgxy.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;
import main.zm.gdlgxy.R;


/**
 * Created by zm on 2018/8/6.
 */

public class AddWeekDialog extends DialogFragment {

    // 回调接口
    public interface OnDialogListener {
        void onDialogInput(String option);
    }

    public OnDialogListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        // 创建View, 指定布局XML
        View view = inflater.inflate(R.layout.dialog_onehua, container);
        final NumberPickerView numberPickerView = view.findViewById(R.id.picker);
        // 点击按钮时，关闭对话框
        Button button = (Button) view.findViewById(R.id.id_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭对话框

                // 返回给调用者
                if (listener != null) {

                    listener.onDialogInput(numberPickerView.getContentByCurrValue());
                }
            }
        });

        String[] city = {"周一","周二","周三","周四","周五"};
        numberPickerView.setDisplayedValues(city);
        numberPickerView.setMinValue(0);
        numberPickerView.setMaxValue(city.length - 1);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        // 当对话框显示时，调整对话框的窗口位置
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

            // 设置对话框的窗口显示
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.dimAmount = 0.3f; // 背景灰度
            lp.gravity = Gravity.BOTTOM; // 靠下显示
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }

}
