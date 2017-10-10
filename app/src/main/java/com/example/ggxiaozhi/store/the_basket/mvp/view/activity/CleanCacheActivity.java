package com.example.ggxiaozhi.store.the_basket.mvp.view.activity;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ggxiaozhi.store.the_basket.R;
import com.example.ggxiaozhi.store.the_basket.base.BaseActivity;
import com.example.ggxiaozhi.store.the_basket.utils.AppInfoUtils;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;

public class CleanCacheActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.pb)
    ProgressBar pb ;
    @BindView(R.id.tv_scan_status)
    TextView tv_status ;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final CacheInfo info = (CacheInfo) msg.obj;
            View view = View.inflate(getApplicationContext(), R.layout.item_cache_info, null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_size = (TextView) view.findViewById(R.id.tv_size);
            iv.setImageDrawable(info.icon);
            tv_name.setText(info.appname);
            tv_size.setText(Formatter.formatFileSize(getApplicationContext(), info.cachesize));
            ll_container.addView(view);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //引导用户进入APPInfo界面
                    AppInfoUtils.showInstalledAppDetails(CleanCacheActivity.this,info.packname);

                    //deleteApplicationCacheFiles
                    //因为删除缓存的方法只有系统级别的应用才可以调用，所以只能通过引导用户进入系统的AppInfo界面，让用户手动删除缓存
                    /*PackageManager pm = getPackageManager();
                    Method[] methods = PackageManager.class.getMethods();
                    for(Method method:methods){
                        if("deleteApplicationCacheFiles".equals(method.getName())){
                            try {
                                method.invoke(pm, info.packname, new IPackageDataObserver.Stub() {
                                    @Override
                                    public void onRemoveCompleted(String packageName, boolean succeeded) throws RemoteException {
                                        System.out.println(succeeded);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }*/
                }
            });
        }
    };

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_clean_cache);
    }

    @Override
    protected void initView() {

        //设置沉浸式状态栏
        setStatus();
        iv_search.setVisibility(View.GONE);
        //设置沉浸式状态栏背景
        bar_layout.setBackgroundResource(R.color.black_alpha_5);
        title_text.setText("清除缓存");

        scanCache();


    }

    /**
     * 扫描缓存的方法
     */
    private void scanCache() {
        new Thread(){
            public void run() {
                PackageManager pm = getPackageManager();
                List<PackageInfo> packInfos = pm.getInstalledPackages(0);
                pb.setMax(packInfos.size());
                int total = 0;
                for(PackageInfo packinfo:packInfos){
                    String packname = packinfo.packageName;
                    try {
                        Method method = PackageManager.class.getMethod("getPackageSizeInfo", String.class,IPackageStatsObserver.class);
                        method.invoke(pm, packname,new MyObserver());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    final String appname = packinfo.applicationInfo.loadLabel(pm).toString();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            tv_status.setText("正在扫描："+appname);
                        }
                    });
                    total++;
                    pb.setProgress(total);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_status.setText("扫描完毕！");
                    }
                });
            };
        }.start();
    }

    private class MyObserver extends IPackageStatsObserver.Stub{

        @Override
        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
                throws RemoteException {
            //获取缓存
            long cache = pStats.cacheSize;
            if(cache>0){
                try {
                    CacheInfo cacheInfo = new CacheInfo();
                    PackageManager pm = getPackageManager();
                    //添加缓存信息到ui界面
                    //获取包名
                    cacheInfo.packname = pStats.packageName;
                    PackageInfo packInfo = pm.getPackageInfo(cacheInfo.packname, 0);
                    cacheInfo.cachesize = cache;
                    cacheInfo.appname = packInfo.applicationInfo.loadLabel(pm).toString();
                    cacheInfo.icon = packInfo.applicationInfo.loadIcon(pm);
                    Message msg = Message.obtain();
                    msg.obj = cacheInfo;
                    handler.sendMessage(msg);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CacheInfo{
        String packname;
        String appname;
        Drawable icon;
        long cachesize;
    }


}
