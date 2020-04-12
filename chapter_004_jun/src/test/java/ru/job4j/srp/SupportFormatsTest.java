package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import java.util.Calendar;

public class SupportFormatsTest {

    private final String ls = System.lineSeparator();

    @Test
    public void whenGeneratedHtml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        SupportFormats engine = new SupportFormats(store);
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(ls)
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
                .append("</tr>").append(ls)
                .append("<tr>").append(ls)
                .append("<td>").append(worker.getName()).append("</td>").append(ls)
                .append("<td>").append(worker.getHired().getTime()).append("</td>").append(ls)
                .append("<td>").append(worker.getFired().getTime()).append("</td>").append(ls)
                .append("<td>").append(worker.getSalary()).append("</td>").append(ls)
                .append("</tr>").append(ls)
                .append("</table>").append(ls)
                .append("</body>").append(ls)
                .append("</html>").append(ls);
        assertThat(engine.generateHtml(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        SupportFormats engine = new SupportFormats(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(ls)
                .append("<employees>").append(ls)
                .append("<employee>").append(ls)
                .append("<name>").append(worker.getName()).append("</name>").append(ls)
                .append("<hired>").append(worker.getHired().getTime()).append("</hired>").append(ls)
                .append("<fired>").append(worker.getFired().getTime()).append("</fired>").append(ls)
                .append("<salary>").append(worker.getSalary()).append("</salary>").append(ls)
                .append("</employee>").append(ls)
                .append("</employees>").append(ls);
        assertThat(engine.generateXml(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGeneratedJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        SupportFormats engine = new SupportFormats(store);
        String ls = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("{").append(ls)
                .append("\"name\": ").append("\"").append(worker.getName()).append("\",").append(ls)
                .append("\"hired\": ").append(worker.getHired().getTime()).append(",").append(ls)
                .append("\"fired\": ").append(worker.getFired().getTime()).append(",").append(ls)
                .append("\"salary\": ").append(worker.getSalary()).append(",").append(ls)
                .append("}").append(ls);
        assertThat(engine.generateJson(em -> true), is(expect.toString()));
    }
}