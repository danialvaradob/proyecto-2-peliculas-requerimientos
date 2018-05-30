package domain;

import java.util.ArrayList;

public class Movie {
    private int id;
    private String name;
    private ArrayList<Actor> actorsList = new ArrayList<>();
    private Director director;
    private int yearReleased;
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private byte[] image;
    private String url;

    public Movie() {};

    public Movie(int id, String name, ArrayList<Actor> actorsList, Director director, int yearReleased,ArrayList<Comment> comments) {
        this.id = id;
        this.name = name;
        this.actorsList = actorsList;
        this.director = director;
        this.yearReleased = yearReleased;
        this.comments = comments;
    }

    public Movie(int id, String name, ArrayList<Actor> actorsList, Director director, int yearReleased, ArrayList<String> tags, ArrayList<Comment> comments) {
        this.id = id;
        this.name = name;
        this.actorsList = actorsList;
        this.director = director;
        this.yearReleased = yearReleased;
        this.tags = tags;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Actor> getActorsList() {
        return actorsList;
    }

    public void setActorsList(ArrayList<Actor> actorsList) {
        this.actorsList = actorsList;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getPosterURL() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
