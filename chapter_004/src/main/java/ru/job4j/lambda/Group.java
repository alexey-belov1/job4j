package ru.job4j.lambda;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Group {

    private static class Holder {
        String name, unit;

        Holder(String name, String unit) {
            this.name = name;
            this.unit = unit;
        }
    }

    public static Map<String, Set<String>> sections(List<Student> students) {
        return students.stream()
                .flatMap(x -> x.getUnits()
                        .stream()
                        .map(el -> new Holder(x.getName(), el))
                )
                .collect(
                        Collectors.groupingBy(x -> x.unit,
                             Collector.of(
                                  HashSet::new,
                                  (set, el) -> set.add(el.name),
                                  (left, right) -> {
                                      left.addAll(right);
                                      return left;
                                  }
                             )
                        )
                );
    }

    public static void main(String[] args) {
        var sectionMap = Group.sections(List.of(
                new Student("Kolya", Set.of("Tennis", "Footbal", "Chess")),
                new Student("Vasya", Set.of("Box", "Footbal", "Chess")),
                new Student("Sasha", Set.of("Tennis", "Basketball", "Volleybal"))
        ));
        System.out.println(sectionMap);
    }
}