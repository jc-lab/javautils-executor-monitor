package kr.jclab.javautils.executormonitor.internal;

import kr.jclab.javautils.executormonitor.ExecutorMonitorHandler;

import java.util.concurrent.Callable;

public class WrappedCallable<V> extends WrappedTaskBase implements Callable<V> {
    private final ExecutorMonitorHandler monitorHandler;
    private final Callable<V> command;

    public WrappedCallable(ExecutorMonitorHandler monitorHandler, Callable<V> command) {
        this.monitorHandler = monitorHandler;
        this.command = command;
    }

    @Override
    public V call() throws Exception {
        startedAt.set(System.nanoTime());
        monitorHandler.onTaskStart(this);
        try {
            V result = command.call();
            monitorHandler.onTaskEnd(this, null);
            return result;
        } catch (Throwable e) {
            monitorHandler.onTaskEnd(this, e);
            throw e;
        } finally {
            finishedAt.set(System.nanoTime());
        }
    }
}
