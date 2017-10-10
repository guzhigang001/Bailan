package com.example.ggxiaozhi.store.the_basket;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blankj.utilcode.utils.ToastUtils;
import com.example.ggxiaozhi.store.the_basket.mvp.view.activity.HomeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    String[] PERMISSION_STORAGES=new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//写内存卡的权限
            Manifest.permission.READ_EXTERNAL_STORAGE,//读内存卡的权限
    };
    private static final int REQUEST_CODE_STORAGE = 1;
    SharedPreferences sp;//用于存储是否第一次进入
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        sp=getSharedPreferences("appStore",Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (!isFirst){
            goHome();
        }

        requestStoragePermission(this);
    }

    @OnClick(R.id.enter_button)
    public void goHome(){
        sp.edit().putBoolean("isFirst",false).apply();
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }

    /**
     * 申请权限 SD卡的读写权限
     * @param activity
     */
    private void requestStoragePermission(Activity activity){
       //检测权限
        int permission = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission!= PackageManager.PERMISSION_GRANTED){
            //没有权限 则申请权限  弹出对话框
            ActivityCompat.requestPermissions(activity,PERMISSION_STORAGES,REQUEST_CODE_STORAGE);
        }
    }

    /**
     * 申请权限结果回调
     * @param requestCode 请求码
     * @param permissions 申请权限的数组
     * @param grantResults 申请权限成功与否的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //申请成功
                    ToastUtils.showShortToast("授权SD卡权限成功");
                }else {
                    //申请失败
                    ToastUtils.showShortToast("授权SD卡权限失败 可能会影响应用的使用");
                }
                break;
        }

    }
}
