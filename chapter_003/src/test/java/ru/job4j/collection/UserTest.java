package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenAsc() {
        Set<User> users = new TreeSet<>();
        users.add(new User("Petr", 32));
        users.add(new User("Ivan", 31));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Ivan", 31)));
        assertThat(it.next(), is(new User("Petr", 32)));
    }

    @Test
    public void whenSameName() {
        Set<User> users = new TreeSet<>();
        users.add(new User("Petr", 32));
        users.add(new User("Petr", 31));
        Iterator<User> it = users.iterator();
        assertThat(it.next(), is(new User("Petr", 31)));
        assertThat(it.next(), is(new User("Petr", 32)));
    }

    @Test
    public void whenComparePertVSIvan() {
        int rsl = new User("Petr", 32)
                .compareTo(
                        new User("Ivan", 31)
                );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByNameAndAge() {
        Comparator<User> cmpNameAge = new UserAscByName().thenComparing(new UserAscByAge());
        int rsl = cmpNameAge.compare(
                new User("Ivan", 32),
                new User("Ivan", 31)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByAgeAndName() {
        Comparator<User> cmpNameAge = new UserAscByAge().thenComparing(new UserAscByName());
        int rsl = cmpNameAge.compare(
                new User("Petr", 31),
                new User("Ivan", 31)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByAgeAsc() {
        Comparator<User> cmpAge = new UserAscByAge();
        int rsl = cmpAge.compare(
                new User("Ivan", 32),
                new User("Petr", 31)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByNameAsc() {
        Comparator<User> cmpName = new UserAscByName();
        int rsl = cmpName.compare(
                new User("Petr", 31),
                new User("Ivan", 32)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByAgeDesc() {
        Comparator<User> cmpAge = new UserDescByAge();
        int rsl = cmpAge.compare(
                new User("Ivan", 32),
                new User("Petr", 31)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByNameDesc() {
        Comparator<User> cmpName = new UserDescByName();
        int rsl = cmpName.compare(
                new User("Petr", 31),
                new User("Ivan", 32)
        );
        assertThat(rsl, lessThan(0));
    }
}