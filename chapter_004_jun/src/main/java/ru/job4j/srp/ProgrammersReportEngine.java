package ru.job4j.srp;

import java.util.function.Predicate;

public class ProgrammersReportEngine implements Report {
    private Store store;
    private SupportFormats formats;

    public ProgrammersReportEngine(Store store) {
        this.store = store;
        formats = new SupportFormats(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return formats.generateHtml(filter);
    }

    @Override
    public String generateHtml(Predicate<Employee> filter) {
        return formats.generateHtml(filter);
    }

    @Override
    public String generateXml(Predicate<Employee> filter) {
        return formats.generateXml(filter);
    }

    @Override
    public String generateJson(Predicate<Employee> filter) {
        return formats.generateJson(filter);
    }
}
