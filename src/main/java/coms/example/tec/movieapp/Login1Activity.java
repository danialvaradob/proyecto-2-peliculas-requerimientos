package coms.example.tec.movieapp;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class Login1Activity extends Activity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;


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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Login1Activity.this, RegisterUserActivity.class);
                //myIntent.putExtra("helper", mDBHelper); //Optional parameters
                Login1Activity.this.startActivity(myIntent);
            }
        });

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
        if (true) {
            Intent intent = new Intent(Login1Activity.this,MainActivity.class);
        }

    }




}
