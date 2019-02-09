package reagodjj.example.com.viewpagerdemo;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import reagodjj.example.com.viewpagerdemo.Fragment.TestFragment;

public class ViewPagerActivity extends AppCompatActivity implements TabHost.TabContentFactory {
    private static final String HOME = "home";
    private static final String MESSAGE = "message";
    private static final String ME = "me";
    private TabHost thMenu;
    private ViewPager vpShow;
    private Fragment[] fragments;
    private int[] titleIDs = {R.string.main_page, R.string.message, R.string.me};
    private int[] iconIDs = {R.drawable.tabbar_home_style, R.drawable.tabbar_msg_style,
            R.drawable.tabbar_me_style};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        vpShow = findViewById(R.id.vp_show);
        thMenu = findViewById(R.id.th_menu);
        fragments = new Fragment[]{
                TestFragment.newInstance(HOME),
                TestFragment.newInstance(MESSAGE),
                TestFragment.newInstance(ME)
        };

        thMenu.setup();
        //三个Tab处理
        for (int index = 0; index < titleIDs.length; index++) {
            @SuppressLint("InflateParams")
            View view = getLayoutInflater().inflate(R.layout.activity_main_tab, null, false);
            ImageView ivTabIcon = view.findViewById(R.id.iv_tab_icon);
            TextView tvTabtitle = view.findViewById(R.id.tv_tab_title);
//            ImageView ivPrompt = view.findViewById(R.id.iv_prompt);

            ivTabIcon.setImageResource(iconIDs[index]);
            tvTabtitle.setText(titleIDs[index]);

            thMenu.addTab(thMenu.newTabSpec(getResources().getString(titleIDs[index]))
                    .setIndicator(view).setContent(this));
        }

        //三个Fragment组成的ViewPager

        vpShow.setOffscreenPageLimit(fragments.length);
        vpShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (thMenu != null) {
                    thMenu.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vpShow.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        thMenu.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (thMenu != null) {
                    int position = thMenu.getCurrentTab();
                    vpShow.setCurrentItem(position);
                }
            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(this);
        view.setMinimumWidth(0);
        view.setMinimumHeight(0);
        return view;
    }
}
