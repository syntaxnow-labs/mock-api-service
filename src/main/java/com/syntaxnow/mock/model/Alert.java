package com.syntaxnow.mock.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table ( name = "AlertData")
public class  Alert {
    @Id
    @Column(name = "id")
    private String id;
    private String projectName;
    private String deviceId;
    private String severity;
    private String message;
    private String timestamp;
    private boolean acknowledged;
}
