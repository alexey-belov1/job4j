package ru.job4j.nonblock;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    ConcurrentHashMap<Integer, Base> bases = new ConcurrentHashMap<>();

    public void add(Base model) {
        this.bases.put(model.getId(), model);
    }

    public boolean update(Base model) throws OptimisticException {
        boolean result = true;

        Base base = this.bases.computeIfPresent(model.getId(),
                (key, value) -> {
                    if (value.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    model.setVersion(model.getVersion() + 1);
                    return model;
                });

        if (base == null) {
            result = false;
        }

        return result;
    }

    public boolean delete(Base model) {
        return this.bases.remove(model.getId(), model);
    }

    public Base get(Integer index) {
        Base temp = this.bases.get(index);
        Base result = null;
        if (temp != null) {
            result = new Base(temp.getId(), temp.getName());
            result.setVersion(temp.getVersion());
        }
        return result;
    }
}
