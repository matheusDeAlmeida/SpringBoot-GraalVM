package com.example.jpa.controller;

import java.util.List;

import com.example.jpa.model.Step;
import com.example.jpa.repository.StepRepository;
import com.example.jpa.repository.TestCaseRepository;
import com.example.jpa.service.StepService;

import org.springframework.beans.factory.annotation.Autowired;
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
public class StepController {
    private StepService service;

    @Autowired
    StepRepository repository;

    @Autowired
    TestCaseRepository testCaseRepository;

    public StepController(StepService service) {
        this.service = service;
    }

    @GetMapping("/testcase/{id}/steps")
    public List<Step> findAllSteps(@PathVariable Integer id) {
        return repository.findByTestCaseId(id);
    }

    @PostMapping(path = { "/testcase/{testCaseId}/step" })
    public Step create(@PathVariable int testCaseId, @RequestBody Step p) {
        return service.addStep(testCaseId, p);
    }

    @DeleteMapping(path = { "/testcase/{testCaseId}/step/{stepId}" })
    public ResponseEntity<?> delete(@PathVariable(value = "testCaseId") int testCaseId,
            @PathVariable(value = "stepId") int stepId) {
        return service.deleteStep(testCaseId, stepId);
    }

}