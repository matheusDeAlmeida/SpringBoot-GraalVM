package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;

import com.example.jpa.model.TestCase;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TestCaseRepository extends JpaRepository<TestCase,Integer>{
    List<TestCase> findByFeatureId(int id);

    Optional<TestCase> findByIdAndFeatureId(int id, int featureId);
}