package com.zkd.activity.jianshu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkd.adapters.SearchHistoryAdapter;
import com.zkd.litepal.bean.SearchHistoryBean;
import com.zkd.utils.V;
import com.zkd.views.FlowLayout;
import com.zkd.views.NoScrollListView;

import org.litepal.crud.DataSupport;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class SearchActivity extends AppCompatActivity {


    private FlowLayout mFlowLayout;
    private  NoScrollListView mListView;
    private SearchHistoryAdapter adapter;

    private String[] mFirstLabels = {"诗","Python","程序员","redis","散文","英语","小说","三生三世十里桃花","Android","爱情","Steam","经济"};

    private String[] mSecondLabels = {"文献","IOS","工作","体育","二炮手","诗歌","小说","万里长城","Android","梅花开","AndroidStudio","生活馆"};

    private static int labelsNum = 0;

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


        //搜索历史
        List<SearchHistoryBean> searchList = DataSupport.findAll(SearchHistoryBean.class);

        adapter = new SearchHistoryAdapter(SearchActivity.this,searchList);

        initViews();
    }

    private void initViews() {

        final LinearLayout ll_cancle = V.f(SearchActivity.this, R.id.ll_search_cancle);
        LinearLayout ll_search_change   = V.f(SearchActivity.this,R.id.ll_search_next);


        final EditText edt_search = V.f(SearchActivity.this, R.id.edt_search_content);

        mListView = V.f(SearchActivity.this,R.id.lv_search_history);
        mFlowLayout = V.f(SearchActivity.this, R.id.fl_search_tags);

        //返回
        ll_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //搜索
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH  )
                {
                    //do something;
                    String searchContent = edt_search.getText().toString();
                   // Toast.makeText(SearchActivity.this, "search" , Toast.LENGTH_SHORT).show();
                    SearchHistoryBean bean = new SearchHistoryBean(searchContent,(new Date()).toString());
                    bean.save();
                    searchHistoryRefresh();
                    //

                    return true;
                }
                return false;
            } 
        });

        //初始化tags
        initLabel(mFirstLabels);

        mListView.setDividerHeight(0);
        mListView.setAdapter(adapter);

        //热门搜索 换一批
        ll_search_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (labelsNum==0) {
                    initLabel(mSecondLabels);
                    labelsNum=1;
                }else if (labelsNum==1){
                    labelsNum=0;
                }

            }
        });


    }



    //刷新搜索历史
    private void searchHistoryRefresh() {
        List<SearchHistoryBean> searchList = DataSupport.findAll(SearchHistoryBean.class);

        adapter.refresh(searchList);
    }

    // 初始化标签
    private void initLabel(final String[] mLabels) {
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
