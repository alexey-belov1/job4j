package ru.job4j.generic;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> simpleArray;

    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<T>(size);
    }

    @Override
    public void add(T model) {
        if (getIndex(model.getId()) == -1) {
            this.simpleArray.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        int index = getIndex(id);
        return (index != -1) && this.simpleArray.set(index, model);
    }

    @Override
    public boolean delete(String id) {
        int index = getIndex(id);
        return (index != -1) && this.simpleArray.remove(index);
    }

    @Override
    public T findById(String id) {
        int index = getIndex(id);
        return (index != -1) ? this.simpleArray.get(index) : null;
    }

    private int getIndex(String id) {
        int result = -1;
        int index = 0;
        for (T t : simpleArray) {
            if (t.getId().equals(id)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }
}
