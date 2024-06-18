package kr.jclab.javautils.executormonitor.internal;

import kr.jclab.javautils.executormonitor.ExecutorMonitor;

import java.util.UUID;
import java.util.concurrent.Callable;

public class WrappedCallable<V> extends WrappedTaskBase implements Callable<V> {
    private final ExecutorMonitor monitor;
    private final Callable<V> command;

    public WrappedCallable(ExecutorMonitor monitor, Callable<V> command) {
        this.monitor = monitor;
        this.command = command;
    }

    @Override
    public V call() throws Exception {
        UUID callId = monitor.onTaskStart(this);
        startedAt.set(System.nanoTime());

        try {
            V result = command.call();
            monitor.onTaskEnd(this, callId, null);
            return result;
        } catch (Throwable e) {
            monitor.onTaskEnd(this, callId, e);
            throw e;
        } finally {
            finishedAt.set(System.nanoTime());
        }
    }
}
