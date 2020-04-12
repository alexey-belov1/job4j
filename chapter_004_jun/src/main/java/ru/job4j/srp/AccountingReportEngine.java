package ru.job4j.srp;

import java.util.function.Predicate;

public class AccountingReportEngine extends SupportFormats implements Report {
    private Store store;

    public AccountingReportEngine(Store store) {
        super(store);
        this.store = store;
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
}
