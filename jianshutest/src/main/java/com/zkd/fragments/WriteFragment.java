package com.zkd.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkd.activity.jianshu.R;

/**
 * Created by Alva on 2017/1/16.
 */
public class WriteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a, null);
        TextView text = (TextView) view.findViewById(R.id.tv_fragment);
        text.setText("写文章");
        return view;
    }


}
