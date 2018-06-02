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

import domain.GlobalClass;
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



    }
    private void validate(String _username,String _password) {
        /*
        global = (GlobalClass) getApplicationContext().getApplicationContext();
        if ((global.userLoggedIn.getUsername() == _username)&&(global.userLoggedIn.getPassword()==_password)) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(intent);
        }
         */
        if (searchUser(_username,_password)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
            // Setting Alert Dialog Title
            alertDialogBuilder.setTitle("Message");
            // Icon Of Alert Dialog
            alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
            // Setting Alert Dialog Message
            alertDialogBuilder.setMessage("Login Successful!");
            alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                }
            });
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
                    finish();
                }
            });
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            LoginActivity.this.startActivity(intent);

        }




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
