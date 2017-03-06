package com.zkd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zkd.activity.jianshu.R;
import com.zkd.utils.V;

import java.util.List;

/**
* SearchActivity 搜索历史RecyclerView Adapter
* @author Alva
* create at 2017/3/3 9:00
*/
public class SearchHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public SearchHistoryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_search_history, parent, false);

        TextView tv_context = V.f(convertView, R.id.tv_searchHistoryItem_content);
        ImageView img_delete = V.f(convertView, R.id.img_searchHistoryItem_delete);

        if (list != null) {
            String content = list.get(position);

            tv_context.setText(content);
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "del", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }
}
