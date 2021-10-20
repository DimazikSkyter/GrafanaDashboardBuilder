package ru.tsitmande.gdb.infrastructure;

import ru.tsitmande.gdb.metrics.Metric;
import ru.tsitmande.gdb.modules.Module;

import java.util.List;
import java.util.Map;

public interface ServiceGenerator <T extends Metric> {

    Map<String, Module<T>> generateModulesFromSource();
}
