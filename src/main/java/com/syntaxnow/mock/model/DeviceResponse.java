package com.syntaxnow.mock.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponse {
    private List<Device> data;
    private Integer totalRecords;
    private String key;
    private Long timestamp;
}