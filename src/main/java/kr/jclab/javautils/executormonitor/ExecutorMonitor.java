package kr.jclab.javautils.executormonitor;

import kr.jclab.javautils.executormonitor.internal.MonitoredExecutorService;
import kr.jclab.javautils.executormonitor.internal.MonitoredScheduledExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorMonitor {
    public static ExecutorService monitor(ExecutorService executorService, ExecutorMonitorHandler handler) {
        return new MonitoredExecutorService(executorService, handler);
    }

    public static ScheduledExecutorService monitor(ScheduledExecutorService executorService, ExecutorMonitorHandler handler) {
        return new MonitoredScheduledExecutorService(executorService, handler);
    }
}
