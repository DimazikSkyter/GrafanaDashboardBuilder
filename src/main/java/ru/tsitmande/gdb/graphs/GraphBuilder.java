package ru.tsitmande.gdb.graphs;

import uk.co.szmg.grafana.domain.Graph;

public interface GraphBuilder {

    String getCluster();

    Graph build();
}
