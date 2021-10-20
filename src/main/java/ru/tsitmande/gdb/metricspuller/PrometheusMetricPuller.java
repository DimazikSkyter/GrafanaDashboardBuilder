package ru.tsitmande.gdb.metricspuller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ru.tsitmande.gdb.metrics.Metric;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class PrometheusMetricPuller implements MetricPuller {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private Map<String, URI> uris;

    @Override
    public Map<String, List<Metric>> pull() {
        uris.entrySet().stream()
                .map( entry -> {
                    HttpGet request = new HttpGet("https://httpbin.org/get");
                    try {
                        CloseableHttpResponse response = httpClient.execute(request);
                        IOUtils
                        response.getEntity().getContent()
                    } catch (IOException e) {
                        log.error("Failed to pull metrics from uri {}", entry.getValue().toString(), e);
                        return null;
                    }
                    })

        con.setRequestMethod("GET");
        return null;
    }
}
