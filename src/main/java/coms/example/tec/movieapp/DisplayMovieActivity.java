package coms.example.tec.movieapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;
import domain.GlobalClass;
import domain.Movie;
import util.DownloadImageTask;

public class DisplayMovieActivity extends AppCompatActivity {


    private TextView actors;
    private TextView director;
    private TextView genre;
    private TextView name;
    private TextView summary;


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
        director = (TextView) findViewById(R.id.directorTextView);
        genre = (TextView) findViewById(R.id.genreTextView);
        name = (TextView) findViewById(R.id.movieNameTextView);
        summary = (TextView) findViewById(R.id.summaryTextView4);

        actors.setMovementMethod(new ScrollingMovementMethod());
        summary.setMovementMethod(new ScrollingMovementMethod());


        this.loadContent();



    }

    private void loadContent() {
        this.loadActors();
        name.setText(this.movieDisplayed.getName());
        director.setText(this.movieDisplayed.getDirector().toString());
        genre.setText(this.movieDisplayed.getGenre().getName());
        summary.setText(this.movieDisplayed.getSummary());

    }

    private void setImage(View view) {
        ImageView new_element = new ImageView(this);
        new_element.setId(this.movieDisplayed.getId());

        new DownloadImageTask(new_element)
                .execute(this.movieDisplayed.getPosterURL());
        new_element.setTag(this.movieDisplayed.getId());

    }

    private void loadActors() {
        ArrayList<Actor> actorsList = this.movieDisplayed.getActorsList();
        for (int i = 0; i < actorsList.size(); i++) {
            this.actors.append(actorsList.get(i).getName() + " " + actorsList.get(i).getLastName() + "\n");
        }
    }

    private ImageView setNewView(List<Movie> movies, int i){
        Movie m = movies.get(i);
        ImageView new_element = new ImageView(this);
        new_element.setId(m.getId());

        new DownloadImageTask(new_element)
                .execute(m.getPosterURL());
        new_element.setTag(m.getId());

        new_element.setOnClickListener((View.OnClickListener) this);
        new_element.setLayoutParams(new TableRow.LayoutParams(300, 400));
        return new_element;
    }



}
