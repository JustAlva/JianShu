package com.zkd.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zkd.activity.jianshu.R;
import com.zkd.activity.jianshu.SearchActivity;
import com.zkd.beans.FollowPersonalBean;
import com.zkd.refreshrecyclerview.BaseRecyclerAdapter;
import com.zkd.utils.V;

import java.util.List;

/**
 * Created by Alva on 2017/2/15.
 */
public class FollowFragmentAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.BaseRecyclerViewHolder, FollowPersonalBean> {

    private Context mContext;
    private List<FollowPersonalBean> list;
    private boolean skipcache = true;

    public FollowFragmentAdapter(Context context, List<FollowPersonalBean> list) {
        super(list);
        this.mContext = context;
        this.list = list;
    }

    @Override
    public BaseRecyclerViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder holder = null;
        headerNum =2;
        if (viewType == 0) {
            holder = new SearchHolder(inflater.inflate(R.layout.item_follow_fragment_recyclerview_search, parent, false));
        } else if (viewType == 1) {
            holder = new JianYouHolder(inflater.inflate(R.layout.item_follow_fragment_recyclerview_first, parent, false));
        } else {
            holder = new FollowViewHolder(inflater.inflate(R.layout.item_follow_fragment_recyclerview, parent, false));
        }

        return holder;
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position, FollowPersonalBean data) {
        if (holder instanceof SearchHolder) {
            ((SearchHolder) holder).ll_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SearchActivity.class);
                    mContext.startActivity(intent);
                }
            });


        } else if (holder instanceof JianYouHolder) {

        } else if (position>=2) {
            if (list != null) {
                FollowPersonalBean bean = list.get(position - 2);
                Glide.with(mContext).load(bean.getImg_res())
                        //.placeholder(R.mipmap.ic_launcher)//默认显示的图片
                        .error(R.mipmap.ic_launcher) //加载错误图片
                        .centerCrop()
                        .skipMemoryCache(false)  //是否跳过内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                        .animate(R.anim.item_alpha_in)
                        .into(((FollowViewHolder) holder).img_item);
                ((FollowViewHolder) holder).tv_name.setText(bean.getName());
                ((FollowViewHolder) holder).tv_update_num.setText(bean.getUpdateNum()+"篇更新");
                ((FollowViewHolder) holder).tv_introduce.setText(bean.getIntroduce());

            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            //搜索框
            return 0;
        } else if (position == 1) {
            // 简友圈
            return 1;
        } else {
            //关注的人
            return 2;
        }
    }

    //search
    class SearchHolder extends BaseRecyclerViewHolder {
        LinearLayout ll_search = null;
        public SearchHolder(View itemView) {
            super(itemView);
            ll_search = (LinearLayout) itemView.findViewById(R.id.ll_follow_item_search);

        }
    }

    //简友圈
    class JianYouHolder extends BaseRecyclerViewHolder {

        public JianYouHolder(View itemView) {
            super(itemView);
        }
    }

    //关注
    class FollowViewHolder extends BaseRecyclerViewHolder {
        ImageView img_item;
        TextView tv_name, tv_update_num, tv_introduce;

        public FollowViewHolder(View itemView) {
            super(itemView);
            img_item = V.f(itemView, R.id.img_followItem_pic);
            tv_name = V.f(itemView, R.id.tv_followItem_name);
            tv_introduce = V.f(itemView, R.id.tv_followItem_introduce);
            tv_update_num = V.f(itemView, R.id.tv_followItem_update_num);
        }
    }
}
