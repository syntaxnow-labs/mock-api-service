package com.syntaxnow.mock.model;

import com.syntaxnow.mock.model.graphs.ProjectDeviceCount;
import com.syntaxnow.mock.model.graphs.RegionDeviceCount;
import com.syntaxnow.mock.model.graphs.TrendData;
import com.syntaxnow.mock.model.graphs.WarrantyData;

import lombok.Data;
import java.util.List;

@Data
public class DashboardResponse {
    private DashboardSummary summary;
    private List<TrendData> deviceStatusTrend;
    private List<ProjectDeviceCount> devicesByProject;
    private List<RegionDeviceCount> regionDistribution;
    private List<WarrantyData> warrantySplit;
    private List<Alert> recentAlerts;
}











