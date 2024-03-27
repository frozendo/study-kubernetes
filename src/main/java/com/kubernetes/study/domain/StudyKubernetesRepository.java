package com.kubernetes.study.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class StudyKubernetesRepository {

    private final Map<String, BackgroundEntity> database;

    public StudyKubernetesRepository() {
        this.database = new HashMap<>();
    }

    public void save(BackgroundEntity backgroundEntity) {
        database.put(backgroundEntity.getIdentity(), backgroundEntity);
    }

    public void update(BackgroundEntity backgroundEntity) {
        database.put(backgroundEntity.getIdentity(), backgroundEntity);
    }

    public Set<BackgroundEntity> list() {
        return new HashSet<>(database.values());
    }

}
