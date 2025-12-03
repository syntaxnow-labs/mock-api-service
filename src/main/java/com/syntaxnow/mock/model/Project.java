package com.syntaxnow.mock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String id;
    private String projectId;
    private String projectName;
    private String primaryContact;
    private Integer deviceCount;
    private String servicePlan;
    private String serviceStartDate;
    private String serviceEndDate;
    private String serviceStatus;
    private String purchaseOrderNo;
    private String salesOrderNo;
    private Boolean serviceNearExpiry;
    private String lastUpdatedDate;
    private Boolean groupedByProject;
}
