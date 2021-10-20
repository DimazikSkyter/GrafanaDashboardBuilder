package ru.tsitmande.gdb.modules;

import ru.tsitmande.gdb.metrics.Metric;

import java.util.List;
import java.util.Map;

public interface Module<T extends Metric> {

    String getName();

    Map<String, T> getMetrics();
}
