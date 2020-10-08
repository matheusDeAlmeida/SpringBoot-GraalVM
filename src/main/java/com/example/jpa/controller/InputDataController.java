package com.example.jpa.controller;

import java.util.List;

import com.example.jpa.model.InputData;
import com.example.jpa.repository.InputDataRepository;
import com.example.jpa.repository.StepRepository;
import com.example.jpa.service.InputDataService;

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
public class InputDataController{
    private InputDataService service;

    @Autowired
    InputDataRepository repository;

    @Autowired
    StepRepository stepRepository;

    public InputDataController(InputDataService service) {
        this.service = service;
    }

    @GetMapping("/step/{id}/inputdata")
    public List<InputData> findAllBugs(@PathVariable (value = "id") int id){
        return repository.findByStepId(id);
    }

    @PostMapping(path = { "/step/{stepId}/inputdata" })
    public InputData create(@PathVariable (value = "stepId") int stepId, @RequestBody InputData bug) {
        return service.addInputData(stepId, bug);
    }

    @DeleteMapping(path = { "/steps/{stepId}/inputdata/{inputdataId}" })
    public ResponseEntity<?> delete(@PathVariable (value = "stepId") int stepId,
    @PathVariable (value = "inputDataId") int inputDataId) {
        return service.deleteInputData(stepId, inputDataId);
    }
}