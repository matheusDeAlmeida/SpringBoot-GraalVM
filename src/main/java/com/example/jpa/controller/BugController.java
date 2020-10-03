package com.example.jpa.controller;

import java.util.List;

import com.example.jpa.model.Bug;
import com.example.jpa.repository.BugRepository;
import com.example.jpa.repository.InputDataRepository;
import com.example.jpa.service.BugService;

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
public class BugController{
    private BugService service;

    @Autowired
    BugRepository repository;

    @Autowired
    InputDataRepository inputDataRepository;

    public BugController(BugService service) {
        this.service = service;
    }

    @GetMapping("/inputdata/{id}/bugs")
    public List<Bug> findAllBugs(@PathVariable (value = "id") int id){
        return repository.findByInputDataId(id);
    }

    @PostMapping(path = { "/inputdata/{inputDataId}/bugs" })
    public Bug create(@PathVariable (value = "inputDataId") int inputDataId, @RequestBody Bug bug) {
        return service.addBug(inputDataId, bug);
    }

    @DeleteMapping(path = { "/inputdata/{inputDataId}/bug/{bugId}" })
    public ResponseEntity<?> delete(@PathVariable (value = "inputDataId") int inputDataId,
    @PathVariable (value = "bugId") int bugId) {
        return service.deleteBug(inputDataId, bugId);
    }
}