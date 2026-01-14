package com.syntaxnow.mock.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "project_id")
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
