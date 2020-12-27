package com.github.cjg.plugindemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Debug;

import com.github.cjg.TimeMonitorConfig;
import com.github.cjg.TimeMonitorManager;

/**
 * Created by chenjinguang on 2020/11/19
 */
public class App extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        try {
            HookHelper.hookInstrumentation(base);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeMonitorManager.getInstance().resetTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START).recordingTimeTag("Application-onCreate");
    }

    @Override
    public Resources getResources() {
        return PluginHelper.getPluginResources() == null ? super.getResources() : PluginHelper.getPluginResources();
    }

}
