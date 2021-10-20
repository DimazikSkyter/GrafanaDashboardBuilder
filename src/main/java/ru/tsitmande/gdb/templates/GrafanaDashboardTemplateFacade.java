package ru.tsitmande.gdb.templates;

import ru.tsitmande.gdb.modules.Module;
import ru.tsitmande.gdb.metrics.Metric;
import uk.co.szmg.grafana.domain.Dashboard;

public interface GrafanaDashboardTemplateFacade <T extends Metric> {

    GrafanaDashboardTemplateFacade<T> addGraph(Module<T> module);

    Dashboard generate();
}
