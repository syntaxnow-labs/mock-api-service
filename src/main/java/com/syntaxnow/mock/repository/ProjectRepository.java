package com.syntaxnow.mock.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.syntaxnow.mock.model.Project;
import com.syntaxnow.mock.model.ProjectResponse;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ProjectRepository   {

    private final List<Project> projects = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/data/projects.json")) {
            ProjectResponse response = mapper.readValue(is, ProjectResponse.class);
            projects.addAll(response.getData());
        }
    }

    public List<Project> findAll() {
        return projects;
    }

    public Optional<Project> findById(String id) {
        return projects.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void save(Project project) {
        projects.add(project);
    }

    public void update(Project updated) {
        findById(updated.getId()).ifPresent(existing -> {
            existing.setProjectName(updated.getProjectName());
            existing.setPrimaryContact(updated.getPrimaryContact());
            existing.setDeviceCount(updated.getDeviceCount());
            existing.setServicePlan(updated.getServicePlan());
            existing.setServiceStartDate(updated.getServiceStartDate());
            existing.setServiceEndDate(updated.getServiceEndDate());
            existing.setServiceStatus(updated.getServiceStatus());
            existing.setPurchaseOrderNo(updated.getPurchaseOrderNo());
            existing.setSalesOrderNo(updated.getSalesOrderNo());
            existing.setServiceNearExpiry(updated.getServiceNearExpiry());
            existing.setLastUpdatedDate(updated.getLastUpdatedDate());
            existing.setGroupedByProject(updated.getGroupedByProject());
        });
    }

    public void delete(String id) {
        projects.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }
}
