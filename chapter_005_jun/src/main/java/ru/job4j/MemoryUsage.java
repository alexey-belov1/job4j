package ru.job4j;

public class MemoryUsage {

    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

        @Override
        public String toString() {
            return "User{"
                    + "name='" + name + '\''
                    + '}';
        }
    }

    public static void info() {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statics (MB) ####");
        System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free Memory:" + runtime.freeMemory() / mb);
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 42000; i++) {
            new User("test");
            //System.out.println(i);
        }
        info();
    }

    /*
    -------Демонстрация работы GC--------

    Один объект new User("test") в 64-х разрядной ОС занимает следующее количество памяти:

        1) new User():
        Заголовок: 16 байт;
        Ссылочная переменная:
            - String name: 4 байта;
        Выравнивание для кратности 8 : 4 байта;
        Итого: 24 байта.

        2) new String():
        Заголовок: 16 байт;
        Ссылочная переменная на объект массива:
            - byte[] value: 4 байта;
        Примитивы:
            - byte coder: 1 байт;
            - int hash: 4 байта;
        Выравнивание для кратности 8 : 7 байт;
        Итого: 32 байта.

        3) new byte[]:
        Заголовок: 16 байт;
        На длину массива: 4 байта;
        Примитивы:
            byte: 1 байт * 4 == 4 байта;
        Итого: 24 байта.

    Итого, new User("test") == 80 байт.

    Для работы программы в параметрах виртуальной машины установлено значение для heap memory 4MB (-Xmx4m).
    В программе в цикле создаются объекты new User("test") без ссылок на эти объекты.
    При нехватке памяти запускается GC и стирает из памяти объекты, не имеющие на них ссылок.
    В данном случае запуск GC осуществляется после создания ~2500 объектов.
    Ошибка java.lang.OutOfMemoryError появляется после создания ~42000 объектов.
    Ошибка возникает из-за недостатка памяти, а именно из-за того, что GC не успевает очищать достаточное количество памяти для создания новых объектов.
     */
}
