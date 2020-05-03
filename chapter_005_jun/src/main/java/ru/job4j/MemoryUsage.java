package ru.job4j;

public class MemoryUsage {

    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }
    }

    public static class BigUser {
        public String[] name;
        private final int size = 10000;

        public BigUser(String name) {
            this.name = new String[size];
            for (int i = 0; i < size; i++) {
                this.name[i] = new String(name);
            }
        }
    }

    public static void info() {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statics (MB) ####");
        System.out.println(String.format("Used Memory: %.1f", (double) (runtime.totalMemory() - runtime.freeMemory()) / mb));
        System.out.println(String.format("Free Memory: %.1f", (double) runtime.freeMemory() / mb));
        System.out.println(String.format("Total Memory: %.1f", (double) runtime.totalMemory() / mb));
        System.out.println(String.format("Max Memory: %.1f", (double) runtime.maxMemory() / mb));
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 500; i++) {
            new BigUser("test");
            //System.out.println(i);
        }
        //info();
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

    /*
            --------Эксперименты с различными GC--------

    ---2.Оценить поведения срабатывания различных типов сборщиков мусора для программы из первого урока данной модуля.---

    а) При использования памяти для heap 10MB и создании большого количества объектов
    малого размера (80байт) сборщики мусора ведут себя похожим образом,
    поскольку собирают мусор только из young generation.
    Serial и CMS запустились по одному разу, а Parallel и GC два раза.
    Разное количество запусков свзано с тем, что на момент начала выполнения программы
    было доступно разное количество свободной памяти (вероятно из-за использования разных сборщиков мусора).

    Результаты:

    -XX:+UseSerialGC:
    Used Memory: 1,6
    Free Memory: 7,5
    Total Memory: 9,7
    Max Memory: 9,7
    [0.219s][info][gc] GC(1) Pause Young (Allocation Failure) 3M->1M(9M) 1.981ms

    -XX:+UseParallelGC:
    Used Memory: 2,3
    Free Memory: 6,6
    Total Memory: 9,5
    Max Memory: 9,5
    [0.226s][info][gc] GC(1) Pause Young (Allocation Failure) 2M->1M(9M) 1.608ms
    [0.230s][info][gc] GC(2) Pause Young (Allocation Failure) 3M->1M(9M) 0.901ms

    -XX:+UseConcMarkSweepGC:
    Used Memory: 1,6
    Free Memory: 7,5
    Total Memory: 9,7
    Max Memory: 9,7
    [0.231s][info][gc] GC(1) Pause Young (Allocation Failure) 3M->1M(9M) 1.809ms

    -XX:+UseG1GC:
    Used Memory: 3,4
    Free Memory: 6,0
    Total Memory: 10,0
    Max Memory: 10,0
    [0.212s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 4M->1M(10M) 2.867ms
    [0.218s][info][gc] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 3M->1M(10M) 1.738ms


    б) При использования памяти для heap 200MB и создании большого количества
    объектов размера порядка ~0.57MB поведения сборщиков мусора в целом также похожи,
    поскольку также собирали мусор только из young generation.
    Немного отличался только G1. Он запускался реже поскольку после первой сборки
    увеличил размер памяти для молодого поколения
    (исходя из того, что сборки соответствуют установленным временным паузам).

    Результаты:

    -XX:+UseSerialGC:
    Used Memory: 4,7
    Free Memory: 116,4
    Total Memory: 121,8
    Max Memory: 193,4
    [0.236s][info][gc] GC(0) Pause Young (Allocation Failure) 33M->1M(121M) 2.782ms
    [0.246s][info][gc] GC(1) Pause Young (Allocation Failure) 35M->1M(121M) 2.098ms
    [0.256s][info][gc] GC(2) Pause Young (Allocation Failure) 35M->1M(121M) 1.675ms
    [0.265s][info][gc] GC(3) Pause Young (Allocation Failure) 35M->1M(121M) 1.630ms

    -XX:+UseParallelGC:
    Used Memory: 5,1
    Free Memory: 115,9
    Total Memory: 121,0
    Max Memory: 178,0
    [0.241s][info][gc] GC(0) Pause Young (Allocation Failure) 32M->1M(121M) 2.187ms
    [0.250s][info][gc] GC(1) Pause Young (Allocation Failure) 33M->1M(121M) 1.720ms
    [0.257s][info][gc] GC(2) Pause Young (Allocation Failure) 33M->1M(121M) 1.231ms
    [0.265s][info][gc] GC(3) Pause Young (Allocation Failure) 33M->1M(145M) 1.427ms

    -XX:+UseConcMarkSweepGC:
    Used Memory: 4,7
    Free Memory: 116,4
    Total Memory: 121,8
    Max Memory: 193,4
    [0.247s][info][gc] GC(0) Pause Young (Allocation Failure) 33M->1M(121M) 2.975ms
    [0.257s][info][gc] GC(1) Pause Young (Allocation Failure) 35M->2M(121M) 2.392ms
    [0.265s][info][gc] GC(2) Pause Young (Allocation Failure) 35M->3M(121M) 1.518ms
    [0.275s][info][gc] GC(3) Pause Young (Allocation Failure) 36M->2M(121M) 2.392ms

    -XX:+UseG1GC:
    Used Memory: 4,0
    Free Memory: 121,5
    Total Memory: 126,0
    Max Memory: 200,0
    [0.255s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 24M->1M(126M) 1.894ms
    [0.282s][info][gc] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 74M->1M(126M) 1.928ms


    ----3.Как вы считаете какой из сборщиков мусора подойдет наиболее оптимально для приложения заявок из второго модуля?----
    Зависит от того на какой машине приложение будет работать...
    а) Если на сервере с большой загруженностью, то parallel или g1.
    Достоинство g1 перед parallel в том, что он позволяет обеспечить более быстрый отклик,
    при этом может ухудшиться пропускная способность приложения.
    б) Для работы на слабой машине подойдет serial.

    ----4.Какой тип сборщика будет оптимален для сервеного приложения?----
    Оптимальным будет G1 (возможно parallel или zgc). Выбор будет зависеть от поставленных задач.
     */
}