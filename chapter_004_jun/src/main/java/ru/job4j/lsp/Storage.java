package ru.job4j.lsp;

public interface Storage {

    boolean offer(Food food);

    Food poll();
}
