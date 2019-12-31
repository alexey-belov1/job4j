package ru.job4j.tracker;

public class StartUI {

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showAllItems(Tracker tracker) {
        System.out.println("=== All Items ====");
        Item[] item = tracker.findAll();
        for (int i = 0; i < item.length; i++) {
            System.out.println(item[i].getName() + " " + item[i].getId());
        }
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Edit item ====");
        String id = input.askStr("Enter id selected item: ");
        String name = input.askStr("Enter new name: ");
        if (tracker.replace(id, new Item(name))) {
            System.out.println("Replace completed successfully!");
        } else {
            System.out.println("Replace was failed!");
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete Item ====");
        String id = input.askStr("Enter id selected item: ");
        if (tracker.delete(id)) {
            System.out.println("Delete completed successfully!");
        } else {
            System.out.println("Delete was failed!");
        }
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find item by id ====");
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(item.getName() + " " + item.getId());
        }
    }

    public static void findItemByName(Input input, Tracker tracker) {
        System.out.println("=== Find item by name ====");
        String name = input.askStr("Enter name: ");
        Item[] item = tracker.findByName(name);
        for (int i = 0; i < item.length; i++) {
            System.out.println(item[i].getName() + " " + item[i].getId());
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = input.askInt("");
            if (select == 0) {
                StartUI.createItem(input, tracker);
            } else if (select == 1) {
                StartUI.showAllItems(tracker);
            } else if (select == 2) {
                StartUI.replaceItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.findItemById(input, tracker);
            } else if (select == 5) {
                StartUI.findItemByName(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}