package com.example.github_searcher.repository;

import com.example.github_searcher.entity.RepositoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryRecordRepository extends JpaRepository<RepositoryRecord, Long>, JpaSpecificationExecutor<RepositoryRecord> {}
