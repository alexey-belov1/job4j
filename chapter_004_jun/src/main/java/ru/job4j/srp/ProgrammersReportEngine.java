package ru.job4j.srp;

import java.util.function.Predicate;

public class ProgrammersReportEngine implements Report {
    private Store store;

    public ProgrammersReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String ls = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("<html>").append(ls)
                .append("<head>").append(ls)
                .append("<title>").append("Report").append("</title>").append(ls)
                .append("<meta charset=windows-1251\">").append(ls)
                .append("</head>").append(ls)
                .append("<body>").append(ls)
                .append("<table>").append(ls)
                .append("<tr>").append(ls)
                .append("<th>").append("Name").append("</th>").append(ls)
                .append("<th>").append("Hired").append("</th>").append(ls)
                .append("<th>").append("Fired").append("</th>").append(ls)
                .append("<th>").append("Salary").append("</th>").append(ls)
                .append("</tr>").append(ls);
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>").append(ls)
                    .append("<td>").append(employee.getName()).append("</td>").append(ls)
                    .append("<td>").append(employee.getHired().getTime()).append("</td>").append(ls)
                    .append("<td>").append(employee.getFired().getTime()).append("</td>").append(ls)
                    .append("<td>").append(employee.getSalary()).append("</td>").append(ls)
                    .append("</tr>").append(ls);
        }
        text.append("</table>").append(ls)
                .append("</body>").append(ls)
                .append("</html>").append(ls);
        return text.toString();
    }
}
