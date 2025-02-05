package org.example;

import java.util.Objects;

public class User {
    private static long counter = 0;
    private final long id;
    private String firstName;

    public User(String firstName) {
        this.id = ++counter;
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", firstName='" + firstName + '\'' + '}';
    }
}
