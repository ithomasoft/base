package com.thomas.base;

import android.app.Application;

import com.thomas.core.ProcessUtils;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (ProcessUtils.isMainProcess()) {
            initNetWork();
            initLog();
            initExpands();
        }
    }

    /**
     * 初始化日志收集系统
     */
    protected abstract void initLog();


    /**
     * 初始化网络请求
     */
    public abstract void initNetWork();

    /**
     * 备用初始化，可进行扩展
     */
    protected abstract void initExpands();
}
