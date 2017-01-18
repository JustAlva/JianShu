package com.zkd.activity.jianshu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkd.fragments.FindFragment;
import com.zkd.fragments.FollowFragment;
import com.zkd.fragments.NewsFragment;
import com.zkd.fragments.PersonalFragment;
import com.zkd.fragments.WriteFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private String[] mTitles = new String[] { "First Fragment !",
            "Second Fragment !", "Third Fragment !", "Fourth Fragment !","Fire Fragment" };
    private FragmentPagerAdapter mAdapter;

    private FollowFragment followFragment = null;
    private FindFragment findFragment = null;
    private WriteFragment  writeFragment = null;
    private NewsFragment newsFragment = null;
    private PersonalFragment personalFragment = null;

    private List<LinearLayout> mTabsLL = new ArrayList<LinearLayout>();
    private List<ImageView> mTabsImg = new ArrayList<ImageView>();
    private List<TextView> mTabsText = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        try {

            initView();

            initDatas();
            mViewPager.setAdapter(mAdapter);
            initEvent();
            // 设定初始页面
            mViewPager.setCurrentItem(0);
            mViewPager.setOffscreenPageLimit(4);
            // 禁止页面滚动
            mViewPager.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View arg0, MotionEvent arg1) {
                    return true;
                }
            });

        } catch (Exception e) {

        }

    }
    /**
     * 初始化所有事件
     */
    private void initEvent() {

        mViewPager.setOnPageChangeListener(this);

    }

    private void initDatas() {
        for (String title : mTitles) {

            if (title == "First Fragment !") {
                followFragment = new FollowFragment() ;
                mTabs.add(followFragment);

            } else if (title == "Second Fragment !") {

                findFragment = new FindFragment();
                mTabs.add(findFragment);

            } else if (title == "Third Fragment !") {

                writeFragment = new WriteFragment();
                mTabs.add(writeFragment);

            } else if (title == "Fourth Fragment !") {

                newsFragment = new NewsFragment();
                mTabs.add(newsFragment);

            } else if (title == "Fire Fragment !") {

                personalFragment = new PersonalFragment();
                mTabs.add(personalFragment);

            }
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }
        };
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_main_viewpager);

        LinearLayout one = (LinearLayout) findViewById(R.id.ll_main_follow);
        ImageView imgOne = (ImageView) findViewById(R.id.img_main_follow);
        TextView tvOne = (TextView) findViewById(R.id.tv_main_follow);
        mTabsLL.add(one);
        mTabsImg.add(imgOne);
        mTabsText.add(tvOne);

        LinearLayout two = (LinearLayout) findViewById(R.id.ll_main_find);
        ImageView imgTwo = (ImageView) findViewById(R.id.img_main_find);
        TextView tvTwo = (TextView) findViewById(R.id.tv_main_find);
        mTabsLL.add(two);
        mTabsImg.add(imgTwo);
        mTabsText.add(tvTwo);

        LinearLayout three = (LinearLayout) findViewById(R.id.ll_main_write);
        ImageView imgThree = (ImageView) findViewById(R.id.img_main_write);
        TextView tvThree = (TextView) findViewById(R.id.tv_main_write);
        mTabsLL.add(three);
        mTabsImg.add(imgThree);
        mTabsText.add(tvThree);

        LinearLayout four = (LinearLayout) findViewById(R.id.ll_main_news);
        ImageView imgFour = (ImageView) findViewById(R.id.img_main_news);
        TextView tvFour = (TextView) findViewById(R.id.tv_main_news);
        mTabsLL.add(four);
        mTabsImg.add(imgFour);
        mTabsText.add(tvFour);

        LinearLayout fire = (LinearLayout) findViewById(R.id.ll_main_personal);
        ImageView imgFire = (ImageView) findViewById(R.id.img_main_personal);
        TextView tvFire = (TextView) findViewById(R.id.tv_main_personal);
        mTabsLL.add(fire);
        mTabsImg.add(imgFire);
        mTabsText.add(tvFire);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        fire.setOnClickListener(this);

        mTabsText.get(0).setTextColor(
                getResources().getColor(R.color.main_font_grey));

    }

    public void onClick(View v) {
        clickTab(v);
    }

    /**
     * 点击Tab按钮
     *
     * @param v
     */
    private void clickTab(View v) {

        switch (v.getId()) {
            case R.id.ll_main_follow:
                mViewPager.setCurrentItem(0, false);
                changeColorSelect(0, R.drawable.main_follow_on);
                changeColorUnselect(1, R.drawable.main_find);
                changeColorUnselect(2, R.drawable.main_write);
                changeColorUnselect(3, R.drawable.main_news);
                changeColorUnselect(4, R.drawable.main_personal);

                break;
            case R.id.ll_main_find:
                mViewPager.setCurrentItem(1, false);
                changeColorSelect(1, R.drawable.main_find_on);
                changeColorUnselect(0, R.drawable.main_follow);
                changeColorUnselect(2, R.drawable.main_write);
                changeColorUnselect(3, R.drawable.main_news);
                changeColorUnselect(4, R.drawable.main_personal);

                break;
            case R.id.ll_main_write:
                mViewPager.setCurrentItem(2, false);
                changeColorSelect(2, R.drawable.main_write_on);
                changeColorUnselect(0, R.drawable.main_follow);
                changeColorUnselect(1, R.drawable.main_find);
                changeColorUnselect(3, R.drawable.main_news);
                changeColorUnselect(4, R.drawable.main_personal);
                break;
            case R.id.ll_main_news:
                mViewPager.setCurrentItem(3, false);
                changeColorSelect(3, R.drawable.main_news_on);
                changeColorUnselect(0, R.drawable.main_follow);
                changeColorUnselect(1, R.drawable.main_find);
                changeColorUnselect(2, R.drawable.main_write);
                changeColorUnselect(4, R.drawable.main_personal);
                break;

            case R.id.ll_main_personal:
                mViewPager.setCurrentItem(4, false);
                changeColorSelect(4, R.drawable.main_personal_on);
                changeColorUnselect(0, R.drawable.main_follow);
                changeColorUnselect(1, R.drawable.main_find);
                changeColorUnselect(2, R.drawable.main_write);
                changeColorUnselect(3, R.drawable.main_news);
                break;
        }
    }

    private void changeColorSelect(int pos, int res) {
        mTabsImg.get(pos).setImageResource(res);
        mTabsText.get(pos).setTextColor(
                getResources().getColor(R.color.main_font_grey));
    }

    private void changeColorUnselect(int pos, int res) {
        mTabsImg.get(pos).setImageResource(res);
        mTabsText.get(pos).setTextColor(
                getResources().getColor(R.color.main_font_grey_qian));
    }

    public void onPageScrollStateChanged(int arg0) {

    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    public void onPageSelected(int arg0) {
    }
}
