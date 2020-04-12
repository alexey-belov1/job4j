package ru.job4j.srp;

import java.util.function.Predicate;

public class SupportFormats {
    private Store store;
    private final String ls = System.lineSeparator();

    public SupportFormats(Store store) {
        this.store = store;
    }

    public String generateHtml(Predicate<Employee> filter) {
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

    public String generateXml(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
            .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(ls)
            .append("<employees>").append(ls);
        for (Employee employee : store.findBy(filter)) {
            text.append("<employee>").append(ls)
                    .append("<name>").append(employee.getName()).append("</name>").append(ls)
                    .append("<hired>").append(employee.getHired().getTime()).append("</hired>").append(ls)
                    .append("<fired>").append(employee.getFired().getTime()).append("</fired>").append(ls)
                    .append("<salary>").append(employee.getSalary()).append("</salary>").append(ls)
                    .append("</employee>").append(ls);
        }
        text.append("</employees>").append(ls);
        return text.toString();
    }

    public String generateJson(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append("{").append(ls)
                    .append("\"name\": ").append("\"").append(employee.getName()).append("\",").append(ls)
                    .append("\"hired\": ").append(employee.getHired().getTime()).append(",").append(ls)
                    .append("\"fired\": ").append(employee.getFired().getTime()).append(",").append(ls)
                    .append("\"salary\": ").append(employee.getSalary()).append(",").append(ls)
                    .append("}").append(ls);
        }
        return text.toString();
    }
}
