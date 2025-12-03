package com.syntaxnow.mock.model;

import lombok.Data;

@Data
public class DashboardSummary {
    private int totalDevices;
    private int connectedDevices;
    private int disconnectedDevices;
    private int activeProjects;
    private int inWarrantyDevices;
    private int outOfWarrantyDevices;
    private int alerts;
}
