package ru.tsitmande.gdb.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tsitmande.gdb.metrics.Metric;
import ru.tsitmande.gdb.metrics.PrometheusMetric;
import uk.co.szmg.grafana.domain.Graph;
import uk.co.szmg.grafana.domain.Target;

import java.util.List;
import java.util.function.Predicate;

import static uk.co.szmg.grafana.domain.DomainFactories.newGraph;

@Data
@AllArgsConstructor
public class TestService implements Module<PrometheusMetric> {

    private String name;
    List<PrometheusMetric> metrics;


    @Override
    public Graph generateGraph(Predicate predicate) {
        var graph = newGraph();
        metrics.stream()
                .filter(predicate)
                .map(o -> ((PrometheusMetric) o).toTarget())
                .forEach(o -> graph.addTarget((Target) o));
        return graph;
    }
}
