package com.kubernetes.study.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyKubernetesRepository extends JpaRepository<BackgroundEntity, String> {

}
