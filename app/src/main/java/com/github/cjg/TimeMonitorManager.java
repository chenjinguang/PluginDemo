package com.github.cjg;

import java.util.HashMap;

/**
 * 采用单例管理各个耗时统计的数据。
 */
public class TimeMonitorManager {

    private static TimeMonitorManager mTimeMonitorManager = null;
    private HashMap<Integer, TimeMonitor> mTimeMonitorMap = null;

    public synchronized static TimeMonitorManager getInstance() {
        if (mTimeMonitorManager == null) {
            mTimeMonitorManager = new TimeMonitorManager();
        }
        return mTimeMonitorManager;
    }

    public TimeMonitorManager() {
        this.mTimeMonitorMap = new HashMap<Integer, TimeMonitor>();
    }

    /**
     * 初始化打点模块
     */
    public void resetTimeMonitor(int id) {
        if (mTimeMonitorMap.get(id) != null) {
            mTimeMonitorMap.remove(id);
        }
        getTimeMonitor(id).startMonitor();
    }

    /**
     * 获取打点器
     */
    public TimeMonitor getTimeMonitor(int id) {
        TimeMonitor monitor = mTimeMonitorMap.get(id);
        if (monitor == null) {
            monitor = new TimeMonitor(id);
            mTimeMonitorMap.put(id, monitor);
        }
        return monitor;
    }
}
