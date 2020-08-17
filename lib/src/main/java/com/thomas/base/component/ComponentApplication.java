package com.thomas.base.component;

import android.content.Context;

import com.thomas.base.BaseApplication;

/**
 * 组件化开发使用的Application，各个组件module可以实现必要的组件初始化
 */
public abstract class ComponentApplication extends BaseApplication {

    private AppLifecycles mAppDelegate;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null) {
            this.mAppDelegate = new AppDelegate(base);
        }
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate != null) {
            this.mAppDelegate.onCreate(this);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null) {
            this.mAppDelegate.onTerminate(this);
        }
    }
}
