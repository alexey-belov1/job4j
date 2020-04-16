package ru.job4j.srp;

import java.util.function.Predicate;

public class AccountingReportEngine implements Report {
    private Store store;
    private SupportFormats formats;

    public AccountingReportEngine(Store store) {
        this.store = store;
        formats = new SupportFormats(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired().getTime()).append(";")
                    .append(employee.getFired().getTime()).append(";")
                    .append(String.format("%.2f$", employee.getSalary() / 75.0)).append(";");
        }
        return text.toString();
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
