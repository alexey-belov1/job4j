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
        return Arrays.copyOf(items, position);
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
     * Метод возвращает заявку с указанным ключом
     * @param id - уникальный ключ
     * @return Заявка с указанным ключом
     */
    public Item findById(String id) {
        return (indexOf(id) != -1) ? (items[indexOf(id)]) : (null);
    }

    /**
     * Метод возвращает индекс в массиве с заявками по указанному ключу
     * @param id - уникальный ключ
     * @return Индекс в массиве по указанному ключу
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод заменяет заявку с указанным уникальный номером на новую. Уникальный ключ не меняется
     * @param id - уникальный ключ заявки, которую необходимо заменить
     * @param item - новая заявка
     */
    public void replace(String id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            items[index].setName(item.getName());
        } else {
            System.out.println("Нет элемента с указанным id");
        }
    }

    /**
     * Метод удаляет заявку по уникальному ключу
     * @param id - уникальный ключ заявки, которую необходимо удалить
     */
    public void delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            int start = index + 1;
            int distPos = index;
            int size = position - index;
            System.arraycopy(items, start, items, distPos, size);
            items[position] = null;
            position--;
        } else {
            System.out.println("Нет элемента с указанным id");
        }
    }
}