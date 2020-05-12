package ru.job4j;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MergerBenchMark {

    @Benchmark
    public void merge(Blackhole bh) {
        Set<User> users = new HashSet<>(Set.of(
                new User("user1", new HashSet<>(Set.of(
                        "xxx@ya.ru",
                        "foo@gmail.com",
                        "lol@mail.ru"
                ))),
                new User("user2", new HashSet<>(Set.of(
                        "foo@gmail.com",
                        "ups@pisem.net"
                ))),
                new User("user3", new HashSet<>(Set.of(
                        "xyz@pisem.net",
                        "vasya@pupkin.com"
                ))),
                new User("user4", new HashSet<>(Set.of(
                        "ups@pisem.net",
                        "aaa@bbb.ru"
                ))),
                new User("user5", new HashSet<>(Set.of(
                        "xyz@pisem.net"
                )))
        ));

        new ArrayList<>(new Merger().merge(users));
    }

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(MergerBenchMark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}