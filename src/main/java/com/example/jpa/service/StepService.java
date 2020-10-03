package com.example.jpa.service;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Step;
import com.example.jpa.repository.StepRepository;
import com.example.jpa.repository.TestCaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

//In computing based on the Java Platform, JavaBeans are classes that encapsulate many objects into a single object. They are serializable, have a zero-argument constructor, and allow access to properties using getter and setter methods.
@Service
public class StepService{
    @Autowired
    private StepRepository repository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    public Step addStep(int testCaseId, @RequestBody Step step){
        return testCaseRepository.findById(testCaseId).map(testCase -> {
                step.setTestCase(testCase);
                return repository.save(step);
        }).orElseThrow(() -> new ResourceNotFoundException("TestCaseId " + testCaseId + " not found"));
    }

    public Step updateStep(int testCaseId, int stepId, Step stepRequest) {
        if (!testCaseRepository.existsById(testCaseId)) {
            throw new ResourceNotFoundException("testCaseId " + testCaseId + " not found");
        }

        return repository.findById(stepId).map(step -> {
            step.setWasItSuccessful(stepRequest.getWasItSuccessful());
            return repository.save(step);
        }).orElseThrow(() -> new ResourceNotFoundException("stepId " + stepId + "not found"));
    }

    public ResponseEntity<?> deleteStep(int testCaseId, int stepId){
        return repository.findByIdAndTestCaseId(stepId, testCaseId).map(step -> {
            repository.delete(step);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}