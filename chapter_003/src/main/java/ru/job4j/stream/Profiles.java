package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(profile -> profile.getAddress())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Profile> listProfiles = Arrays.asList(
                new Profile(
                        new Address("Ekaterinburg", "Malysheva", 23, 9)),
                new Profile(
                        new Address("Pyshma", "Krivousova", 18, 35))
        );
        System.out.println(new Profiles().collect(listProfiles));
    }
}
