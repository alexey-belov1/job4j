package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertMatrixToList {

    public List<Integer> convert(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(e -> Stream.of(e)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Integer[][] matrix = {{1, 2}, {3, 4}};
        System.out.println(new ConvertMatrixToList().convert(matrix));
    }
}
