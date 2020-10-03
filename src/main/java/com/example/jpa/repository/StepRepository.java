package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;

import com.example.jpa.model.Step;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StepRepository extends JpaRepository<Step,Integer>{

	Optional<Step> findByIdAndTestCaseId(int stepId, int testCaseId);

	List<Step> findByTestCaseId(Integer id);}