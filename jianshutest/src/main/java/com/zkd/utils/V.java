package com.zkd.utils;

import android.app.Activity;
import android.view.View;

/**
 * findViewById简化
 *
 * @author Alva
 *         created at 2016/12/12 9:19
 */
public class V {


    /**
     * activity.findViewById()
     *
     * @param context
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends View> T f(Activity context, int id) {
        return (T) context.findViewById(id);
    }

    /**
     * contextView.findViewById()
     * @param convertView
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends View> T f(View convertView, int id) {
        return (T) convertView.findViewById(id);
    }
}
