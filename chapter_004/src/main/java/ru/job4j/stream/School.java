package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    public static List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream()
                .filter(predict)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Student> studentsList = List.of(
                new Student(10, "Petrov"),
                new Student(30, "Ivanov"),
                new Student(50, "Smirnov")
        );

        Map<String, Student> studentsMap = studentsList.stream().collect(Collectors.toMap(
                x -> x.getSurname(),
                x -> x)
        );
    }
}
