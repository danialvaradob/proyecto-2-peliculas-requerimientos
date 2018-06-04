package domain;

import java.util.ArrayList;

public class RegularUser extends User {

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

    @Override
    public String toString() {
        return super.getName() + " " + super.getLastname();
    }
}
