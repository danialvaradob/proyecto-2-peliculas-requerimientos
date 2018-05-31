package coms.example.tec.movieapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import domain.GlobalClass;
import domain.Movie;

public class DisplayMovieActivity extends AppCompatActivity {


    private TextView actors;
    private GlobalClass global;
    private Movie movieDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);
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

        global = (GlobalClass) getApplicationContext().getApplicationContext();
        //load the movie
        movieDisplayed = global.currentMovie;


        actors = (TextView) findViewById(R.id.actorsTextView);
        actors.setMovementMethod(new ScrollingMovementMethod());

    }

}
