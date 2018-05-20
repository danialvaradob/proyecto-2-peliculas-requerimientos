package domain;

import java.util.ArrayList;

public class RegularUser extends User {

    private ArrayList<Movie> favoriteMovies = new ArrayList<>();


    public RegularUser(String username, String password, String name, String lastname) {
        super(username, password, name, lastname);
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

}
