package kr.jclab.javautils.executormonitor.internal;

import kr.jclab.javautils.executormonitor.TaskInfo;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class WrappedTaskBase implements TaskInfo {
    protected final String taskId = UUID.randomUUID().toString();
    protected final RuntimeException stack = new RuntimeException("STACK");
    protected final AtomicLong startedAt = new AtomicLong(-1);
    protected final AtomicLong finishedAt = new AtomicLong(-1);

    @Override
    @Nonnull
    public String getId() {
        return taskId;
    }

    @Override
    @Nonnull
    public RuntimeException getStack() {
        return stack;
    }

    @Override
    public long getStartedAt() {
        return startedAt.get();
    }

    @Override
    public long getFinishedAt() {
        return finishedAt.get();
    }

    @Override
    public long getDuration() {
        long startedAt = getStartedAt();
        if (startedAt < 0) {
            return -1L;
        }
        return System.nanoTime() - startedAt;
    }
}
