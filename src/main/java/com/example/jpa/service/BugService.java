package com.example.jpa.service;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Bug;
import com.example.jpa.repository.BugRepository;
import com.example.jpa.repository.InputDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

// Basear-se em:
// /media/matheus/a/repo-cursos/jpa-hibernate-tutorials/jpa-one-to-many-demo
@Service
public class BugService{
    @Autowired
    private BugRepository repository;

    @Autowired
    InputDataRepository inputDataRepository;

    public Bug addBug(int inputDataId, @RequestBody Bug bug){
        return inputDataRepository.findById(inputDataId).map(inputdata -> {
                bug.setInputData(inputdata);
                return repository.save(bug);
        }).orElseThrow(() -> new ResourceNotFoundException("inputdataid " + inputDataId + " not found"));
    }

    public Bug updateBug(int inputDataId, int bugId, Bug bugRequest) {
        if (!inputDataRepository.existsById(inputDataId)) {
            throw new ResourceNotFoundException("inputDataId " + inputDataId + " not found");
        }

        return repository.findById(bugId).map(bug -> {
            bug.setDescription(bugRequest.getDescription());
            return repository.save(bug);
        }).orElseThrow(() -> new ResourceNotFoundException("bug " + bugId + "not found"));
    }

    public ResponseEntity<?> deleteBug(int inputDataId, int bugId){
        return repository.findByIdAndInputDataId(bugId, inputDataId).map(bug -> {
            repository.delete(bug);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}