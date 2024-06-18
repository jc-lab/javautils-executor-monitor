package kr.jclab.javautils.executormonitor;

import javax.annotation.Nonnull;

public interface TaskInfo {
    @Nonnull String getId();
    @Nonnull RuntimeException getStack();

    /**
     * @return nano
     */
    long getStartedAt();

    /**
     * @return nano
     */
    long getFinishedAt();

    /**
     * @return nano
     */
    long getDuration();
}
