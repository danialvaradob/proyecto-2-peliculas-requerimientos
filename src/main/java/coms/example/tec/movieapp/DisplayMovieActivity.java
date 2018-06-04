package coms.example.tec.movieapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;
import domain.GlobalClass;
import domain.Movie;
import util.DownloadImageTask;
import util.FileHelper;

public class DisplayMovieActivity extends AppCompatActivity {


    private TextView actors;
    private TextView director;
    private TextView genre;
    private TextView name;
    private TextView summary;
    private ImageButton favoriteMovie;


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
        favoriteMovie = (ImageButton) findViewById(R.id.favoriteBtn);

        actors.setMovementMethod(new ScrollingMovementMethod());
        summary.setMovementMethod(new ScrollingMovementMethod());


        this.loadContent();




        favoriteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add a movie to the favorites.
                global.userLoggedIn.addMovie(movieDisplayed);
                saveFavoriteMovie();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DisplayMovieActivity.this);
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("FAVORITA");
                // Icon Of Alert Dialog
                //alertDialogBuilder.setIcon(R.drawable.ic_menu_send);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("Pelicula agregada a favoritas");
                alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
                alertDialogBuilder.show();
            }
        });


    }

    private void loadContent() {
        this.loadActors();
        name.setText(this.movieDisplayed.getName());
        director.setText(this.movieDisplayed.getDirector().toString());
        genre.setText(this.movieDisplayed.getGenre().getName());
        summary.setText(this.movieDisplayed.getSummary());
        setImage();

    }

    private void setImage() {
        ImageView new_element = findViewById(R.id.movieImageView);
        //new_element.setId(this.movieDisplayed.getId());


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

    private void saveFavoriteMovie() {
        for (int i =0; i< global.users.size();i++) {
            if (global.users.get(i).getUsername().equals(global.userLoggedIn.getUsername())) {
                global.users.get(i).addMovie(movieDisplayed);
            }
        }
        FileHelper fh = new FileHelper();
        fh.saveUsers(global.users,DisplayMovieActivity.this);

    }

}
