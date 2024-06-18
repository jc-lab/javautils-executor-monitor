package kr.jclab.javautils.executormonitor;

import javax.annotation.Nullable;

public interface ExecutorMonitorHandler {
    void onTaskStart(TaskInfo taskInfo);
    void onTaskEnd(TaskInfo taskInfo, @Nullable Throwable throwable);
}
