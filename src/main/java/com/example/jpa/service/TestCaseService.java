package com.example.jpa.service;

import java.util.ArrayList;
import java.util.List;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.TestCase;
import com.example.jpa.repository.FeatureRepository;
import com.example.jpa.repository.TestCaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Basear-se em:
// /media/matheus/a/repo-cursos/jpa-hibernate-tutorials/jpa-one-to-many-demo
@Service
public class TestCaseService{
    @Autowired
    private TestCaseRepository repository;

    @Autowired
    FeatureRepository featureRepository;

    public List<TestCase> getTestCases(int id){
        List<TestCase> testcases = new ArrayList<>();
        repository.findByFeatureId(id).forEach(testcases::add);
        return testcases;
    }

    public ResponseEntity<TestCase> getTestCase(int id){
        return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    public TestCase addTestCase(int featureId, TestCase testCase){
        return featureRepository.findById(featureId).map(feature -> {
            testCase.setFeature(feature);
            return repository.save(testCase);
        }).orElseThrow(() -> new ResourceNotFoundException("FeatureId " + featureId + " not found"));
    }

    public TestCase updateTestCase(int featureId, int testcaseId, TestCase testcaseRequest) {
        if (!featureRepository.existsById(featureId)) {
            throw new ResourceNotFoundException("FeatureId " + featureId + " not found");
        }

        return repository.findById(testcaseId).map(testcase -> {
            testcase.setDescription(testcaseRequest.getDescription());
            testcase.setWasItSuccessful(testcaseRequest.getWasItSuccessful());
            //testcase.setFeature(testcaseRequest.getFeature());
            // testcase.setPlataformas(testcaseRequest.getPlataformas());

            return repository.save(testcase);
        }).orElseThrow(() -> new ResourceNotFoundException("TestCaseId " + testcaseId + "not found"));
    }

    public ResponseEntity<?> deleteTestCase(int featureId, int testcaseId){
        return repository.findByIdAndFeatureId(testcaseId, featureId).map(testcase -> {
            repository.delete(testcase);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}