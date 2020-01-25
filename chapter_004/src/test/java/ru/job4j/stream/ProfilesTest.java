package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenSortAndEqualsAddress() {
        List<Profile> listProfiles = Arrays.asList(
                new Profile(
                        new Address("Pyshma", "Krivousova", 18, 35)),
                new Profile(
                        new Address("Ekaterinburg", "Malysheva", 23, 9)),
                new Profile(
                        new Address("Ekaterinburg", "Malysheva", 23, 9))
        );

        List<Address> expected = Arrays.asList(
                new Address("Ekaterinburg", "Malysheva", 23, 9),
                new Address("Pyshma", "Krivousova", 18, 35)
        );
        assertThat(new Profiles().collect(listProfiles), is(expected));
    }
}
