package controller;

import android.database.Cursor;
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
    public static final String MOVIES_COLUMN_YEAR = "year";
    public static final String MOVIES_COLUMN_REVIEW = "review";
    public static final String MOVIES_COLUMN_SYNOPSIS = "synopsis";
    public static final String MOVIES_COLUMN_IMAGE = "image";



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
    public Cursor getMovieDataByName(String _name) {
        //TODO actors missing
        SQLiteDatabase db =  helper.getReadableDatabase();
        String[] param = new String[] {_name};
        Cursor res =  db.rawQuery( "select m.idMovie, m.name, d.name as \"directorName\",g.name as \"genreName\" ,m.year,m.synopsis,m.image " +
                "from Movie m inner join Director d on m.idDirector = d.idDirector " +
                "inner join Genre g on m.idGenre = g.idGenre\n" +
                "where m.name=?", param);
        return res;

    }

    public Cursor getMovieDataByID(int id) {
        //TODO actors missing
        SQLiteDatabase db =  helper.getReadableDatabase();
        String[] param = new String[] {Integer.toString(id)};
        Cursor res =  db.rawQuery( "select m.idMovie, m.name, d.name as \"directorName\",g.name as \"genreName\" ,m.year,m.synopsis,m.image " +
                "from Movie m inner join Director d on m.idDirector = d.idDirector " +
                "inner join Genre g on m.idGenre = g.idGenre\n" +
                "where m.idMovie=?", param);
        return res;
    }


    public Movie getMovieById(int _id) {
        Cursor rs = getMovieDataByID(_id);

        //id_To_Update = Value;
        if (rs != null)
            rs.moveToFirst();

        String id = rs.getString(rs.getColumnIndex(MOVIES_COLUMN_ID));
        String name = rs.getString(rs.getColumnIndex(MOVIES_COLUMN_NAME));
        String director = rs.getString(rs.getColumnIndex(MOVIES_COLUMN_ID_DIRECTOR));
        String genre = rs.getString(rs.getColumnIndex(MOVIES_COLUMN_ID_GENRE));
        String year = rs.getString(rs.getColumnIndex(MOVIES_COLUMN_YEAR));
        String synopsis = rs.getString(rs.getColumnIndex(MOVIES_COLUMN_SYNOPSIS));
        String image = rs.getString(rs.getColumnIndex(MOVIES_COLUMN_IMAGE));

        //if (!rs.isClosed())  {
        //    rs.close();
        //}

        Movie m = new Movie();
        return m;
    }

    /*
    public Cursor getRegularUserData(int id) {
        SQLiteDatabase db =  helper.getReadableDatabase();
        Cursor res =  db.rawQuery( "select u.userName,u.password from User u where id="+id+"", null );
        return res;
    }

    public boolean insertContact (String _name, String _lastname, String _email, String _username,
                                  String _password) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_NAME, _name);
        contentValues.put(USER_COLUMN_LASTNAME, _lastname);
        contentValues.put(USER_COLUMN_EMAIL, _email);
        contentValues.put(USER_COLUMN_USERNAME, _username);
        contentValues.put(USER_COLUMN_PASSWORD, _password);
        db.insert(USER_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<String> getAllusernames() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor res =  db.rawQuery( "select u.username from User u", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_USERNAME)));
            res.moveToNext();
        }
        return array_list;
    }



     */

    //CONTACTS

}
