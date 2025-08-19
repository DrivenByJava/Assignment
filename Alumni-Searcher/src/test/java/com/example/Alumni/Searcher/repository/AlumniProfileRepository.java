package com.example.Alumni.Searcher.repository;

import com.example.Alumni.Searcher.entity.AlumniProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {
}
