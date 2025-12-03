package com.syntaxnow.mock.model.search;

import com.syntaxnow.mock.model.Alert;
import com.syntaxnow.mock.model.Device;
import com.syntaxnow.mock.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private List<Project> projects;
    private List<Device> devices;
    private List<Alert> alerts;
}
