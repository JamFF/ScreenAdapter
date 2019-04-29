package com.ff.screenadapter.density;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * description: Density适配
 * author: FF
 * time: 2019-04-29 16:01
 */
public class Density {

    private static final float WIDTH = 320;// 参考设备的宽，单位是dp

    private static float appDensity;// 表示屏幕密度
    private static float appScaleDensity;// 字体缩放比例，默认等于appDensity

    private static float oldDensity;
    private static float oldScaledDensity;
    private static int oldDensityDpi;

    private static DisplayMetrics sDisplayMetrics;// Application的DisplayMetrics

    private static MyComponentCallbacks sCallbacks = new MyComponentCallbacks();

    /**
     * 修改Activity的Density
     *
     * @param application {@link Application}
     * @param activity    需要修改Density的Activity
     */
    public static void setDensity(final Application application, Activity activity) {

        // 获取当前app的屏幕显示信息
        sDisplayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            // 初始化赋值操作
            appDensity = sDisplayMetrics.density;
            appScaleDensity = sDisplayMetrics.scaledDensity;
        }

        // 计算目标值density, scaleDensity, densityDpi
        float targetDensity = sDisplayMetrics.widthPixels / WIDTH;
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);// TODO: 2019-04-29

        // 替换Activity的density, scaleDensity, densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();

        // 记录下初始值，以便Fragment还原时使用
        oldDensity = dm.density;
        oldScaledDensity = dm.scaledDensity;
        oldDensityDpi = dm.densityDpi;

        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;

        registerComponentCallbacks(application);
    }

    /**
     * 还原Activity的Density
     *
     * @param application {@link Application}
     * @param activity    需要还原Density的Activity
     */
    public static void resetDensity(final Application application, Activity activity) {

        unregisterComponentCallbacks(application);
        // 替换Activity的density, scaleDensity, densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = oldDensity;
        dm.scaledDensity = oldScaledDensity;
        dm.densityDpi = oldDensityDpi;
    }

    /**
     * 注册字体变化监听回调
     *
     * @param application {@link Application}
     */
    private static void registerComponentCallbacks(Application application) {
        if (application == null) {
            return;
        }
        application.registerComponentCallbacks(sCallbacks);
    }

    /**
     * 反注册字体变化监听回调
     *
     * @param application {@link Application}
     */
    private static void unregisterComponentCallbacks(Application application) {
        if (application == null) {
            return;
        }
        application.unregisterComponentCallbacks(sCallbacks);
    }

    private static class MyComponentCallbacks implements ComponentCallbacks {

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            // 监听设备配置信息改变
            if (newConfig != null && newConfig.fontScale > 0) {
                // 字体发生更改，重新对scaleDensity进行赋值
                appScaleDensity = sDisplayMetrics.scaledDensity;
            }
        }

        @Override
        public void onLowMemory() {

        }
    }
}
