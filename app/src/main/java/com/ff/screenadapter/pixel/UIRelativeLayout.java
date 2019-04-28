package com.ff.screenadapter.pixel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.ff.screenadapter.pixel.utils.UIUtils;

/**
 * description: 自定义控件进行像素适配
 * author: FF
 * time: 2019-04-26 19:05
 */
public class UIRelativeLayout extends RelativeLayout {

    private boolean isFirst = true;

    public UIRelativeLayout(Context context) {
        super(context);
    }

    public UIRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UIRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isFirst) {
            // 会多次执行，使用标记控制
            isFirst = false;

            float scaleX = UIUtils.INSTANCE.getHorizontalScaleValue();
            float scaleY = UIUtils.INSTANCE.getVerticalScaleValue();
            int childCount = this.getChildCount();

            View child;
            LayoutParams layoutParams;
            for (int i = 0; i < childCount; i++) {
                child = this.getChildAt(i);
                layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
                // 1.注意padding不在这里，是子控件中的属性
                // 2.不需要setLayoutParams，因为此时还有没完成绘制
            }
        }
        // 3.注意上面代码要放在super前调用，不然第一次不会生效
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
