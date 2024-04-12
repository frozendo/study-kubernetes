package com.kubernetes.study.service;

import com.kubernetes.study.domain.StatusProcess;
import com.kubernetes.study.domain.BackgroundRepository;
import com.kubernetes.study.domain.BackgroundEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunBackgroundService {

    private final BackgroundRepository repository;

    public RunBackgroundService(BackgroundRepository repository) {
        this.repository = repository;
    }

    public void startBackgroundProcess(int toProcess) {
        var background = new BackgroundEntity(toProcess, StatusProcess.SCHEDULED);

        repository.save(background);

        var process = new BackgroundProcessService(background, repository);

        Thread thread = new Thread(process);
        thread.start();
    }

    public List<BackgroundEntity> listProcessExecuted() {
        return repository.findAll();
    }

}
