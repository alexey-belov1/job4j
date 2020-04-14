package ru.job4j.lsp;

import java.util.Date;

public class PutInTrash implements PutInStorage {

    @Override
    public boolean execute(Storage storage, Food food) {
        boolean result = false;
        double relative = (double) (new Date().getTime() - food.getCreateDate().getTime())
                / (food.getExpaireDate().getTime() - food.getCreateDate().getTime());
        if (relative > 1.0) {
            storage.offer(food);
            result = true;
        }
        return result;
    }
}
