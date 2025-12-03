package com.syntaxnow.mock.model.graphs;

import lombok.Data;

@Data
public class TrendData {
    private String date;
    private int connected;
    private int disconnected;
}