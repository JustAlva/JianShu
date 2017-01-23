package com.zkd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkd.activity.jianshu.R;
import com.zkd.adapters.FollowFragmentRVAdapter;
import com.zkd.beans.FollowPersonalBean;
import com.zkd.utils.V;

import java.util.ArrayList;
import java.util.List;

/**
* 关注页面
* @author Alva
* create by 2017/1/18 9:10
*/
public class FollowFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_follow, null);
        initViews(view,getActivity());
        
        
        return view;
    }

    private void initViews(View view, FragmentActivity a) {
        LinearLayout  ll_title_left = V.f(view,R.id.ll_title_bar_left);
        LinearLayout  ll_title_between = V.f(view,R.id.ll_title_bar_between);
        LinearLayout  ll_title_right = V.f(view,R.id.ll_title_bar_right);

        ImageView img_title_left   = V.f(view,R.id.img_title_bar_left);
        ImageView img_title_betweent   = V.f(view,R.id.img_title_bar_between);

        TextView  tv_title = V.f(view,R.id.tv_title);

        RecyclerView rv_recyclerview = V.f(view, R.id.rv_followFragment);


        //--------------------------------------------
        img_title_left.setImageResource( R.drawable.follow_add_people );
        tv_title.setText("全部关注");

        List<FollowPersonalBean> list = new ArrayList<FollowPersonalBean>();
        FollowPersonalBean bean = new FollowPersonalBean();
        for (int i = 0; i < 5; i++) {
            list.add(bean);
        }

        FollowFragmentRVAdapter adapter = new FollowFragmentRVAdapter(a, list);
        rv_recyclerview.setLayoutManager(new LinearLayoutManager(a));
        rv_recyclerview.setAdapter(adapter);
        rv_recyclerview.setItemAnimator(new DefaultItemAnimator());
    }


}
