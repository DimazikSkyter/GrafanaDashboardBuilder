package ru.tsitmande.gdb;

import org.junit.jupiter.api.Test;
import uk.co.szmg.grafana.DashboardSerializer;
import uk.co.szmg.grafana.domain.Dashboard;
import uk.co.szmg.grafana.domain.Row;
import uk.co.szmg.grafana.domain.Target;

import static uk.co.szmg.grafana.domain.DomainFactories.*;

public class GrafanaDashboardGeneratorTest {

    @Test
    void test1() {
        Target target1 = newTarget()
                .withTarget("maxSeries(humidity.peti.test.sensors)");
        Target target2 = newTarget()
                .withTarget("minSeries(humidity.peti.test.sensors)");

        Row row1 = newRow()
                .withHeight("100px")
                .addPanel(newSingleStat()
                        .withTitle("Single stat test")
                        .addTarget(target1)
                        .withSpan(2))
                .addPanel(newText()
                        .withContent("<div class=\"text-center dashboard-header\"><span>This is the test</span></div>")
                        .withMode("html")
                        .withTransparent(true)
                        .withSpan(8))
                .addPanel(newSingleStat()
                        .withTitle("Single stat test")
                        .addTarget(target1)
                        .withSpan(2));

        Row row2 = newRow()
                .addPanel(newGraph()
                        .addTarget(target1)
                        .withSpan(12));

        Row row3 = newRow()
                .addPanel(newGraph()
                        .addTarget(target2)
                        .withSpan(12)
                        .withTitle("фвыфв фывыф")
                        .withLegend(newLegend().withMax(true))
                );

        Dashboard dashboard = newDashboard()
                .withTitle("Test dashboard")
                .addRow(row1)
                .addRow(row2)
                .addRow(row3);

        DashboardSerializer serializer = new DashboardSerializer();

        System.out.println(serializer.toString(dashboard));
    }
}
