package ru.tsitmande.gdb.infrastructure;

import org.junit.jupiter.api.Test;
import ru.tsitmande.gdb.metrics.PrometheusMetric;

import java.util.Collections;

class TestServiceTest {

    @Test
    void generateTestService() {
        PrometheusMetric prometheusMetric = new PrometheusMetric("CPU Utilization", "avg", "cpu_utils");
        TestService testService = new TestService("Test service name", Collections.singletonList(prometheusMetric));
        System.out.println(testService);
    }
}