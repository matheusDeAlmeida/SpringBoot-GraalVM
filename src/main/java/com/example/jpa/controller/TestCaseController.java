package com.example.jpa.controller;

import java.util.List;

import com.example.jpa.model.TestCase;
import com.example.jpa.service.TestCaseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TestCaseController{
    private TestCaseService service;

    public TestCaseController(TestCaseService service){
        this.service = service;
    }

    @GetMapping("/feature/{id}/testcases")
    public List<TestCase> findAllTestCases(@PathVariable Integer id){
        return service.getTestCases(id);
    }

    @GetMapping(path = { "/feature/{featureId}/testcase/{id}" })
    public ResponseEntity<TestCase> findById(@PathVariable int id){
        return service.getTestCase(id);
    }

    @PostMapping(path = { "/feature/{featureId}/testcase" })
    public void create(@PathVariable int featureId, @RequestBody TestCase f) {
        service.addTestCase(featureId, f);
    }

    @DeleteMapping(path = { "/feature/{featureId}/testcase/{testCaseId}" })
    public ResponseEntity<?> delete(@PathVariable (value = "featureId") int featureId,
    @PathVariable (value = "testCaseId") int testCaseId) {
        return service.deleteTestCase(featureId, testCaseId);
    }

}