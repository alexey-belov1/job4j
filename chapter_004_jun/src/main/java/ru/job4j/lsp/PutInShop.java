package ru.job4j.lsp;

import java.util.Date;

public class PutInShop implements PutInStorage {

    @Override
    public boolean execute(Storage storage, Food food) {
        boolean result = false;
        double relative = (double) (new Date().getTime() - food.getCreateDate().getTime())
                / (food.getExpaireDate().getTime() - food.getCreateDate().getTime());
        if (relative > 0.25 && relative <= 0.75) {
            storage.offer(food);
            result = true;
        } else if (relative > 0.75 && relative <= 1.0) {
            food.setDiscount(20);
            storage.offer(food);
            result = true;
        }

        return result;
    }
}
