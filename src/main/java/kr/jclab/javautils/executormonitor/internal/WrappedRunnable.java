package kr.jclab.javautils.executormonitor.internal;

import kr.jclab.javautils.executormonitor.ExecutorMonitor;

import java.util.UUID;

public class WrappedRunnable extends WrappedTaskBase implements Runnable {
    private final ExecutorMonitor monitor;
    private final Runnable command;

    public WrappedRunnable(ExecutorMonitor monitor, Runnable command) {
        this.monitor = monitor;
        this.command = command;
    }

    @Override
    public void run() {
        UUID callId = monitor.onTaskStart(this);
        startedAt.set(System.nanoTime());

        try {
            command.run();
            monitor.onTaskEnd(this, callId, null);
        } catch (Throwable e) {
            monitor.onTaskEnd(this, callId, e);
            throw e;
        } finally {
            finishedAt.set(System.nanoTime());
        }
    }
}
