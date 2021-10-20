package ru.tsitmande.gdb.modules;

import lombok.Data;
import ru.tsitmande.gdb.metrics.Metric;

import java.util.Map;

@Data
public class SimpleModule<T extends Metric> implements Module<T> {

    private final String name;
    private final Map<String, T> metrics;
}
