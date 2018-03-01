# BaiLan
百篮应用市场。

####  注意
我已经将本应用用到的自定义View整理成了博客。请移步到我的博客，将会对你学习自定义View有很大帮助  
同时项目中会在近期加入显示手机应用apk文件及安装，以及同步下载进度的功能。都会在博客中讲解。请大家多多关注

[我的CSDN](http://blog.csdn.net/gg199402?viewmode=contents)  

[我的简书](https://www.jianshu.com/u/70a8f4edb323)  

---
#### 应用介绍
- Android6.0运行时权限
- RecycleView高级封装，万能RecycleView，试用90%以上布局
- 通过高度计算设计沉浸式状态栏
- 多种自定义控件(比如自定义轮播图，下载进度Progress，SubTabNavitagor,Flowlayout,阻尼会弹View，伸缩TextView等)
- 功能强大，健壮，完善的网络请求库(基于Rxjava2，retrofit2，GreenDAO，Okhttp3的网络请求库，支持多文件下载，断网重新请求，Rxjava生命周期管理，缓存数据，断电续传，异常处理....)
- 利用AIDL缓存清理
- 观察者模式多页面下载进度同步
- 自定义带进度WebView
- 应用下载后自定义安装
- 查找应用，卸载，安装
- 所有应用数据缓存清理
- 自定义Activity跳转动画
- 阻尼回弹
- 沉浸式状态栏
- 根据数据请求结果动态更新界面  
- 自定义Activity切换动画  
- ......

------------
#### 技术要点
- retorfit2
- Rxjava2
- Degger2
- GreenDAO
- Okhttp3
- butterknife
- circleimageview
- blankj

------------

#### 项目架构
- Retorfit2+Rxjava2+Mvp+Dagger2架构开发多层封装，高度解耦

------------

>项目的演示：

> * [1. 引导页与运行时权限](#1)
>
> * [2. 分类页](#2)
> 
> * [3. 排行页](#3) 
> 
> * [4. 管理页](#4)
> 
> * [5. 推荐页](#5)
>
> * [6. 应用详情页](#6)
> 
> * [7. 我的](#7)
> 

------------

<h2  id='1'>1. 整体风格和个人页面</h2>

##### 引导页与运行时权限

![引导页与运行时权限](http://upload-images.jianshu.io/upload_images/3983615-c79154e55a07dd60.gif?imageMogr2/auto-orient/strip)
>如果觉得还行，请给个star吧

<h2  id='2'>2. 分类页</h2>

![分类页.gif](http://upload-images.jianshu.io/upload_images/3983615-50f59d8d9f0326cd.gif?imageMogr2/auto-orient/strip)

>加入阻尼回弹，搜索框动画，自定义webView等

<h2  id='3'>3. 排行页</h2>

![排行页.gif](http://upload-images.jianshu.io/upload_images/3983615-72bd30390e0fc8c6.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>多任务下载，自动安装

<h2  id='4'>4. 管理页 </h2>


![管理页.gif](http://upload-images.jianshu.io/upload_images/3983615-5f304bc7ea18f67b.gif?imageMogr2/auto-orient/strip)

>支持下拉刷新，上拉加载更多

<h2  id='5'>5. 推荐页 </h2>  


![推荐页.gif](http://upload-images.jianshu.io/upload_images/3983615-40f6e9ca7625640a.gif?imageMogr2/auto-orient/strip)

>自定义轮播图，支持滑动边界判断，加载更多

<h2  id='6'>6. 应用详情页 </h2>

![应用详情页gif.gif](http://upload-images.jianshu.io/upload_images/3983615-3293e7e39c62e7de.gif?imageMogr2/auto-orient/strip)

>自定义选择框(SubTabNavigator)与Fragment联动，自定义TextView伸缩框等。

<h2  id='7'>7. 我的</h2>

![mine.png](http://upload-images.jianshu.io/upload_images/3983615-ce46c45e226867e9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#####项目细节概括图   
![仿华为应用市场.png](http://upload-images.jianshu.io/upload_images/3983615-e1cc17b818caa010.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  

这个项目是我在菜鸟窝学习的[华为应用市场](http://www.cniao5.com/course/10132)，虽然是自己从0写的，但是完全消化还差的很远在多任务下载还有些问题。后续会加入观察者模式使多页面下载进度同步。如果你拷贝后发现报错只需要把多任务下载取消，后续会解决这个BUG，如果想了解更多请点击[菜鸟窝](http://www.cniao5.com/)

---
里面的控件和框架比较成熟，有需要的可以copy到自己的项目。想了解更多请留意我的博客，后续会把里面的自定义View和重点框架详细分析

---
当时看到了码云代码管理，好奇就在那里发布了，如果想看提交记录[点这里](https://gitee.com/gg199402/events)  

 


