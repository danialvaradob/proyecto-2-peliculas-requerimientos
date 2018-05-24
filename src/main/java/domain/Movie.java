package domain;

import java.util.ArrayList;

public class Movie {
    private String name;
    private ArrayList<Actor> actorsList = new ArrayList<>();
    //private Director director;
    private int yearReleased;
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();

    public Movie(String _name, int _year) {
        this.name = _name;
        this.yearReleased = _year;
    }
}
