package ru.job4j.synch;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {

    @Test
    public void whenTransferSuccessful() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 100);
        storage.add(user1);
        storage.add(user2);
        storage.transfer(1, 2, 50);

        assertThat(user1.getAmount(), is(50));
        assertThat(user2.getAmount(), is(150));
    }

    @Test
    public void whenTransferUnsuccessful() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        storage.add(user1);
        storage.transfer(1, 2, 50);
        assertThat(user1.getAmount(), is(100));
    }
}
