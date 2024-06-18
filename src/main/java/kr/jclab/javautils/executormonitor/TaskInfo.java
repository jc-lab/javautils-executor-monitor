package kr.jclab.javautils.executormonitor;

import javax.annotation.Nonnull;
import java.util.UUID;

public interface TaskInfo {
    @Nonnull
    UUID getId();
    @Nonnull
    RuntimeException getStack();

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
