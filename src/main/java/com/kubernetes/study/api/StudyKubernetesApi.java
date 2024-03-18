package com.kubernetes.study.api;

import com.kubernetes.study.services.StudyKubernetesService;
import com.kubernetes.study.domain.BackgroundEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/background")
public class StudyKubernetesApi {

    private final StudyKubernetesService studyKubernetesService;

    public StudyKubernetesApi(StudyKubernetesService studyKubernetesService) {
        this.studyKubernetesService = studyKubernetesService;
    }

    @PostMapping("/{toProcess}")
    public String startNewBackground(@PathVariable("toProcess") int toProcess) {
        studyKubernetesService.startBackgroundProcess(toProcess);
        return "Running";
    }

    @GetMapping
    public Set<BackgroundEntity> listBackground() {
        return studyKubernetesService.listProcessExecuted();
    }

}
