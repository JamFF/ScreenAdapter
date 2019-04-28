package com.ff.screenadapter.pixel.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * description: 像素适配工具类
 * author: FF
 * time: 2019-04-26 20:08
 */
public enum UIUtils {

    INSTANCE;

    private static final float STANDARD_WIDTH = 1080;// 基准宽（短边）
    private static final float STANDARD_HEIGHT = 1920;// 基准高（长边）

    private static final String SYSTEM_BAR_HEIGHT = "system_bar_height";// 很多手机没有该参数
    private static final String STATUS_BAR_HEIGHT = "status_bar_height";

    private static float displayMetricsWidth = 0;// 屏幕的实际宽（短边）
    private static float displayMetricsHeight = 0;// 屏幕的实际高（长边）
    private static float systemBarHeight;// 状态栏高度

    public void init(Context context) {
        if (displayMetricsWidth == 0 || displayMetricsHeight == 0) {
            // 计算缩放系数
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null) {
                return;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            // 在这里得到设备的真实值
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            // systemBarHeight = reflectStatusBarHeight(context, 60);// 反射获取
            systemBarHeight = getStatusBarHeight(context, 60);

            // 判断手机方向
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                // 横屏时，短边为手机的高
                displayMetricsWidth = (float) (displayMetrics.heightPixels);
                // 横屏时，长边为手机的宽减去状态栏（因为一般UI设计图中没有考虑状态栏高度）
                displayMetricsHeight = displayMetrics.widthPixels - systemBarHeight;
            } else {
                // 竖屏时，短边为手机的宽
                displayMetricsWidth = (float) (displayMetrics.widthPixels);
                // 竖屏时，长边为手机的高减去状态栏（因为一般UI设计图中没有考虑状态栏高度）
                displayMetricsHeight = displayMetrics.heightPixels - systemBarHeight;
            }
        }
    }

    public void notifyInstance(Context context) {
        displayMetricsWidth = 0;
        displayMetricsHeight = 0;
        init(context);
    }

    private void checkInit() {
        if (displayMetricsWidth == 0 || displayMetricsHeight == 0) {
            throw new RuntimeException("UIUtils must dispatch init");
        }
    }

    /**
     * 获取水平方向的缩放比例
     */
    public float getHorizontalScaleValue() {
        checkInit();
        return displayMetricsWidth / STANDARD_WIDTH;
    }

    /**
     * 获取垂直方向的缩放比例
     */
    public float getVerticalScaleValue() {
        checkInit();
        return displayMetricsHeight / (STANDARD_HEIGHT - systemBarHeight);
    }

    public int getWidth(int width) {
        checkInit();
        return Math.round((float) width * displayMetricsWidth / STANDARD_WIDTH);
    }

    public int getHeight(int height) {
        checkInit();
        return Math.round((float) height * displayMetricsHeight / (STANDARD_HEIGHT - systemBarHeight));
    }

    /**
     * 反射获取状态栏高度
     *
     * @param context      上下文
     * @param defaultValue 默认值
     * @return
     */
    public int reflectStatusBarHeight(Context context, int defaultValue) {
        return getValue(context, "com.android.internal.R$dimen", STATUS_BAR_HEIGHT, defaultValue);
    }

    /**
     * 通过反射获取系统参数
     *
     * @param context      上下文
     * @param className    类名
     * @param fieldName    属性值
     * @param defaultValue 默认值
     */
    private int getValue(Context context, String className, String fieldName, int defaultValue) {
        try {
            Class<?> clz = Class.forName(className);
            Object object = clz.newInstance();
            Field field = clz.getField(fieldName);
            int id = Integer.parseInt(field.get(object).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 获取状态栏高度
     *
     * @param context      上下文
     * @param defaultValue 默认值
     */
    public int getStatusBarHeight(Context context, int defaultValue) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(STATUS_BAR_HEIGHT, "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
