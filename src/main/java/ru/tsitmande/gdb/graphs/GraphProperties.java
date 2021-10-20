package ru.tsitmande.gdb.graphs;

import lombok.Data;

import java.util.List;

@Data
public class GraphProperties {

    private String cluster;
    private List<String> metrics;
    private String graphName;
}
