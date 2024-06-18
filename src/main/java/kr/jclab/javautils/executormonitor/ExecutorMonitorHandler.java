package kr.jclab.javautils.executormonitor;

import javax.annotation.Nullable;
import java.util.UUID;

public interface ExecutorMonitorHandler {
    void onTaskStart(TaskInfo taskInfo, UUID callId);
    void onTaskEnd(TaskInfo taskInfo, UUID callId, @Nullable Throwable throwable);
}
