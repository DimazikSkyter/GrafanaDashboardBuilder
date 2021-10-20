package ru.tsitmande.gdb;

import lombok.RequiredArgsConstructor;
import ru.tsitmande.gdb.infrastructure.Module;
import ru.tsitmande.gdb.metrics.PrometheusMetric;
import ru.tsitmande.gdb.templates.GrafanaDashboardProperties;
import ru.tsitmande.gdb.templates.GrafanaDashboardTemplateFacade;
import uk.co.szmg.grafana.domain.Dashboard;
import uk.co.szmg.grafana.domain.Graph;
import uk.co.szmg.grafana.domain.Row;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static uk.co.szmg.grafana.domain.DomainFactories.newDashboard;
import static uk.co.szmg.grafana.domain.DomainFactories.newRow;

@RequiredArgsConstructor
public class GrafanaDashboardPrometheusFacade implements GrafanaDashboardTemplateFacade<PrometheusMetric> {

    private final GrafanaDashboardProperties grafanaDashboardProperties;
    private final List<Module<PrometheusMetric>> grafanaModules = new ArrayList<>();
    private final Map<String, Predicate> predicatesMapping = new HashMap<>();

    @Override
    public GrafanaDashboardTemplateFacade<PrometheusMetric> addGraph(Module<PrometheusMetric> module) {
        grafanaModules.add(module);
        return this;
    }


    @Override
    public Dashboard generate() {
        List<Graph> graphs = grafanaModules.stream()
                .map(prometheusMetricModule -> prometheusMetricModule.generateGraph(
                        predicatesMapping.computeIfAbsent(prometheusMetricModule.getName(), s -> o -> true)
                ))
                .collect(Collectors.toList());

        Dashboard dashboard = newDashboard()
                .withTitle(grafanaDashboardProperties.getTitle());

        for (int i = 0; i <= (graphs.size() + 1) / 2; i += 2) {
            Row row = newRow()
                    .addPanel(graphs.get(i));
            if(i + 1 < graphs.size()) {
                row.addPanel(graphs.get(i + 1));
            }
            dashboard.addRow(row);
        }

        return dashboard;
    }
}
