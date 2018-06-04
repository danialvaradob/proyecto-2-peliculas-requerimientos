package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class RegularUser extends User implements Serializable {

    private ArrayList<Movie> favoriteMovies = new ArrayList<>();
    private String email;
    private boolean blocked = false;

    public RegularUser(String name, String lastname,String email,String username, String password) {
        super(username, password, name, lastname);
        this.email = email;
    }

    public ArrayList<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(ArrayList<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public boolean addMovie(Movie _movie) {
        if (!this.favoriteMovies.contains(_movie)) {
            this.favoriteMovies.add(_movie);
            return true;
        }
        return false;
    }

    public void blockUser() {this.blocked = true;}

    public boolean isBlocked() {return this.blocked;}

    @Override
    public String toString() {
        return super.getName() + " " + super.getLastname();
    }
}
