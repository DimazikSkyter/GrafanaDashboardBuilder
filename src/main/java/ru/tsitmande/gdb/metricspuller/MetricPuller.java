package ru.tsitmande.gdb.metricspuller;

import ru.tsitmande.gdb.metrics.Metric;

import java.util.List;
import java.util.Map;

public interface MetricPuller {

    Map<String, List<Metric>> pull();
}
