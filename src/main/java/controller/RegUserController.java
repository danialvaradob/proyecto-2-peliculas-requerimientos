package controller;

import util.DatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import java.util.ArrayList;

public class RegUserController {
    DatabaseHelper helper;
    public static final String DATABASE_NAME = "DB_progra_2.db";
    public static final String USER_TABLE_NAME = "User";
    public static final String USER_COLUMN_ID = "idUser";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_LASTNAME = "lastname";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_USERNAME = "userName";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_BLOCKED = "blocked";

    public RegUserController(DatabaseHelper _helper) {
        helper = _helper;
    }

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




}
