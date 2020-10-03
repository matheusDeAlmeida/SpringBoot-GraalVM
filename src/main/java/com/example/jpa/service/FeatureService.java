package com.example.jpa.service;

import com.example.jpa.repository.FeatureRepository;
import com.example.jpa.repository.ProjectRepository;
import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

// Basear-se em:
// /media/matheus/a/repo-cursos/jpa-hibernate-tutorials/jpa-one-to-many-demo
@Service
public class FeatureService{
    @Autowired
    private FeatureRepository repository;

    @Autowired
    ProjectRepository projectRepository;

    public Feature addFeature(int projectId, @RequestBody Feature feature){
        return projectRepository.findById(projectId).map(project -> {
                feature.setProject(project);
                return repository.save(feature);
        }).orElseThrow(() -> new ResourceNotFoundException("ProjectId " + projectId + " not found"));
    }

    public Feature updateFeature(int projectId, int featureId, Feature featureRequest) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("ProjectId " + projectId + " not found");
        }

        return repository.findById(featureId).map(feature -> {
            feature.setDescription(featureRequest.getDescription());
            return repository.save(feature);
        }).orElseThrow(() -> new ResourceNotFoundException("FeatureId " + featureId + "not found"));
    }

    public ResponseEntity<?> deleteFeature(int projectId, int featureId){
        return repository.findByIdAndProjectId(featureId, projectId).map(feature -> {
            repository.delete(feature);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}