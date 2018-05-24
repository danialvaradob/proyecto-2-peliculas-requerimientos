package controller;

import android.database.sqlite.SQLiteDatabase;
import domain.Movie;

public class MovieController {
    private SQLiteDatabase mDb;
    private Movie movie;
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String MOVIES_TABLE_NAME = "Movie";
    public static final String MOVIES_COLUMN_ID = "id";
    public static final String MOVIES_COLUMN_NAME = "name";
    public static final String MOVIES_COLUMN_ID_DIRECTOR = "idDirector";
    public static final String MOVIES_COLUMN_ID_GENRE = "idGenre";
    public static final String MOVIESCOLUMN_YEAR = "year";
    public static final String MOVIES_COLUMN_REVIEW = "review";



    public MovieController(Movie _movie, SQLiteDatabase _mDb) {
        this.movie = _movie;
        this.mDb = _mDb;
    }

    public boolean insert() {
        //insert code TODO
        return true;
    }

    //CONTACTS

}
