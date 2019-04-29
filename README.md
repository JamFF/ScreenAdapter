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
* 自定义控件适配：不能用系统的容器控件 LinearLayout 等，对layout侵入比较大
* 代码动态适配，对代码侵入比较大

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