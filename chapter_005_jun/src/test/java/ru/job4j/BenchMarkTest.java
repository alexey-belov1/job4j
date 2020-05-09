package ru.job4j;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class BenchMarkTest {

    @Param({"10000"})
    private int count;

    @Benchmark
    public void loopFor(Blackhole bh) {
        for (int i = 0; i < this.count; i++) {
            new BigUser("test");
        }
    }

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchMarkTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
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

}