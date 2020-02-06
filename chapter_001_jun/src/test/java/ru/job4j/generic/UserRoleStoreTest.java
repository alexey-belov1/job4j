package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class UserRoleStoreTest {
    UserStore<User> userStrore;

    @Before
    public void setUp() {
        userStrore = new UserStore<User>(3);
        userStrore.add(new User("User1"));
        userStrore.add(new User("User2"));
        userStrore.add(new User("User2"));
        userStrore.add(new User("User3"));
    }

    @Test
    public void whenAddUserHasSameName() {
        assertThat(userStrore.findById("User1").getId(), is("User1"));
        assertThat(userStrore.findById("User2").getId(), is("User2"));
        assertThat(userStrore.findById("User3").getId(), is("User3"));
    }

    @Test
    public void whenReplaceUser() {
        assertThat(userStrore.replace("User2", new User("User4")), is(true));
        assertThat(userStrore.findById("User1").getId(), is("User1"));
        assertThat(userStrore.findById("User4").getId(), is("User4"));
        assertThat(userStrore.findById("User3").getId(), is("User3"));
    }

    @Test
    public void whenDeleteUser() {
        assertThat(userStrore.delete("User2"), is(true));
        assertThat(userStrore.findById("User1").getId(), is("User1"));
        assertThat(userStrore.findById("User2"), is(nullValue()));
        assertThat(userStrore.findById("User3").getId(), is("User3"));
    }

}

