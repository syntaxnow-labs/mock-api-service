package com.syntaxnow.mock.model;

import lombok.Data;

@Data
public class Alert {
    private String id;
    private String projectName;
    private String deviceId;
    private String severity;
    private String message;
    private String timestamp;
    private boolean acknowledged;
}
