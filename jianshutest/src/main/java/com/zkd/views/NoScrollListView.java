package com.zkd.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
* 不滚动listview
* @author Alva
* create at 2017/3/3 10:21
*/
public class NoScrollListView extends ListView {
	public NoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}
}
