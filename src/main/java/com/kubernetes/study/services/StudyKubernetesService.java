package com.kubernetes.study.services;

import com.kubernetes.study.domain.StatusProcess;
import com.kubernetes.study.domain.StudyKubernetesRepository;
import com.kubernetes.study.domain.BackgroundEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudyKubernetesService {

    private final StudyKubernetesRepository repository;

    public StudyKubernetesService(StudyKubernetesRepository repository) {
        this.repository = repository;
    }

    public void startBackgroundProcess(int toProcess) {
        var background = new BackgroundEntity(toProcess, StatusProcess.SCHEDULED);

        repository.save(background);

        var process = new BackgroundProcessService(background, repository);

        Thread thread = new Thread(process);
        thread.start();
    }

    public Set<BackgroundEntity> listProcessExecuted() {
        return repository.list();
    }

}
