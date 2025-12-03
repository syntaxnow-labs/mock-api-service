package com.syntaxnow.mock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private List<Project> data;
    private Integer totalRecords;
    private String key;
    private Long timestamp;
}
