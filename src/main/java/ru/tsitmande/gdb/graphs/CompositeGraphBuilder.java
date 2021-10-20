package ru.tsitmande.gdb.graphs;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.tsitmande.gdb.metrics.Metric;
import ru.tsitmande.gdb.modules.Module;
import uk.co.szmg.grafana.domain.Graph;

import java.util.Map;
import java.util.Objects;

import static uk.co.szmg.grafana.domain.DomainFactories.newGraph;

@RequiredArgsConstructor
public class CompositeGraphBuilder implements GraphBuilder {

    @NonNull
    private final GraphProperties graphProperties;
    @NonNull
    private final Map<String, Module<? extends Metric>> modules;

    @Override
    public String getCluster() {
        return graphProperties.getCluster();
    }

    @Override
    public Graph build() {
        Graph graph = newGraph()
                .withTitle(graphProperties.getGraphName());
        graphProperties.getMetrics().stream()
                .map(this::find)
                .filter(Objects::nonNull)
                .forEach(metric ->
                        graph.addTarget(metric.toTarget()));
        return graph;
    }

    private Metric find(String name) {
        return modules.values().stream()
                .filter(module -> module.getMetrics().containsKey(name))
                .findAny()
                .map(module -> module.getMetrics().get(name))
                .orElse(null);
    }
}
