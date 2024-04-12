package com.kubernetes.study.service;

import com.kubernetes.study.domain.StatusProcess;
import com.kubernetes.study.domain.BackgroundRepository;
import com.kubernetes.study.domain.BackgroundEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunBackgroundService {

    private final BackgroundRepository repository;
    private final UuidService uuidService;

    public RunBackgroundService(BackgroundRepository repository, UuidService uuidService) {
        this.repository = repository;
        this.uuidService = uuidService;
    }

    public void startBackgroundProcess(int toProcess) {
        var uniqueId = uuidService.getUniqueId();
        var background = new BackgroundEntity(uniqueId, toProcess, StatusProcess.SCHEDULED);

        repository.save(background);

        var process = new BackgroundProcessService(background, repository);

        Thread thread = new Thread(process);
        thread.start();
    }

    public List<BackgroundEntity> listProcessExecuted() {
        return repository.findAll();
    }

}
