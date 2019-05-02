# ScreenAdapter
Android Screen Adapter

## 限定符适配
App 会根据当前手机的分辨率自动选择对应的 dimens.xml

![](https://raw.githubusercontent.com/JamFF/ScreenAdapter/master/art/qualifier_adapter.png)

优势：
* 使用简单，无需要开发者手动指定
* Google 推荐使用，由系统自己判断
* 适配通过不同的 xml 布局完成，无需再代码中额外再写

劣势：
* 增加 APK 大小，适配机型越多，需要的 xml 也就越多
* 适配所有机型的分辨率，xml 文件加起来有近 3M 的大小
* 不能适配奇葩机型，如手表

## 像素适配
以一个特定尺寸的设备为参考，在 View 的加载过程，根据当前设备的实际像素换算出目标像素，再作用在控件上。

有两种：自定义控件适配和代码动态适配，可以结合使用。

优势：
* 适配范围广

劣势：
* 自定义控件适配：对layout侵入比较大，Dialog 中不能使用
* 代码动态适配：对代码侵入比较大

参照：PixelFragment

## 百分比适配
以父容器尺寸作为参考，在 View 的加载过程，根据当前父容器实际尺寸换算出目标尺寸，再作用在控件上。

优势：
* 通过百分比定义宽高，比较方便
* 彻底抛弃 px dp 单位，通过百分比实现，可以在布局中完成适配
* 对开发者工作量少

劣势：
* 不能用系统的容器控件 LinearLayout 等，需要替换为支持百分比的对应控件
* 自定义容器，也要增加百分比的支持，或者继承已支持百分比的容器控件

参照：MyPercentFragment  
再推荐一个[百分比布局库的扩展](https://github.com/hongyangAndroid/android-percent-support-extend)

## density 适配
修改 density, scaleDensity, densityDpi值——直接更改系统内部对于目标尺寸而言的像素密度。

由于不同的设备，哪怕分辨率一样，density 的值都会不一样，所以这就需要我们根据分辨率来修改 density 的值。

* density: 屏幕的密度，例如参考像素密度为160，每一英寸有160个像素点，那么运行在像素密度为320的设备上，density就是2了。这个参考像素是需要设计师提供的。
* scaleDensity: 字体缩放比例，默认情况scaleDensity等于density。
* densityDpi: 屏幕上每一英寸的像素点个数。

可以看下 TypedValue 类：
```java
public static float applyDimension(int unit, float value,
                                   DisplayMetrics metrics) {
    switch (unit) {
        case COMPLEX_UNIT_PX:
            return value;
        case COMPLEX_UNIT_DIP:
            return value * metrics.density;
        case COMPLEX_UNIT_SP:
            return value * metrics.scaledDensity;
        case COMPLEX_UNIT_PT:
            return value * metrics.xdpi * (1.0f/72);
        case COMPLEX_UNIT_IN:
            return value * metrics.xdpi;
        case COMPLEX_UNIT_MM:
            return value * metrics.xdpi * (1.0f/25.4f);
    }
    return 0;
}
```

使用时可以根据需求放在 Activity、Fragment、基类、或者 Application 中。

优势：
* 适配非常简单、快速

劣势：
* 部分设备不允许修改density
* 顶部的 ToolBar 的高度也会被改变，横竖屏切换时更明显