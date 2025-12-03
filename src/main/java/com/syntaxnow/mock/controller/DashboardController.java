package com.syntaxnow.mock.controller;


import com.syntaxnow.mock.model.DashboardResponse;
import com.syntaxnow.mock.repository.DashboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final DashboardRepository repository;

    @GetMapping
    public DashboardResponse getDashboard() {
        repository.refreshTimestamp();
        return repository.getDashboardData();
    }

    @GetMapping("/summary")
    public Object getSummaryOnly() {
        return repository.getDashboardData().getSummary();
    }

    @GetMapping("/trend")
    public Object getTrendData() {
        return repository.getDashboardData().getDeviceStatusTrend();
    }

    @GetMapping("/projects")
    public Object getProjectBreakdown() {
        return repository.getDashboardData().getDevicesByProject();
    }

    @GetMapping("/regions")
    public Object getRegionData() {
        return repository.getDashboardData().getRegionDistribution();
    }

    @GetMapping("/warranty")
    public Object getWarrantyData() {
        return repository.getDashboardData().getWarrantySplit();
    }

    @GetMapping("/alerts")
    public Object getRecentAlerts() {
        return repository.getDashboardData().getRecentAlerts();
    }
}
