package com.kubernetes.study.services;

import com.kubernetes.study.domain.StatusProcess;
import com.kubernetes.study.domain.StudyKubernetesRepository;
import com.kubernetes.study.domain.BackgroundEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackgroundProcessService implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BackgroundProcessService.class);

    private final BackgroundEntity backgroundEntity;
    private final StudyKubernetesRepository repository;

    public BackgroundProcessService(BackgroundEntity backgroundEntity, StudyKubernetesRepository repository) {
        this.backgroundEntity = backgroundEntity;
        this.repository = repository;
    }

    @Override
    public void run() {

        updateProcess(StatusProcess.RUNNING);

        try {
            for (int i = 1; i <= backgroundEntity.getToProcess(); i++) {
                Thread.sleep(3000);
                logger.info("Thread: {}, counting = {}", backgroundEntity.getIdentity(), i);

                updateProcess(i);
            }

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error("Error on running process");
            updateProcess(StatusProcess.ERROR);
            throw new RuntimeException(e);
        }

        updateProcess(StatusProcess.FINISHED);

        logger.info("That's all folks!!!");

    }

    private void updateProcess(StatusProcess status) {
        backgroundEntity.updateStatus(status);
        repository.update(backgroundEntity);
    }

    private void updateProcess(int processed) {
        backgroundEntity.updateExecutionData(processed);
        repository.update(backgroundEntity);
    }

}
