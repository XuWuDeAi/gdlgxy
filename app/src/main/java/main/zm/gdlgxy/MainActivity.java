package main.zm.gdlgxy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.LinearLayout;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import main.zm.gdlgxy.base.BaseActivity;
import main.zm.gdlgxy.fragment.CurriculumFragment;
import main.zm.gdlgxy.fragment.MeFragment;
import main.zm.gdlgxy.fragment.NewsFragment;
import main.zm.gdlgxy.fragment.ScheduleFragment;
import main.zm.gdlgxy.unit.MoreWindow;
import main.zm.gdlgxy.view.NoScrollViewPager;

public class MainActivity extends BaseActivity {

    LinearLayout idContainer;
    MoreWindow mMoreWindow;
    // 每一页就是一个Fragment
    Fragment[] pages = {
            new NewsFragment(), new ScheduleFragment(), new CurriculumFragment()
            , new MeFragment()
    };


    @BindView(R.id.viewpaper)
    NoScrollViewPager viewPager;
    @OnClick(R.id.lin_tab_home) void homeCkick() {

       viewPager.setCurrentItem(0);
    }
    @OnClick(R.id.lin_tab_course) void courseCkick() {

        viewPager.setCurrentItem(1);
    }
    @OnClick(R.id.lin_tab_group) void groupCkick() {

        viewPager.setCurrentItem(2);
    }
    @OnClick(R.id.lin_tab_person) void personCkick() {

        viewPager.setCurrentItem(3);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //状态栏透明
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        idContainer = findViewById(R.id.id_container);
        mMoreWindow = new MoreWindow(this);
        mMoreWindow.init(idContainer);
        findViewById(R.id.lin_tab_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreWindow();
            }
        });
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
    }



    // ViewPager支持
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // 一共有几页
        @Override
        public int getCount() {
            return pages.length;
        }

        // 每一页的对象
        @Override
        public Fragment getItem(int position) {
            return pages[position];
        }

    }

    private void showMoreWindow() {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(this);
            mMoreWindow.init(idContainer);
        }

        mMoreWindow.showMoreWindow(idContainer);
    }
}
