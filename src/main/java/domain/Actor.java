package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Actor implements Serializable {
    protected String name;
    protected String lastName;
    protected ArrayList<Movie> moviesList = new ArrayList<>();

    public Actor(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return name + " " + lastName;
    }
}
