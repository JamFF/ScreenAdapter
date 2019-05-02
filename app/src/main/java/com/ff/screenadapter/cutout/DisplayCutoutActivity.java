package com.ff.screenadapter.cutout;

import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ff.screenadapter.R;
import com.ff.screenadapter.pixel.utils.UIUtils;

/**
 * description: 刘海屏适配
 * 1.判断手机厂商 华为，小米，oppo
 * 2.判断手机是否刘海
 * 3.设置是否让内容区域延伸进刘海
 * 4.设置控件是否避开刘海区域
 * 5.获取刘海的高度
 * author: FF
 * time: 2019-05-02 14:16
 */
public class DisplayCutoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. 设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 判断手机是否是刘海屏
        boolean hasDisplayCutout = hasDisplayCutout(window);
        if (hasDisplayCutout) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                // 2. 让内容区域延伸进刘海
                WindowManager.LayoutParams params = window.getAttributes();
                /*
                 *  API 28以上支持
                 *  @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 全屏模式，内容下移，非全屏不受影响
                 *  @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 允许内容去延伸进刘海区（还需要设置沉浸式，才能生效）
                 *  @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容延伸进刘海区
                 */
                params.layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                window.setAttributes(params);
            }


            // 3. 设置成沉浸式
            int flags = View.SYSTEM_UI_FLAG_FULLSCREEN// 全屏
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION// 隐藏虚拟导航栏
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;// 全屏
            int visibility = window.getDecorView().getSystemUiVisibility();
            visibility |= flags; // 追加沉浸式设置
            window.getDecorView().setSystemUiVisibility(visibility);
        }

        setContentView(R.layout.activity_display_cutout);

        // 如果在刘海位置出现了控件，可以设置 topMargin 向下移动
        Button button = findViewById(R.id.button);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) button.getLayoutParams();
        layoutParams.topMargin = heightForDisplayCutout();
        button.setLayoutParams(layoutParams);

        // 或者设置父容器的paddingTop
//        RelativeLayout layout = findViewById(R.id.container);
//        layout.setPadding(layout.getPaddingLeft(), heightForDisplayCutout(),
//                layout.getPaddingRight(), layout.getPaddingBottom());
    }

    /**
     * 判断手机是否为刘海屏，官方适配
     *
     * @param window {@link android.view.Window}
     */
    private boolean hasDisplayCutout(Window window) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            View rootView = window.getDecorView();
            WindowInsets insets = rootView.getRootWindowInsets();// API 23
            if (insets != null) {// 模拟器这里返回null
                DisplayCutout displayCutout = insets.getDisplayCutout();// API 28
                if (displayCutout != null) {
                    return displayCutout.getBoundingRects() != null// 刘海屏的集合
                            && displayCutout.getBoundingRects().size() > 0// 刘海的个数
                            && displayCutout.getSafeInsetTop() > 0;// 刘海的高度
                }
            }
        }
        return false;// 模拟器的话，这里需要强制返回true
    }

    //通常情况下，刘海的高就是状态栏的高
    public int heightForDisplayCutout() {
        return UIUtils.INSTANCE.getStatusBarHeight(this, 96);
    }
}
