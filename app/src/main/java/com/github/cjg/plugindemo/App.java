package com.github.cjg.plugindemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * Created by chenjinguang on 2020/11/19
 */
public class App extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        try {
            //HookHelper.hookAMS();
            HookHelper.hookInstrumentation(base);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Resources getResources() {
        return PluginHelper.getPluginResources() == null ? super.getResources() : PluginHelper.getPluginResources();
    }

}
