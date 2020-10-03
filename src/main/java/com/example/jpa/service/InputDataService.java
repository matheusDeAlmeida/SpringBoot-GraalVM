package com.example.jpa.service;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.InputData;
import com.example.jpa.repository.InputDataRepository;
import com.example.jpa.repository.StepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

// Basear-se em:
// /media/matheus/a/repo-cursos/jpa-hibernate-tutorials/jpa-one-to-many-demo
@Service
public class InputDataService{
    @Autowired
    private InputDataRepository repository;

    @Autowired
    StepRepository stepRepository;

    public InputData addInputData(int stepId, @RequestBody InputData inputData){
        return stepRepository.findById(stepId).map(step -> {
                inputData.setStep(step);
                return repository.save(inputData);
        }).orElseThrow(() -> new ResourceNotFoundException("stepId " + stepId + " not found"));
    }

    public InputData updateInputData(int stepId, int inputDataId, InputData inputDataRequest) {
        if (!stepRepository.existsById(stepId)) {
            throw new ResourceNotFoundException("stepId " + stepId + " not found");
        }

        return repository.findById(inputDataId).map(inputData -> {
            inputData.setDescription(inputDataRequest.getDescription());
            return repository.save(inputData);
        }).orElseThrow(() -> new ResourceNotFoundException("inputDataId " + inputDataId + "not found"));
    }

    public ResponseEntity<?> deleteInputData(int stepId, int inputDataId){
        return repository.findByIdAndStepId(inputDataId, stepId).map(inputData -> {
            repository.delete(inputData);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}