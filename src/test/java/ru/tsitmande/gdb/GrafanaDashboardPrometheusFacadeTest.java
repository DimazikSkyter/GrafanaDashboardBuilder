package ru.tsitmande.gdb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tsitmande.gdb.infrastructure.TestService;
import ru.tsitmande.gdb.metrics.Metric;
import ru.tsitmande.gdb.metrics.PrometheusMetric;
import ru.tsitmande.gdb.templates.GrafanaDashboardProperties;
import uk.co.szmg.grafana.DashboardSerializer;
import uk.co.szmg.grafana.domain.Dashboard;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class GrafanaDashboardPrometheusFacadeTest {

    GrafanaDashboardPrometheusFacade grafanaDashboardPrometheusFacade;

    @BeforeEach
    void init() {
        GrafanaDashboardProperties grafanaDashboardProperties = GrafanaDashboardProperties.builder()
                .title("Тестовый тайтл")
                .build();
        grafanaDashboardPrometheusFacade = new GrafanaDashboardPrometheusFacade(grafanaDashboardProperties);
    }

    @Test
    void setProperties() {
    }

    @Test
    void addGraph() {
        grafanaDashboardPrometheusFacade.addGraph(null);
    }

    @Test
    void generate() {
        PrometheusMetric metric1 = new PrometheusMetric("имя 1", null, "avg(cpu)");
        PrometheusMetric metric2 = new PrometheusMetric("имя 2", null, "max(cpu)");
        PrometheusMetric metric3 = new PrometheusMetric("имя 3", null, "avg(ram)");
        PrometheusMetric metric4 = new PrometheusMetric("имя 4", null, "max(ram)");
        PrometheusMetric metric5 = new PrometheusMetric("имя 5", null, "max(ram)");
        PrometheusMetric metric6 = new PrometheusMetric("имя 6", null, "max(ram)");
        TestService testService1 = new TestService("Тестовый график 1", Arrays.asList(metric1, metric2));
        TestService testService2 = new TestService("Тестовый график 2", Arrays.asList(metric3, metric4));
        TestService testService3 = new TestService("Тестовый график 3", Arrays.asList(metric5, metric6));

        grafanaDashboardPrometheusFacade.addGraph(testService1).addGraph(testService2).addGraph(testService3);

        Dashboard dashboard = grafanaDashboardPrometheusFacade.generate();
        DashboardSerializer serializer = new DashboardSerializer();

        log.info("dashboard is\n{}", serializer.toString(dashboard));
    }
}