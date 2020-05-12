package ru.job4j;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class MergerTest {

    @Test
    public void whenDifferentMergers() {
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

        List<User> result = new ArrayList<>(new Merger().merge(users));
        result.sort(Comparator.comparingInt(u -> u.getEmails().size()));

        Set<String> expect1 = new HashSet<>(Set.of(
                "vasya@pupkin.com",
                "xyz@pisem.net"
        ));

        Set<String> expect2 = new HashSet<>(Set.of(
                "xxx@ya.ru",
                "foo@gmail.com",
                "lol@mail.ru",
                "ups@pisem.net",
                "aaa@bbb.ru"
        ));

        assertThat(result.get(0).getName(), anyOf(equalTo("user3"), equalTo("user5")));
        assertThat(result.get(0).getEmails(), is(expect1));
        assertThat(result.get(1).getName(), anyOf(equalTo("user1"), equalTo("user2"), equalTo("user4")));
        assertThat(result.get(1).getEmails(), is(expect2));
        assertThat(result.size(), is(2));
    }

    @Test
    public void whenAllUsersHasSameEmail() {
        Set<User> users = new HashSet<>(Set.of(
                new User("user1", new HashSet<>(Set.of(
                        "xxx@ya.ru",
                        "foo@gmail.com"
                ))),
                new User("user2", new HashSet<>(Set.of(
                        "xxx@ya.ru",
                        "foo@gmail.com"
                ))),
                new User("user3", new HashSet<>(Set.of(
                        "xxx@ya.ru",
                        "foo@gmail.com"
                )))
        ));

        List<User> result = new ArrayList<>(new Merger().merge(users));

        Set<String> expect = new HashSet<>(Set.of(
                "xxx@ya.ru",
                "foo@gmail.com"
        ));

        assertThat(result.get(0).getName(), anyOf(equalTo("user1"), equalTo("user2"), equalTo("user3")));
        assertThat(result.get(0).getEmails(), is(expect));
        assertThat(result.size(), is(1));
    }

    @Test
    public void whenAllUsersHasUniqEmail() {
        Set<User> users = new HashSet<>(Set.of(
                new User("user1", new HashSet<>(Set.of(
                        "xxx@ya.ru"
                ))),
                new User("user2", new HashSet<>(Set.of(
                        "foo@gmail.com"
                ))),
                new User("user3", new HashSet<>(Set.of(
                        "lol@mail.ru"
                )))
        ));

        List<User> result = new ArrayList<>(new Merger().merge(users));
        result.sort(Comparator.comparing(User::getName));

        assertThat(result.get(0).getName(), is("user1"));
        assertThat(result.get(0).getEmails(), is(Set.of("xxx@ya.ru")));
        assertThat(result.get(1).getName(), is("user2"));
        assertThat(result.get(1).getEmails(), is(Set.of("foo@gmail.com")));
        assertThat(result.get(2).getName(), is("user3"));
        assertThat(result.get(2).getEmails(), is(Set.of("lol@mail.ru")));
        assertThat(result.size(), is(3));
    }
}
