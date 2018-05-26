package controller;

import util.DatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RegUserController {
    DatabaseHelper helper;
    public static final String DATABASE_NAME = "DB_progra_2.db";
    public static final String USER_TABLE_NAME = "User";
    public static final String USER_COLUMN_ID = "idUser";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_LASTNAME = "lastname";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_USERNAME = "userName";
    public static final String USER_COLUMN_PASS = "password";
    public static final String USER_COLUMN_BLOCKED = "blocked";

    public RegUserController(DatabaseHelper _helper) {
        helper = _helper;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db =  helper.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }


}
