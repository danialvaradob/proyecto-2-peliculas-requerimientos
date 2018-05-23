package controller;

import android.database.sqlite.SQLiteDatabase;
import domain.Movie;

public class MovieController {
    private SQLiteDatabase mDb;
    private Movie movie;


    

    public MovieController(Movie _movie, SQLiteDatabase _mDb) {
        this.movie = _movie;
        this.mDb = _mDb;
    }

    public boolean insert() {
        //insert code TODO
        return true;
    }

}
