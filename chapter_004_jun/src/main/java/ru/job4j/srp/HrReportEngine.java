package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

public class HrReportEngine implements Report {
    private Store store;

    public HrReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        List<Employee> employees = store.findBy(filter);
        employees.sort((x1, x2) -> Double.compare(x2.getSalary(), x1.getSalary()));

        for (Employee employee : employees) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired().getTime()).append(";")
                    .append(employee.getFired().getTime()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}
