package ru.tsitmande.gdb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tsitmande.gdb.modules.SimpleModule;
import ru.tsitmande.gdb.templates.GrafanaDashboardProperties;


@Slf4j
class GrafanaDashboardPrometheusFacadeTest {

    GrafanaDashboardPrometheusFacade<ru.tsitmande.gdb.metrics.Metric> grafanaDashboardPrometheusFacade;

    @BeforeEach
    void init() {
        GrafanaDashboardProperties grafanaDashboardProperties = GrafanaDashboardProperties.builder()
                .title("Тестовый тайтл")
                .build();
        grafanaDashboardPrometheusFacade = new GrafanaDashboardPrometheusFacade<>(grafanaDashboardProperties);
    }

    @Test
    void setProperties() {
    }

    @Test
    void addGraph() {
    }

    @Test
    void generate() {
//        PrometheusMetric metric1 = new PrometheusMetric("имя 1", null, "avg(cpu)");
//        PrometheusMetric metric2 = new PrometheusMetric("имя 2", null, "max(cpu)");
//        PrometheusMetric metric3 = new PrometheusMetric("имя 3", null, "avg(ram)");
//        PrometheusMetric metric4 = new PrometheusMetric("имя 4", null, "max(ram)");
//        PrometheusMetric metric5 = new PrometheusMetric("имя 5", null, "max(ram)");
//        PrometheusMetric metric6 = new PrometheusMetric("имя 6", null, "max(ram)");
//
//
//        grafanaDashboardPrometheusFacade.addGraph(testService1).addGraph(testService2).addGraph(testService3);
//
//        Dashboard dashboard = grafanaDashboardPrometheusFacade.generate();
//        DashboardSerializer serializer = new DashboardSerializer();
//
//        log.info("dashboard is\n{}", serializer.toString(dashboard));
    }
}