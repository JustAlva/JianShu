package com.zkd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkd.activity.jianshu.R;
import com.zkd.beans.FollowPersonalBean;
import com.zkd.utils.V;

import java.util.List;

/**
 * 关注页RecyclerView的adapter
 *
 * @author Alva
 *         create by 2017/1/18 14:24
 */
public class FollowFragmentRVAdapter extends RecyclerView.Adapter<FollowFragmentRVAdapter.FollowViewHolder> {

    private Context context;
    private List<FollowPersonalBean> list;

    public FollowFragmentRVAdapter(Context context, List<FollowPersonalBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public FollowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FollowViewHolder holder = new FollowViewHolder(LayoutInflater.from(context).inflate(R.layout.item_follow_fragment_recyclerview, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(FollowViewHolder holder, int position) {
        if (list != null) {


        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FollowViewHolder extends RecyclerView.ViewHolder {
        ImageView img_item;
        TextView tv_name,tv_update_num,tv_introduce;
        public FollowViewHolder(View itemView) {
            super(itemView);
            img_item   = V.f(itemView,R.id.img_followItem_pic);
            tv_name = V.f(itemView, R.id.tv_followItem_name);
            tv_introduce = V.f(itemView, R.id.tv_followItem_introduce);
            tv_update_num = V.f(itemView, R.id.tv_followItem_update_num);
        }
    }
}
