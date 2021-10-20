package ru.tsitmande.gdb;

import lombok.val;
import ru.tsitmande.gdb.graphs.CompositeGraphBuilder;
import ru.tsitmande.gdb.graphs.GraphProperties;
import ru.tsitmande.gdb.metrics.Metric;
import ru.tsitmande.gdb.modules.Module;
import ru.tsitmande.gdb.templates.GrafanaDashboardProperties;
import ru.tsitmande.gdb.templates.GrafanaDashboardTemplateFacade;
import uk.co.szmg.grafana.domain.Dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static uk.co.szmg.grafana.domain.DomainFactories.newDashboard;
import static uk.co.szmg.grafana.domain.DomainFactories.newRow;

public class GrafanaDashboardPrometheusFacade<T extends Metric> implements GrafanaDashboardTemplateFacade<T> {

    private final GrafanaDashboardProperties grafanaDashboardProperties;
    private final Map<String, Module<? extends Metric>> grafanaModules = new HashMap<>();
    private final List<GraphProperties> dashboardGraphs = new ArrayList<>();

    public GrafanaDashboardPrometheusFacade(GrafanaDashboardProperties grafanaDashboardProperties) {
        this.grafanaDashboardProperties = grafanaDashboardProperties;
        loadCurrentGraphs();
    }

    private void loadCurrentGraphs() {

    }


    @Override
    public GrafanaDashboardTemplateFacade<T> addGraph(Module<T> module) {
        grafanaModules.put(module.getName(), module);
        return this;
    }

    @Override
    public Dashboard generate() {
        Dashboard dashboard = newDashboard()
                .withTitle(grafanaDashboardProperties.getTitle());

        dashboardGraphs.stream()
                .map(graphProperties -> new CompositeGraphBuilder(graphProperties, grafanaModules))
                .collect(Collectors.groupingBy(CompositeGraphBuilder::getCluster, Collectors.toList()))
                .entrySet().stream()
                .map(entry -> {
                    val row = newRow()
                            .withTitle(entry.getKey());
                    entry.getValue().forEach(compositeGraphBuilder -> row.addPanel(compositeGraphBuilder.build()));
                    return row;
                })
                .forEach(dashboard::addRow);

        return dashboard;
    }
}
