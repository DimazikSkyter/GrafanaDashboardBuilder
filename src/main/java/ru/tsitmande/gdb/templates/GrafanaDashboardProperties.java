package ru.tsitmande.gdb.templates;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrafanaDashboardProperties {

    private String title;
}
