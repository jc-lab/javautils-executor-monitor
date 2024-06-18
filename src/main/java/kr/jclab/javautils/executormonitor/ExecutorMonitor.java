package kr.jclab.javautils.executormonitor;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import kr.jclab.javautils.executormonitor.internal.MonitoredExecutorService;
import kr.jclab.javautils.executormonitor.internal.MonitoredScheduledExecutorService;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorMonitor {
    private final ExecutorMonitorHandler handler;

    private final NoArgGenerator callIdGenerator = Generators.timeBasedEpochRandomGenerator();

    ExecutorMonitor(ExecutorMonitorHandler handler) {
        this.handler = handler;
    }

    public ExecutorMonitorHandler getHandler() {
        return handler;
    }

    public UUID onTaskStart(TaskInfo taskInfo) {
        UUID callId = callIdGenerator.generate();
        handler.onTaskStart(taskInfo, callId);
        return callId;
    }

    public void onTaskEnd(TaskInfo taskInfo, UUID callId, @Nullable Throwable throwable) {
        handler.onTaskEnd(taskInfo, callId, throwable);
    }

    public static ExecutorService monitor(ExecutorService executorService, ExecutorMonitorHandler handler) {
        ExecutorMonitor instance = new ExecutorMonitor(handler);
        return new MonitoredExecutorService(executorService, instance);
    }

    public static ScheduledExecutorService monitor(ScheduledExecutorService executorService, ExecutorMonitorHandler handler) {
        ExecutorMonitor instance = new ExecutorMonitor(handler);
        return new MonitoredScheduledExecutorService(executorService, instance);
    }
}
