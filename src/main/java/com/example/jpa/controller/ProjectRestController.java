package com.example.jpa.controller;

import java.util.List;

import com.example.jpa.model.Project;
import com.example.jpa.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ProjectRestController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/projects")
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @PostMapping("/project")
    public Project addProject(@RequestBody final Project project){
        return projectRepository.save(project);
    }

    @DeleteMapping(path = { "/project/{id}" })
    public ResponseEntity<?> delete(@PathVariable Integer id){
        return projectRepository.findById(id).map(record -> {
            projectRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}