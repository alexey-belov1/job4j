package ru.job4j;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class User
 */
public class User {

    /**
     * User name
     */
    private String name;

    /**
     * User email
     */
    private Set<String> emails;

    public User(String name) {
        this.name = name;
        this.emails = new HashSet<>();
    }

    public User(String name, Set<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return this.name;
    }

    public boolean addEmail(String mail) {
        return this.emails.add(mail);
    }

    public Set<String> getEmails() {
        return this.emails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return this.name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
