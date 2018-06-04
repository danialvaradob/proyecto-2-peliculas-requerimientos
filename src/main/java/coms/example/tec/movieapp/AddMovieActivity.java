package coms.example.tec.movieapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import domain.Actor;

public class AddMovieActivity extends AppCompatActivity {


    private EditText movieName;
    private EditText movieDirectorname;
    private EditText movieDirectorlastname;
    private EditText movieActorname;
    private EditText movieActorlastname;
    private EditText movieGenre;
    private EditText movieYear;
    private EditText movieSummary;
    private Button addActor;
    private Button addMovie;


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

        addActor = findViewById(R.id.addActorButton);
        addMovie = findViewById(R.id.addMovieButton);

        ArrayList<Actor> actors = new ArrayList<>();

        addActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

}
