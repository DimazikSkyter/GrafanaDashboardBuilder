package ru.tsitmande.gdb.metrics;


import lombok.AllArgsConstructor;
import lombok.Data;
import uk.co.szmg.grafana.domain.Target;

import static uk.co.szmg.grafana.domain.DomainFactories.newTarget;

@Data
@AllArgsConstructor
public class PrometheusMetric implements Metric {

    private String name;
    private String metricType;
    private String metricQuery;

    @Override
    public Target toTarget() {
        return newTarget()
                .withTarget(metricQuery)
                .withRefId(name);
    }
}
