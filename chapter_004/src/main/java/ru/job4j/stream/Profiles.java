package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        return  profiles.stream()
                    .map(profile -> profile.getAddress())
                    .sorted((Address first, Address second) -> first.getCity().compareTo(second.getCity()))
                    .distinct()
                    .collect(Collectors.toList());
    }
}
