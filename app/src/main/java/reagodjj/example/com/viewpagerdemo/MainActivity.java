package reagodjj.example.com.viewpagerdemo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int DOT_WIDTH_HEIGHT = 80;
    private static final int DOT_MAX_WIDTH = 100;
    private static final int DOT_MAX_HEIGHT = 100;
    private static final int LEFT_MARGIN = 20;
    private static final int INIT_ITEM = 0;
    private static final int PAGE_LIMIT = 4;
    private int[] layoutIds = {
            R.layout.view_first,
            R.layout.view_second,
            R.layout.view_third,
    };

    private ViewPager vpMain;

    private ViewGroup dotViewGroup;
    private List<View> viewList = viewList = new ArrayList<>();
    private List<ImageView> dotList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpMain = findViewById(R.id.vp_main);
        dotViewGroup = findViewById(R.id.ll_dot);
        //数据、适配器
        int index = 0;
        while (index < layoutIds.length) {
//            viewList.add(LayoutInflater.from(this).inflate(layoutId, null));
            ImageView ivContent = new ImageView(this);
            ivContent.setImageResource(R.mipmap.diglett);
            viewList.add(ivContent);

            ImageView ivDot = new ImageView(this);
            ivDot.setImageResource(R.mipmap.ic_launcher_round);
            ivDot.setMaxWidth(DOT_MAX_WIDTH);
            ivDot.setMaxHeight(DOT_MAX_HEIGHT);

            //利用LinearLayout作为ViewGroup布局，将ivDot加载进去
            LinearLayout.LayoutParams layoutParams = new
                    LinearLayout.LayoutParams(DOT_WIDTH_HEIGHT, DOT_WIDTH_HEIGHT);
            layoutParams.leftMargin = LEFT_MARGIN;
            ivDot.setLayoutParams(layoutParams);
            ivDot.setEnabled(false);
            dotList.add(ivDot);
            dotViewGroup.addView(ivDot);
            index++;
        }

        vpMain.setAdapter(pagerAdapter);
        vpMain.setOffscreenPageLimit(PAGE_LIMIT);
        vpMain.setCurrentItem(INIT_ITEM);
        setCurrentView(INIT_ITEM);

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setCurrentView(int position) {
        for (int index = 0; index < dotList.size(); index++) {
            dotList.get(index).setImageResource(position == index ?
                    R.mipmap.diglett : R.mipmap.ic_launcher_round);
        }
    }

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return layoutIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View child = viewList.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(viewList.get(position));
        }
    };
}
