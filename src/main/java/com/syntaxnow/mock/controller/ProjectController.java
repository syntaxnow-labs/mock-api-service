package com.syntaxnow.mock.controller;


import com.syntaxnow.mock.model.Project;
import com.syntaxnow.mock.model.ProjectResponse;
import com.syntaxnow.mock.repository.ProjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectRepo repository;

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

    @PutMapping(path = "/{id}")
    public ResponseEntity<Project> update(@PathVariable String id, @RequestBody Project updated) {
        updated.setId(id);
        repository.save(updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByStatus")
    public List<Project> getStatus(@RequestParam String serviceStatus) {
        return repository.findByServiceStatusOrderById(serviceStatus);
    }

    @GetMapping("/projects")
    public Page<Project> getProjects(Pageable  pageable) {
        return repository.findAll(pageable);
    }
}


