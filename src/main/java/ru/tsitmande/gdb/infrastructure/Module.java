package ru.tsitmande.gdb.infrastructure;

import ru.tsitmande.gdb.metrics.Metric;
import uk.co.szmg.grafana.domain.Graph;

import java.util.List;
import java.util.function.Predicate;

public interface Module<T extends Metric> {

    String getName();

    List<T> getMetrics();

    Graph generateGraph(Predicate predicate);
}
