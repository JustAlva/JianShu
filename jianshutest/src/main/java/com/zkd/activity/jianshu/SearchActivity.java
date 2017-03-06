package com.zkd.activity.jianshu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkd.adapters.SearchHistoryAdapter;
import com.zkd.utils.V;
import com.zkd.views.FlowLayout;
import com.zkd.views.NoScrollListView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {


    private FlowLayout mFlowLayout;

    private String[] mLabels = {"诗","Python","程序员","redis","散文","英语","小说","三生三世十里桃花","Android","爱情","Steam","经济"};
    /**
     * 修改状态栏字体颜色
     *
     * @param darkmode true：深色 ； false：浅色
     * @param activity
     */
    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setStatusBarDarkMode(true, SearchActivity.this);
        setContentView(R.layout.activity_search);

        initViews();
    }

    private void initViews() {

        LinearLayout ll_cancle = V.f(SearchActivity.this, R.id.ll_search_cancle);
        LinearLayout ll_search_change   = V.f(SearchActivity.this,R.id.ll_search_next);

        NoScrollListView mListView = V.f(SearchActivity.this,R.id.lv_search_history);
        mFlowLayout = V.f(SearchActivity.this, R.id.fl_search_tags);

        //返回
        ll_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //初始化tags
        initLabel();

        List<String> searchList = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            searchList.add("search"+i);
        }
        SearchHistoryAdapter adapter = new SearchHistoryAdapter(SearchActivity.this,searchList);
        mListView.setAdapter(adapter);

    }

    // 初始化标签
    private void initLabel() {
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 10, 10);// 设置边距
        for (int i = 0; i < mLabels.length; i++) {
            final TextView tv_tag = new TextView(SearchActivity.this);
            tv_tag.setTag(i);
            tv_tag.setTextSize(15);
            tv_tag.setText(mLabels[i]);
            tv_tag.setPadding(24, 11, 24, 11);
            tv_tag.setTextColor(getResources().getColor(R.color.main_font_grey_qian));

            tv_tag.setBackgroundResource(R.drawable.search_tag_bg);
            mFlowLayout.addView(tv_tag, layoutParams);
            // 标签点击事件
            tv_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this,mLabels[(int) tv_tag.getTag()], Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
