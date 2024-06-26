package kr.jclab.javautils.executormonitor.internal;

import kr.jclab.javautils.executormonitor.ExecutorMonitor;

import javax.annotation.Nonnull;
import java.util.concurrent.*;

public class MonitoredScheduledExecutorService extends MonitoredExecutorService implements ScheduledExecutorService {
    private final ScheduledExecutorService delegate;

    public MonitoredScheduledExecutorService(@Nonnull ScheduledExecutorService delegate, @Nonnull ExecutorMonitor monitor) {
        super(delegate, monitor);
        this.delegate = delegate;
    }

    @Override
    @Nonnull
    public ScheduledFuture<?> schedule(@Nonnull Runnable command, long delay, @Nonnull TimeUnit unit) {
        return delegate.schedule(wrap(command), delay, unit);
    }

    @Override
    @Nonnull
    public <V> ScheduledFuture<V> schedule(@Nonnull Callable<V> callable, long delay, @Nonnull TimeUnit unit) {
        return delegate.schedule(wrap(callable), delay, unit);
    }

    @Override
    @Nonnull
    public ScheduledFuture<?> scheduleAtFixedRate(@Nonnull Runnable command, long initialDelay, long period, @Nonnull TimeUnit unit) {
        return delegate.scheduleAtFixedRate(wrap(command), initialDelay, period, unit);
    }

    @Override
    @Nonnull
    public ScheduledFuture<?> scheduleWithFixedDelay(@Nonnull Runnable command, long initialDelay, long delay, @Nonnull TimeUnit unit) {
        return delegate.scheduleWithFixedDelay(wrap(command), initialDelay, delay, unit);
    }
}
