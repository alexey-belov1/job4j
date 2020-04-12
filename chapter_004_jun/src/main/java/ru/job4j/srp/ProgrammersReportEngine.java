package ru.job4j.srp;

import java.util.function.Predicate;

public class ProgrammersReportEngine extends SupportFormats implements Report {
    private Store store;

    public ProgrammersReportEngine(Store store) {
        super(store);
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return generateHtml(filter);
    }
}
