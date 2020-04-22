package ru.job4j.lsp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ControllQuality {

    public void resort(Map<Storage, PutInStorage> map) {
        Queue<Food> foods = new LinkedList<>();
        for (Storage st : map.keySet()) {
            Food food;
            while ((food = st.poll()) != null) {
                foods.offer(food);
            }
        }

        toStorage(map, foods);
    }

    public void distribute(Map<Storage, PutInStorage> map, Food food) {
        toStorage(map, new LinkedList<>(List.of(food)));
    }

    public void distribute(Map<Storage, PutInStorage> map, List<Food> foods) {
        toStorage(map, new LinkedList<>(foods));
    }

    private void toStorage(Map<Storage, PutInStorage> map, Queue<Food> foods) {
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
