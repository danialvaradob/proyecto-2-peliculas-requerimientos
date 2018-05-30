package domain;

import android.app.Application;

import java.util.ArrayList;

public class GlobalClass extends Application {
    public RegularUser userLoggedIn;
    public boolean adminLogged = false;
    public ArrayList<Movie> moviesInApp = new ArrayList<>();
}
