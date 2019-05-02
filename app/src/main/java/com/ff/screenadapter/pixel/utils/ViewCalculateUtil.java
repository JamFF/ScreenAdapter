package com.ff.screenadapter.pixel.utils;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * description: 代码像素适配工具类，对layout侵性小
 * author: FF
 * time: 2019-04-27 23:43
 */
public class ViewCalculateUtil {

    /**
     * 设置RelativeLayout中 view的高度宽度
     */
    public static void setViewRelativeLayoutParam(View view, int width, int height,
                                                  int topMargin, int bottomMargin,
                                                  int lefMargin, int rightMargin) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.INSTANCE.getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = UIUtils.INSTANCE.getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = UIUtils.INSTANCE.getHeight(topMargin);
        layoutParams.bottomMargin = UIUtils.INSTANCE.getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.INSTANCE.getWidth(lefMargin);
        layoutParams.rightMargin = UIUtils.INSTANCE.getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置view的内边距
     */
    public static void setViewPadding(View view, int topPadding, int bottomPadding,
                                      int leftPadding, int rightPadding) {
        view.setPadding(UIUtils.INSTANCE.getWidth(leftPadding),
                UIUtils.INSTANCE.getHeight(topPadding),
                UIUtils.INSTANCE.getWidth(rightPadding),
                UIUtils.INSTANCE.getHeight(bottomPadding));
    }

    /**
     * 设置字体大小
     *
     * @param view TextView
     * @param size 纵向像素值
     */
    public static void setTextSize(TextView view, int size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, UIUtils.INSTANCE.getHeight(size));
    }

    /**
     * 设置LinearLayout中 view的高度宽度
     */
    public static void setViewLinearLayoutParam(View view, int width, int height) {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.INSTANCE.getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = UIUtils.INSTANCE.getHeight(height);
        } else {
            layoutParams.height = height;
        }

        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置RelativeLayout中 view的高度宽度
     */
    public static void setViewGroupLayoutParam(View view, int width, int height) {

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.INSTANCE.getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = UIUtils.INSTANCE.getHeight(height);
        } else {
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置LinearLayout中 view的高度宽度
     */
    public static void setViewLinearLayoutParam(View view, int width, int height,
                                                int topMargin, int bottomMargin,
                                                int lefMargin, int rightMargin) {

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.width = UIUtils.INSTANCE.getWidth(width);
        } else {
            layoutParams.width = width;
        }
        if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT) {
            layoutParams.height = UIUtils.INSTANCE.getHeight(height);
        } else {
            layoutParams.height = height;
        }

        layoutParams.topMargin = UIUtils.INSTANCE.getHeight(topMargin);
        layoutParams.bottomMargin = UIUtils.INSTANCE.getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtils.INSTANCE.getWidth(lefMargin);
        layoutParams.rightMargin = UIUtils.INSTANCE.getWidth(rightMargin);
        view.setLayoutParams(layoutParams);
    }
}
