package org.popcraft.chunky.platform;

import java.nio.file.Path;

public interface Config {
    Path getDirectory();

    int getVersion();

    String getLanguage();

    boolean getContinueOnRestart();

    boolean isForceLoadExistingChunks();

    boolean isSilent();

    void setSilent(boolean silent);

    int getUpdateInterval();

    void setUpdateInterval(int updateInterval);

    /**
     * Maximum number of chunks a generation task works on concurrently.
     * A value less than 1 means "auto", which scales with available CPU cores.
     */
    int getMaxWorkingCount();

    void setMaxWorkingCount(int maxWorkingCount);

    void reload();
}
