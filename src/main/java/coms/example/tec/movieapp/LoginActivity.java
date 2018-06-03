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
import util.FileHelper;

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

        //Sets the global class. If this is not used in the Activity the app will crash when
        //using global attributes
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

        //user1
        RegularUser u1 = new RegularUser("User 1","lastname", "email","username", "pass");
        global.users.add(u1);
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
                comments,"https://image.ibb.co/ck6jay/Avengers_Infinity_War_poster.jpg","Pelicula de accion basada en lso Comics de Marvel");
        movieList.add(m1);
        //------------------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------

        Actor a3 = new Actor("Ryan","Gosling");
        Actor a4 = new Actor("Rachel","McAdams");


        ArrayList<Actor> actorsList2 = new ArrayList<>();
        actorsList.add(a3);
        actorsList.add(a4);

        Director director2 = new Director("Nick","Cassavetes");
        Genre genre2 = new Genre("Drama");

        //int id, String name, ArrayList<Actor> actorsList,
        // Director director, int yearReleased,ArrayList<Comment> comments
        Movie m2 = new Movie(3,"The Notebook",actorsList2,director2,genre2,2004,
                comments,"https://image.ibb.co/ewjtay/MV5_BMTk3_OTM5_Njg5_M15_BMl5_Ban_Bn_Xk_Ft_ZTYw_Mz_A0_ODI3_V1_UX182_CR0_0_182_268_AL.jpg",
                "Pelicula de romantica");
        movieList.add(m2);




        Admin a  = new Admin("admin1", "1234","Daniel", "Al");

        FileHelper.saveMovies(movieList);

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
            alertDialogBuilder.setMessage("BIENVENID@" + " " + global.userLoggedIn.toString());
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
