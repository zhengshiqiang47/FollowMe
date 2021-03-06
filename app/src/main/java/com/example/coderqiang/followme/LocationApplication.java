package com.example.coderqiang.followme;


import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.radar.RadarSearchManager;
import com.example.coderqiang.followme.Service.LocationService;
import com.example.coderqiang.followme.Util.GetPermission;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.squareup.leakcanary.LeakCanary;

/**
 * 主Application，所有百度定位SDK的接口说明请参考线上文档：http://developer.baidu.com/map/loc_refer/index.html
 *
 * 百度定位SDK官方网站：http://developer.baidu.com/map/index.php?title=android-locsdk
 * 
 * 直接拷贝com.baidu.location.service包到自己的工程下，简单配置即可获取定位结果，也可以根据demo内容自行封装
 */
public class LocationApplication extends Application {
    public static final String API_KEY="23548642";
	public LocationService locationService;
    public Vibrator mVibrator;
    private static LocationApplication locationApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationApplication=this;
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        EMOptions options=new EMOptions();
        options.setAutoLogin(false);
        EaseUI.getInstance().init(getApplicationContext(), options);
        EMClient.getInstance().init(getApplicationContext(),options);
        EMClient.getInstance().setDebugMode(true);
//        SysUtil.setApplication(this);
//        if(SysUtil.isTCMSServiceProcess(this)){
//            return;
//        }
//        if(SysUtil.isMainProcess()){
//            YWAPI.init(this,"23548642");
//        }

    }
    public static LocationApplication getInstance() {
        return locationApplication;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
