package ru.tsitmande.gdb.metrics;

import uk.co.szmg.grafana.domain.Target;

public interface Metric {

    String getName();

    String getMetricType();

    String getMetricQuery();

    Target toTarget();
}
