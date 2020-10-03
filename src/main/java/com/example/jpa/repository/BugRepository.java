package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;

import com.example.jpa.model.Bug;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BugRepository extends JpaRepository<Bug,Integer>{
    List<Bug> findByInputDataId(int id);
	Optional<Bug> findByIdAndInputDataId(int featureId, int InputDataId);}