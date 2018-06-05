package coms.example.tec.movieapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.GlobalClass;
import domain.Movie;
import domain.RegularUser;
import domain.User;
import util.DownloadImageTask;

public class UsersActivity extends AppCompatActivity implements View.OnClickListener{
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        global = (GlobalClass) getApplicationContext().getApplicationContext();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        populateTable();

    }

    private void populateTable(){
        TableLayout usersTbl = (TableLayout) findViewById(R.id.UsersTableLayout);
        TableRow newRow = new TableRow(this);
        List<RegularUser> users;
        users = global.users;
        //Collections.reverse(movies);

        int i = 0;
        TextView new_element1 = null;
        while (i<users.size()){
            new_element1 = setNewTextView(users, i);
            i++;

            usersTbl.addView(new_element1);

        }
    }
    private TextView setNewTextView(List<RegularUser> users, int i){
        RegularUser u = users.get(i);
        TextView new_element = new TextView(this);
        new_element.setId(u.getId());

        new_element.setTag(u.getId());
        new_element.setText("Nombre: "+u.getName());
        new_element.setOnClickListener((View.OnClickListener) this);
        new_element.setLayoutParams(new TableRow.LayoutParams(300, 400));
        return new_element;
    }

    @Override
    public void onClick(View view) {

        System.out.println(String.valueOf(view.getId()));

        int UserId = (int)findViewById(view.getId()).getId();

        this.setBlocked(UserId);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UsersActivity.this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Message");
        // Icon Of Alert Dialog
        //alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("USUARIO BLOQUEADO");
        alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });
        alertDialogBuilder.show();


    }

    private void setBlocked(int _id) {
        ArrayList<RegularUser> users = global.users;
        RegularUser u = null;
        for (int i = 0; i < users.size();i++) {
            u = users.get(i);
            if (_id == u.getId()) {
                //global.currentMovie = m;
                u.blockUser();
                break;
            }
        }
    }

}

