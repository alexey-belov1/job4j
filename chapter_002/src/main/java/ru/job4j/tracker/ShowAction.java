package ru.job4j.tracker;

import java.util.ArrayList;

public class ShowAction implements UserAction  {
    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        ArrayList<Item> items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item.getName() + " " + item.getId());
        }
        return true;
    }
}
