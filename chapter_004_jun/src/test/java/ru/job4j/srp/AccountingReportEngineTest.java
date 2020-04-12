package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import java.util.Calendar;

public class AccountingReportEngineTest {

    @Test
    public void whenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        AccountingReportEngine engine = new AccountingReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired().getTime()).append(";")
                .append(worker.getFired().getTime()).append(";")
                .append(String.format("%.2f$", 1.33)).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}