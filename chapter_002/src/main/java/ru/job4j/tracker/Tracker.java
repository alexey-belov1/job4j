package ru.job4j.tracker;

import java.util.Random;
import java.util.Arrays;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализующий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rnd = new Random();
        return String.valueOf(System.currentTimeMillis() + rnd.nextLong());
    }

    /**
     * Метод возвращает список всех заявок
     * @return Все заявки
     */
    public Item[] findAll() {
        int size = 0;
        Item[] itemsWithoutNull = new Item[items.length];
        for (int i = 0; i < position; i++) {
            if (items[i].getId() != null) {
                itemsWithoutNull[size] = items[i];
                size++;
            }
        }
        return Arrays.copyOf(itemsWithoutNull, size);
    }

    /**
     * Метод возвращает все заявки с указанным именем
     * @param key - Имя в заявке
     * @return Все заявки с указанным именем
     */
    public Item[] findByName(String key) {
        int size = 0;
        Item[] itemsIncludeKey = new Item[items.length];
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                itemsIncludeKey[size] = items[i];
                size++;
            }
        }
        return Arrays.copyOf(itemsIncludeKey, size);
    }

    /**
     * Метод возвращает заявку с указанным ключом. Если такой заявки нет, то возвращает null
     * @param id - уникальный ключ
     * @return Заявка с указанным ключом
     */
    public Item findById(String id) {
        Item itemWithId = null;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) {
                itemWithId = items[i];
                break;
            }
        }
        return itemWithId;
    }
}