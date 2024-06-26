package com.kubernetes.study.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundRepository extends JpaRepository<BackgroundEntity, String> {

}
