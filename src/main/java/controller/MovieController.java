package controller;

import android.database.sqlite.SQLiteDatabase;
import domain.Movie;
import util.DatabaseHelper;

public class MovieController {
    DatabaseHelper helper;
    private Movie movie;
    public static final String DATABASE_NAME = "BD_progra_reque_2";
    public static final String MOVIES_TABLE_NAME = "Movie";
    public static final String MOVIES_COLUMN_ID = "id";
    public static final String MOVIES_COLUMN_NAME = "name";
    public static final String MOVIES_COLUMN_ID_DIRECTOR = "idDirector";
    public static final String MOVIES_COLUMN_ID_GENRE = "idGenre";
    public static final String MOVIESCOLUMN_YEAR = "year";
    public static final String MOVIES_COLUMN_REVIEW = "review";


    public MovieController(DatabaseHelper _helper) {
        helper = _helper;
    }

    public void setMovie(Movie _movie) {
        this.movie = _movie;
    }

    public boolean insert() {
        //insert code TODO
        return true;
    }

    //CONTACTS

}
