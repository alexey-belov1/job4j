package ru.job4j;

import java.util.Arrays;
import java.util.List;

public class NoName {
    public static void badMethod() throws Exception {
    }

    public interface Wrapper<T> {
        T get();
        void set(T value);
        boolean isEmpty();
    }

    public static class ExpHold<T> implements Wrapper<T> {

        private T value;

        @Override
        public T get() {
            return this.value;
        }

        @Override
        public void set(T value) {
            this.value = value;
        }

        @Override
        public boolean isEmpty() {
            return this.value == null;
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        Wrapper<Exception> ex = new ExpHold<>();
        names.forEach(
                n ->  {
                    try {
                        badMethod();
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        if (!ex.isEmpty()) {
            throw ex.get();
        }

        //Первая часть задания
        final StringBuilder last = new StringBuilder();
        names.forEach(
                n ->  {
                    last.ensureCapacity(0);
                    last.append(n);
                }
        );
    }
}

