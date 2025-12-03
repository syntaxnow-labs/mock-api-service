package com.syntaxnow.mock.model.graphs;

import lombok.Data;

@Data
public class Alert {
    private String id;
    private String projectName;
    private String message;
    private String severity;
    private String timestamp;
}