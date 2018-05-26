package domain;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SystemStructure {
    private User user;
    private ArrayList<Movie> movies = new ArrayList<>();
    private util.DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    public SystemStructure() {}

    public void addUser(User _user) {
        this.user = _user;
    }




}
