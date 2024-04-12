package com.kubernetes.study.api;

import com.kubernetes.study.service.RunBackgroundService;
import com.kubernetes.study.domain.BackgroundEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/background")
public class RunBackgroundApi {

    private final RunBackgroundService runBackgroundService;

    public RunBackgroundApi(RunBackgroundService runBackgroundService) {
        this.runBackgroundService = runBackgroundService;
    }

    @PostMapping("/{toProcess}")
    public String startNewBackground(@PathVariable("toProcess") int toProcess) {
        runBackgroundService.startBackgroundProcess(toProcess);
        return "Running";
    }

    @GetMapping
    public List<BackgroundEntity> listBackground() {
        return runBackgroundService.listProcessExecuted();
    }

}
