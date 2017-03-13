package com.zkd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkd.activity.jianshu.R;
import com.zkd.litepal.bean.SearchHistoryBean;
import com.zkd.utils.V;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
* SearchActivity 搜索历史RecyclerView Adapter
* @author Alva
* create at 2017/3/3 9:00
*/
public class SearchHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<SearchHistoryBean> list;

    public SearchHistoryAdapter(Context context, List<SearchHistoryBean> list) {
        this.context = context;
        this.list = list;
    }

    public void refresh(List<SearchHistoryBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        if (position == list.size()) {
            return null;
        }
        else{
            return list.get(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private   LinearLayout ll_clear;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (position == list.size()) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_history_clear, parent, false);

            ll_clear   = V.f(convertView,R.id.ll_item_search_clear);
            TextView tv_clear   = V.f(convertView,R.id.tv_item_search_clear);

            ll_clear.setVisibility(View.VISIBLE);
            //清空搜索
            tv_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSupport.deleteAll(SearchHistoryBean.class);
                    list.clear();
                    notifyDataSetChanged();
                    ll_clear.setVisibility(View.GONE);
                }
            });
        }else {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_history, parent, false);

            TextView tv_context = V.f(convertView, R.id.tv_searchHistoryItem_content);
            ImageView img_delete = V.f(convertView, R.id.img_searchHistoryItem_delete);

            if (list != null) {
                final SearchHistoryBean bean = list.get(position);
                String content = bean.getSearchContent();

                tv_context.setText(content);
                img_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context, "del", Toast.LENGTH_SHORT).show();
                        bean.delete();
                        list.remove(position);
                        if (list.size()==0) {
                            ll_clear.setVisibility(View.GONE);
                        }
                        notifyDataSetChanged();
                    }
                });
            }
        }
        if (getCount()<=1) {
            ll_clear.setVisibility(View.GONE);
        }
        return convertView;
    }


}
