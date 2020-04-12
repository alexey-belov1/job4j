package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import java.util.Calendar;

public class ProgrammersReportEngineTest {

    @Test
    public void whenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ProgrammersReportEngine engine = new ProgrammersReportEngine(store);
        String ls = System.lineSeparator();
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
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}