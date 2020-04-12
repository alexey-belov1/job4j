package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import java.util.Calendar;

public class HrReportEngineTest {

    @Test
    public void whenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Alex", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        HrReportEngine engine = new HrReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired().getTime()).append(";")
                .append(worker2.getFired().getTime()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getHired().getTime()).append(";")
                .append(worker1.getFired().getTime()).append(";")
                .append(worker1.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}