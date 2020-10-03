package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;

import com.example.jpa.model.InputData;

import org.springframework.data.jpa.repository.JpaRepository;


public interface InputDataRepository extends JpaRepository<InputData,Integer>{
    List<InputData> findByStepId(int id);
	Optional<InputData> findByIdAndStepId(int inputDataId, int stepId);}