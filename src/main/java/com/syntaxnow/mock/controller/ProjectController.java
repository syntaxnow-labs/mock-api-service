package com.syntaxnow.mock.controller;


import com.syntaxnow.mock.model.Project;
import com.syntaxnow.mock.model.ProjectResponse;
import com.syntaxnow.mock.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectRepository repository;

    @GetMapping
    public ProjectResponse getAll() {
        List<Project> data = repository.findAll();
        return new ProjectResponse(data, data.size(), "static-key-demo", Instant.now().toEpochMilli());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        repository.save(project);
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable String id, @RequestBody Project updated) {
        updated.setId(id);
        repository.update(updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        repository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
