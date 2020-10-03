package com.example.jpa.repository;

import java.util.List;

import com.example.jpa.model.Feature;

/* import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; */
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature,Integer>{
    List<Feature> findByProjectId(int id);

    Optional<Feature> findByIdAndProjectId(int id, int projectId);
}