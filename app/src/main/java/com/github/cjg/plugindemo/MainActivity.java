package com.github.cjg.plugindemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.cjg.TimeMonitorConfig;
import com.github.cjg.TimeMonitorManager;
import com.github.cjg.plugindemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START).recordingTimeTag("MainActivity-onCreate-Over");
        Debug.startMethodTracing();
    }

    @Override
    protected void onStart() {
        super.onStart();
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START).end("MainActivity-onStart", false);
    }

    public void loadPlugin(View view) {
        try {
            PluginHelper.loadPlugin(this, getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "插件加载失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }

    public void launchPluginActivity(View view) {
        Class pluginActivityClass = null;
        try {
            pluginActivityClass = Class.forName("com.github.cjg.plugin.PluginActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (pluginActivityClass == null) {
            Toast.makeText(this, "找不到PluginActivity", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, pluginActivityClass);
        startActivity(intent);
    }

}
