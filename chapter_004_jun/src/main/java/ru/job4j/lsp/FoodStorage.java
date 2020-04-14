package ru.job4j.lsp;

import java.util.LinkedList;
import java.util.Queue;

public class FoodStorage implements Storage {
    private Queue<Food> foods = new LinkedList<>();

    @Override
    public boolean offer(Food food) {
        return foods.offer(food);
    }

    @Override
    public Food poll() {
        return foods.poll();
    }
}
