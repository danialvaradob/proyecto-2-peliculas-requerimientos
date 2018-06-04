package coms.example.tec.movieapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;

import domain.Actor;
import domain.Comment;
import domain.Director;
import domain.Genre;
import domain.GlobalClass;
import domain.Movie;
import util.FileHelper;

public class AddMovieActivity extends AppCompatActivity {


    private EditText movieName;
    private EditText movieDirectorname;
    private EditText movieDirectorlastname;
    private EditText movieActorname;
    private EditText movieActorlastname;
    private EditText movieGenre;
    private EditText movieYear;
    private EditText movieSummary;
    private EditText movieURL;
    private Button addActor;
    private Button addMovie;
    private ArrayList<Actor> actors = new ArrayList<>();

    GlobalClass global;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        movieName = findViewById(R.id.movieNameTextEdit);
        movieDirectorname = findViewById(R.id.nameDirTextEdit);
        movieDirectorlastname = findViewById(R.id.lastnameDirTextEdit);
        movieActorname = findViewById(R.id.actorNameTextEdit);
        movieActorlastname = findViewById(R.id.actorLastnameTextEdit);
        movieGenre = findViewById(R.id.genreTextEdit);
        movieYear = findViewById(R.id.yearTextEdit);
        movieSummary = findViewById(R.id.summaryTextEdit);
        movieURL = findViewById(R.id.imgUrlTextEdit);

        addActor = findViewById(R.id.addActorButton);
        addMovie = findViewById(R.id.addMovieButton);

        global = (GlobalClass) getApplicationContext().getApplicationContext();

        addActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actor a = new Actor(movieActorname.getText().toString(),movieActorlastname.getText().toString());
                actors.add(a);
                movieActorname.setText("");
                movieActorlastname.setText("");


            }
        });

        addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int size = global.moviesInApp.size();
                Movie m1 = new Movie(size+1,movieName.getText().toString(),actors,new Director(movieDirectorname.getText().toString(),movieDirectorlastname.getText().toString()),
                        new Genre(movieGenre.getText().toString()),Integer.getInteger(movieYear.getText().toString()),
                        new ArrayList<Comment>(),movieURL.getText().toString(),movieSummary.getText().toString());

                FileHelper fh = new FileHelper();
                fh.addMovie(m1,AddMovieActivity.this);
                global.moviesInApp.add(m1);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddMovieActivity.this);
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("Message");
                // Icon Of Alert Dialog
                //alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("PELICULA AGREGADA A LA BD");
                alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
                alertDialogBuilder.show();
                Intent intent = new Intent(AddMovieActivity.this,MainActivity.class);
                AddMovieActivity.this.startActivity(intent);



            }
        });



    }

}
