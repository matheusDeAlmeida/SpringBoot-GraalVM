package com.example.jpa.controller;

import java.util.List;

import com.example.jpa.model.Feature;
import com.example.jpa.repository.FeatureRepository;
import com.example.jpa.repository.ProjectRepository;
import com.example.jpa.service.FeatureService;

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
public class FeatureController{
    private FeatureService service;

    @Autowired
    FeatureRepository repository;

    @Autowired
    ProjectRepository projectRepository;

    public FeatureController(FeatureService service){
        this.service = service;
    }

    @GetMapping("/project/{id}/features")
    public List<Feature> findAllFeatures(@PathVariable (value = "id") int id){
        return repository.findByProjectId(id);
    }

    @PostMapping(path = { "/project/{projectId}/feature" })
    public Feature create(@PathVariable (value = "projectId") int projectId, @RequestBody Feature feature) {
        return service.addFeature(projectId, feature);
    }

    @DeleteMapping(path = { "/project/{projectId}/feature/{featureId}" })
    public ResponseEntity<?> delete(@PathVariable (value = "projectId") int projectId,
    @PathVariable (value = "featureId") int featureId) {
        return service.deleteFeature(projectId, featureId);
    }
}