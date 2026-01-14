package com.syntaxnow.mock.controller;

import com.syntaxnow.mock.model.Alert;
import com.syntaxnow.mock.model.Device;
import com.syntaxnow.mock.model.Project;
import com.syntaxnow.mock.model.search.SearchResponse;
import com.syntaxnow.mock.repository.AlertRepository;
import com.syntaxnow.mock.repository.DeviceRepository;
import com.syntaxnow.mock.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private final ProjectRepository projectRepo;
    private final DeviceRepository deviceRepo;
    private final AlertRepository alertRepo;

    @GetMapping
    public SearchResponse search(@RequestParam String q) {
        String query = q.toLowerCase(Locale.ROOT);

        List<Project> projects = projectRepo.findAll().stream()
                .filter(p ->
                        (p.getProjectName() != null && p.getProjectName().toLowerCase().contains(query)) ||
                        (p.getProjectId() != null && p.getProjectId().toLowerCase().contains(query)))
                .toList();

        List<Device> devices = deviceRepo.findAll().stream()
                .filter(d ->
                        (d.getDeviceName() != null && d.getDeviceName().toLowerCase().contains(query)) ||
                        (d.getImei() != null && d.getImei().contains(query)) ||
                        (d.getRouterId() != null && d.getRouterId().toLowerCase().contains(query)))
                .toList();

        List<Alert> alerts = alertRepo.findAll(null).stream()
                .filter(a ->
                        (a.getProjectName() != null && a.getProjectName().toLowerCase().contains(query)) ||
                        (a.getId() != null && a.getId().toLowerCase().contains(query)) ||
                        (a.getMessage() != null && a.getMessage().toLowerCase().contains(query)))
                .toList();

        return new SearchResponse(projects, devices, alerts);
    }
}
