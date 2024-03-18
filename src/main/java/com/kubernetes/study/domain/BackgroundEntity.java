package com.kubernetes.study.domain;

import java.util.UUID;

public class BackgroundEntity {

    private final String identity;
    private final int toProcess;
    private int processed;
    private StatusProcess status;

    public BackgroundEntity(int toProcess, StatusProcess status) {
        this.identity = UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(8);
        this.toProcess = toProcess;
        this.processed = 0;
        this.status = status;
    }

    public String getIdentity() {
        return identity;
    }

    public int getToProcess() {
        return toProcess;
    }

    public int getProcessed() {
        return processed;
    }

    public StatusProcess getStatus() {
        return status;
    }

    public void updateExecutionData(int processed) {
        this.processed = processed;
    }

    public void updateStatus(StatusProcess status) {
        this.status = status;
    }
}
