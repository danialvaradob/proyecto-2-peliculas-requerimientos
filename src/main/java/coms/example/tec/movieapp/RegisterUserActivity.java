package coms.example.tec.movieapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import controller.RegUserController;
import domain.GlobalClass;
import domain.RegularUser;
import domain.User;
import util.DatabaseHelper;
import util.FileHelper;

public class RegisterUserActivity extends Activity {

    private util.DatabaseHelper DBhelper;
    private controller.RegUserController userController;

    //GUI
    private EditText name;
    private EditText lastname;
    private EditText email;
    private EditText username;
    private EditText password;
    private Button accept;

    GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        //Intent i = getIntent();
        //DatabaseHelper DBhelper = (DatabaseHelper)i.getSerializableExtra("helper");



        //
        accept = (Button) findViewById(R.id.acceptBtn);
        name = (EditText) findViewById(R.id.nameTextEdit);
        lastname = (EditText) findViewById(R.id.lastnameTextEdit);
        email = (EditText) findViewById(R.id.emailTextEdit);
        username = (EditText) findViewById(R.id.usernameTextEdit);
        password = (EditText) findViewById(R.id.passwordTextEdit);


        DBhelper = new util.DatabaseHelper(this);
        try {
            DBhelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        userController = new RegUserController(DBhelper);

        //EVENTS
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //insertConctactDB();
                 global = (GlobalClass) getApplicationContext().getApplicationContext();
                 RegularUser u = new RegularUser(name.getText().toString(),lastname.getText().toString(),
                        email.getText().toString(),username.getText().toString(),
                         password.getText().toString());
                    //global.userLoggedIn = u;

                global.users.add(u);

                //saves users
                FileHelper fh = new FileHelper();
                fh.saveUsers(global.users, RegisterUserActivity.this);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterUserActivity.this);
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("Message");
                // Icon Of Alert Dialog
                alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("REGISTRAD@!");
                alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
                alertDialogBuilder.show();






            }
        });

    }

    private void insertConctactDB() {
        if (userController.insertContact(name.getText().toString(),lastname.getText().toString(),
                email.getText().toString(),username.getText().toString(),
                password.getText().toString())) {
            System.out.print("USER INSERTED");
        }


    }








}
