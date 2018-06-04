package coms.example.tec.movieapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import util.DownloadImageTask;

public class RecomendationActivity  extends AppCompatActivity implements View.OnClickListener{
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendation);
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
        TableLayout recomenTbl = (TableLayout) findViewById(R.id.RecomenTableLayout);
        TableRow newRow = new TableRow(this);
        ArrayList<Movie> movies;


        // funcion de recomendacion
        movies = recomendation(global.userLoggedIn.getFavoriteMovies());
        Collections.reverse(movies);

        int i = 0;
        TextView new_element1 = null;
        while (i<movies.size()){
            new_element1 = setNewTextView(movies, i);
            i++;

            recomenTbl.addView(new_element1);

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

    private TextView setNewTextView(List<Movie> movies, int i){
        Movie m = movies.get(i);
        TextView new_element = new TextView(this);
        new_element.setId(m.getId());

        new_element.setTag(m.getId());
        new_element.setText("Nombre: "+m.getName());
        new_element.setOnClickListener((View.OnClickListener) this);
        new_element.setLayoutParams(new TableRow.LayoutParams(300, 400));
        return new_element;
    }

    private ArrayList<Movie> recomendation (List<Movie> favs){

        ArrayList<Movie> recomen = new ArrayList<Movie>();
        if (favs.size()==0){
            int i = 0;
            TextView new_element1 = null;
            while (i<10) {
                int numero = (int) (Math.random() * global.moviesInApp.size()) + 1;
                recomen.add(global.moviesInApp.get(numero));
                i++;
            }
        }
        int i = 0;
        TextView new_element1 = null;
        while (i<10){
            int j=0;
            while(j< global.moviesInApp.size()) {
                int numero = (int) (Math.random() * favs.size()) + 1;
                if (favs.get(numero).getDirector().equals(global.moviesInApp.get(j).getDirector()) ||
                        favs.get(numero).getGenre().equals(global.moviesInApp.get(j).getGenre()) ||
                        favs.get(numero).getTags().equals(global.moviesInApp.get(j).getTags())){
                    recomen.add(global.moviesInApp.get(j));
                }
                j++;
            }
            i++;

        }

        return recomen;
    }

    @Override
    public void onClick(View view) {

    }
}


