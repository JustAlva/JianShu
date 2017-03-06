package com.zkd.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkd.activity.jianshu.R;
import com.zkd.adapters.FollowFragmentAdapter;
import com.zkd.beans.FollowPersonalBean;
import com.zkd.refreshrecyclerview.OnLoadMoreListener;
import com.zkd.refreshrecyclerview.OnRefreshListener;
import com.zkd.refreshrecyclerview.SuperRefreshRecyclerView;
import com.zkd.utils.V;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注页面
 *
 * @author Alva
 *         create by 2017/1/18 9:10
 */
public class FollowFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private int[] img_res = {R.drawable.follow_1, R.drawable.follow_2, R.drawable.follow_3, R.drawable.follow_4, R.drawable.follow_5, R.drawable.follow_6, R.drawable.follow_7, R.drawable.follow_8};
    private String[] names = {"影视天堂", "摄影", "运动&健身", "技术干活", "连载小说", "产品", "生活家", "游戏"};
    private String[] introduces = {"春风十里不如你", "当我读一首诗给你听", "运动不是那么轻易的", "网络数据抓去", "触碰不到的幸福", "订阅即将开始", "整理日记", "青春，游戏"};
    private SuperRefreshRecyclerView rv_recyclerview;
    private List<FollowPersonalBean> list;
    private FollowFragmentAdapter adapter;

    private boolean isFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_follow, null);
        initViews(view, getActivity());


        return view;
    }

    private void initViews(View view, FragmentActivity a) {
        LinearLayout ll_title_left = V.f(view, R.id.ll_title_bar_left);
        LinearLayout ll_title_between = V.f(view, R.id.ll_title_bar_between);
        LinearLayout ll_title_right = V.f(view, R.id.ll_title_bar_right);

        ImageView img_title_left = V.f(view, R.id.img_title_bar_left);
        ImageView img_title_betweent = V.f(view, R.id.img_title_bar_between);

        TextView tv_title = V.f(view, R.id.tv_title);

        rv_recyclerview = V.f(view, R.id.rv_followFragment);


        //--------------------------------------------
        img_title_left.setImageResource(R.drawable.follow_add_people);
        tv_title.setText("全部关注");

        list = new ArrayList<FollowPersonalBean>();
        FollowPersonalBean bean = new FollowPersonalBean();
        for (int i = 0; i < 5; i++) {
            bean = new FollowPersonalBean(img_res[i], "", names[i], true, 99, introduces[i]);
            list.add(bean);
        }

        adapter = new FollowFragmentAdapter(a, list);
        rv_recyclerview.init(new LinearLayoutManager(a), this, this);
        rv_recyclerview.setRefreshEnabled(true);
        rv_recyclerview.setLoadingMoreEnable(true);
        rv_recyclerview.setAdapter(adapter);
        rv_recyclerview.showData();

    }


    @Override
    public void onLoadMore() {
        Log.i("zkd", "isFirst1:" + isFirst);
        Log.i("zkd", "loadMore");
        int num = 0;
        for (int i = 0; i < 3; i++) {
            FollowPersonalBean bean = new FollowPersonalBean(img_res[i], "", names[i], true, 99, introduces[i]);
            list.add(bean);
            num++;
        }
        adapter.notifyDataSetChanged();
        rv_recyclerview.setLoadingMore(false);
        rv_recyclerview.moveToPosition(list.size() - num - 1);

    }

    @Override
    public void onRefresh() {
        //下拉刷新
        Toast.makeText(getActivity(), "onRefresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            public void run() {


                rv_recyclerview.setRefreshing(false);

                //execute the task

            }

        }, 3000);
    }
}
