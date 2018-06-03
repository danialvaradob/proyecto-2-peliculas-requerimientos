package coms.example.tec.movieapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.io.IOException;
import java.util.ArrayList;

import domain.Actor;
import domain.Admin;
import domain.Comment;
import domain.Director;
import domain.Genre;
import domain.GlobalClass;
import domain.Movie;
import domain.RegularUser;

public class LoginActivity extends Activity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private Switch switchAdmin;

    GlobalClass global;


    //database
    private util.DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        username = (EditText) findViewById(R.id.usernameTextEdit);
        password = (EditText) findViewById(R.id.passwordTextEdit);
        login = (Button) findViewById(R.id.loginBtn);
        register = (Button) findViewById(R.id.registerBtn);
        switchAdmin = (Switch) findViewById(R.id.adminSwitch);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterUserActivity.class);
                //myIntent.putExtra("helper", mDBHelper); //Optional parameters
                LoginActivity.this.startActivity(myIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textOff, textOn;
                textOff = (String) switchAdmin.getTextOff();
                textOn = (String) switchAdmin.getTextOn();
                validate(username.getText().toString(),password.getText().toString());

                //Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                //LoginActivity.this.startActivity(myIntent);


            }
        });

        global = (GlobalClass) getApplicationContext().getApplicationContext();

        //DBHelper created and managed
        mDBHelper = new util.DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }


        ////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////
//Actors
        Actor a1 = new Actor("Josh","Brollin");
        Actor a2 = new Actor("Scarlett","Johansson");

        ArrayList<Actor> actorsList = new ArrayList<>();
        actorsList.add(a1);
        actorsList.add(a2);

        Director director = new Director("Joe","Russo");
        Genre genre = new Genre("Action");

        //Movie, just to show it on the table
        //url movie 1 https://ibb.co/dY7CTJ
        ArrayList<Movie> movieList = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();

        //int id, String name, ArrayList<Actor> actorsList,
        // Director director, int yearReleased,ArrayList<Comment> comments
        Movie m1 = new Movie(2,"Avengers: Infinity War",actorsList,director,genre,2018,
                comments,"https://ibb.co/dY7CTJ","Pelicula de accion basada en lso Comics de Marvel");
        m1.setUrl("https://ibb.co/dY7CTJ");
        m1.setSummary("Pelicula de accion basada en lso Comics de Marvel");
        movieList.add(m1);

        Admin a  = new Admin("admin1", "1234","Daniel", "Al");

        global.moviesInApp = movieList;
        ////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////


    }
    private void validate(String _username,String _password) {
        /*
        global = (GlobalClass) getApplicationContext().getApplicationContext();
        if ((global.userLoggedIn.getUsername() == _username)&&(global.userLoggedIn.getPassword()==_password)) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(intent);
        }
         */
        //this.switchAdmin.

        if (this.switchAdmin.isChecked()) {
            if (this.adminLogin(_username,_password)) {
                global.adminLogged = true;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("Message");
                // Icon Of Alert Dialog
                //alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("LOGIN ADMINISTRADOR");
                alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
                alertDialogBuilder.show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                LoginActivity.this.startActivity(intent);
            }

        }

        else if (searchUser(_username,_password)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            // Setting Alert Dialog Title
            alertDialogBuilder.setTitle("Message");
            // Icon Of Alert Dialog
            //alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
            // Setting Alert Dialog Message
            alertDialogBuilder.setMessage("Login Successful!");
            alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            alertDialogBuilder.show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(intent);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            // Setting Alert Dialog Title
            alertDialogBuilder.setTitle("Message");
            // Icon Of Alert Dialog
            alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
            // Setting Alert Dialog Message
            alertDialogBuilder.setMessage("Login Unsuccessful!");
            alertDialogBuilder.setNegativeButton("Accept", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            alertDialogBuilder.show();

        }
    }

    private boolean adminLogin(String _username,String _password) {
        Admin admin1 = global.admin;
        if (admin1.getUsername().equals(_username) && admin1.getPassword().equals(_password)) {
            return true;
        }
        return false;
    }

    private boolean searchUser(String _username,String _password) {
        ArrayList<RegularUser> users = global.users;
        for (int i = 0; i < users.size();i++) {
            RegularUser user = users.get(i);
            if (user.getUsername().equals(_username) && user.getPassword().equals(_password)) {
                global.userLoggedIn = user;
                return true;
            }
        }
        return false;
    }




}
