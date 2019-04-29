package com.ff.screenadapter.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ff.screenadapter.R;

/**
 * description: 自定义百分比布局
 * author: FF
 * time: 2019-04-29 09:21
 */
public class MyPercentLayout extends RelativeLayout {

    private static final String TAG = "MyPercentLayout";

    public MyPercentLayout(Context context) {
        super(context);
    }

    public MyPercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure: ");
        // 获取父容器的尺寸
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams params = child.getLayoutParams();
            // 如果是百分比布局属性
            if (checkLayoutParams(params)) {
                LayoutParams lp = (LayoutParams) params;
                float widthPercent = lp.widthPercent;
                float heightPercent = lp.heightPercent;
                float marginLeftPercent = lp.marginLeftPercent;
                float marginRightPercent = lp.marginRightPercent;
                float marginTopPercent = lp.marginTopPercent;
                float marginBottomPercent = lp.marginBottomPercent;

                if (widthPercent > 0) {
                    lp.width = (int) (widthSize * widthPercent);
                }

                if (heightPercent > 0) {
                    lp.height = (int) (heightSize * heightPercent);
                }

                if (marginLeftPercent > 0) {
                    lp.leftMargin = (int) (widthSize * marginLeftPercent);
                }

                if (marginRightPercent > 0) {
                    lp.rightMargin = (int) (widthSize * marginRightPercent);
                }

                if (marginTopPercent > 0) {
                    lp.topMargin = (int) (heightSize * marginTopPercent);
                }

                if (marginBottomPercent > 0) {
                    lp.bottomMargin = (int) (heightSize * marginBottomPercent);
                }
            }
        }
        // 放在LayoutParams设置之后调用
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof MyPercentLayout.LayoutParams;
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        Log.d(TAG, "generateLayoutParams: ");
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            // 这里注意，LayoutParams的styleable有命名规范，要以外部类的类名加_Layout结尾，这样才能在xml布局中有提示
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.MyPercentLayout_Layout);
            // 解析自定义属性
            widthPercent = a.getFraction(R.styleable.MyPercentLayout_Layout_widthPercent, 1, 1, 0);
            heightPercent = a.getFraction(R.styleable.MyPercentLayout_Layout_heightPercent, 1, 1, 0);
            marginLeftPercent = a.getFraction(R.styleable.MyPercentLayout_Layout_marginLeftPercent, 1, 1, 0);
            marginRightPercent = a.getFraction(R.styleable.MyPercentLayout_Layout_marginRightPercent, 1, 1, 0);
            marginTopPercent = a.getFraction(R.styleable.MyPercentLayout_Layout_marginTopPercent, 1, 1, 0);
            marginBottomPercent = a.getFraction(R.styleable.MyPercentLayout_Layout_marginBottomPercent, 1, 1, 0);
            a.recycle();
        }
    }
}
