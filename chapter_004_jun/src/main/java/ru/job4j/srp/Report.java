package ru.job4j.srp;

import java.util.function.Predicate;

public interface Report {

    String generate(Predicate<Employee> filter);

    String generateHtml(Predicate<Employee> filter);

    String generateXml(Predicate<Employee> filter);

    String generateJson(Predicate<Employee> filter);
}
