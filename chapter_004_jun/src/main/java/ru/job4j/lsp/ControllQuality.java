package ru.job4j.lsp;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ControllQuality {

    public void redistribute(Map<Storage, PutInStorage> map) {
        Queue<Food> foods = new LinkedList<>();
        for (Storage st : map.keySet()) {
            Food food;
            while ((food = st.poll()) != null) {
                foods.offer(food);
            }
        }

        Food food;
        while ((food = foods.poll()) != null) {
            for (Storage storage : map.keySet()) {
                if (map.get(storage).execute(storage, food)) {
                    break;
                }
            }
        }
    }
}
