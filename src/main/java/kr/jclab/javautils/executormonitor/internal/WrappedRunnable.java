package kr.jclab.javautils.executormonitor.internal;

import kr.jclab.javautils.executormonitor.ExecutorMonitorHandler;

public class WrappedRunnable extends WrappedTaskBase implements Runnable {
    private final ExecutorMonitorHandler monitorHandler;
    private final Runnable command;

    public WrappedRunnable(ExecutorMonitorHandler monitorHandler, Runnable command) {
        this.monitorHandler = monitorHandler;
        this.command = command;
    }

    @Override
    public void run() {
        startedAt.set(System.nanoTime());
        monitorHandler.onTaskStart(this);
        try {
            command.run();
            monitorHandler.onTaskEnd(this, null);
        } catch (Throwable e) {
            monitorHandler.onTaskEnd(this, e);
            throw e;
        } finally {
            finishedAt.set(System.nanoTime());
        }
    }
}
