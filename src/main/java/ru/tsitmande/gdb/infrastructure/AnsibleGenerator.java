package ru.tsitmande.gdb.infrastructure;

import lombok.RequiredArgsConstructor;
import ru.tsitmande.gdb.metrics.Metric;
import ru.tsitmande.gdb.modules.Module;

import java.util.Map;

@RequiredArgsConstructor
public class AnsibleGenerator<T extends Metric> implements ServiceGenerator<T> {

    private final String hostIni;
    private Map<String, Module<T>> modules;

    @Override
    public Map<String, Module<T>> generateModulesFromSource() {
        if (modules == null) {
            parseHostIni();
        }
        return modules;
    }

    private void parseHostIni() {

    }
}
