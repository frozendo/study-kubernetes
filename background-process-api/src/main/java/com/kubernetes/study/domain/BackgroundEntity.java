package com.kubernetes.study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "background_entity")
public class BackgroundEntity {

    @Id
    @Column(name = "id")
    private String identity;

    @Column(name = "qtd_pending", nullable = false)
    private int pending;

    @Column(name = "qtd_processed", nullable = false)
    private int processed;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusProcess status;

    public BackgroundEntity() {
    }

    public BackgroundEntity(String identity, int pending, StatusProcess status) {
        this.identity = identity;
        this.pending = pending;
        this.processed = 0;
        this.status = status;
    }

    public String getIdentity() {
        return identity;
    }

    public int getPending() {
        return pending;
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
