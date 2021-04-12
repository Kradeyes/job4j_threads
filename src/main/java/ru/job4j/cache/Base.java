package ru.job4j.cache;

import java.util.Objects;

public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, String name, int version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 0;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Base updateVersion() {
        return new Base(id, name, version + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return getId() == base.getId()
                && getVersion() == base.getVersion()
                && Objects.equals(getName(), base.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}