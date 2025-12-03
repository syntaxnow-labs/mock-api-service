package com.syntaxnow.mock.repository;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.syntaxnow.mock.model.DashboardResponse;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Repository
public class DashboardRepository {

    private DashboardResponse dashboardData;

    @PostConstruct
    public void loadData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/data/dashboard.json")) {
            dashboardData = mapper.readValue(is, DashboardResponse.class);
        }
    }

    public DashboardResponse getDashboardData() {
        return dashboardData;
    }

    public void refreshTimestamp() {
        dashboardData.getSummary().setAlerts((int)(Math.random() * 50));
    }
}
