package coms.example.tec.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Actor;
import domain.Comment;
import domain.Director;
import domain.Genre;
import domain.GlobalClass;
import domain.Movie;
import util.DownloadImageTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private GlobalClass global;
    private ArrayList<Movie> movies  = new ArrayList<>();
    private TableLayout table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        global = (GlobalClass) getApplicationContext().getApplicationContext();

        this.table = findViewById(R.id.movieTableLayout);

        if (!global.adminLogged) {
            Menu menu = navigationView.getMenu();
            MenuItem nav_item_editmovies = menu.findItem(R.id.nav_editmovies);
            MenuItem nav_item_blockusers = menu.findItem(R.id.nav_blockusers);
            MenuItem nav_item_managemovies = menu.findItem(R.id.nav_editmovies);
            //usage
            nav_item_editmovies.setEnabled(false);
            nav_item_blockusers.setEnabled(false);
            nav_item_managemovies.setEnabled(false);
            //visibility
            nav_item_editmovies.setVisible(false);
            nav_item_blockusers.setVisible(false);
            nav_item_managemovies.setVisible(false);

        } else {
            Menu menu = navigationView.getMenu();
            MenuItem nav_item_favs = menu.findItem(R.id.nav_favoritemovies);
            MenuItem nav_item_recom = menu.findItem(R.id.nav_recomendations);
            //usage
            nav_item_favs.setEnabled(false);
            nav_item_recom.setEnabled(false);
            //visibility
            nav_item_favs.setVisible(false);
            nav_item_recom.setVisible(false);


        }

        //Actors
        Actor a1 = new Actor("Josh","Brollin");
        Actor a2 = new Actor("Scarlett","Johansson");

        ArrayList<Actor> actorsList = new ArrayList<>();
        actorsList.add(a1);
        actorsList.add(a2);

        Director director = new Director("Joe","Russo");
        Genre genre = new Genre("Action");

        //Movie, just to show it on the table
        //url movie 1 https://ibb.co/dY7CTJ
        ArrayList<Movie> movieList = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();

        //int id, String name, ArrayList<Actor> actorsList,
        // Director director, int yearReleased,ArrayList<Comment> comments
        Movie m1 = new Movie(2,"Avengers: Infinity War",actorsList,director,genre,2018,
                comments,"https://ibb.co/dY7CTJ","Pelicula de accion basada en lso Comics de Marvel");
        m1.setUrl("https://ibb.co/dY7CTJ");
        m1.setSummary("Pelicula de accion basada en lso Comics de Marvel");
        movieList.add(m1);


        //global.moviesInApp = movieList;
        //this.movies = global.moviesInApp;

        this.movies =  movieList;
        this.populateTable();

        //cambiar esto, para que cuando seleccione la pelicula, la seleccionada tome el ID y obtenga la pelicula que es
        //y cambie el currentMovie
        global.currentMovie = m1;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_startscren) {
            //Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
            //MainActivity.this.startActivity(myIntent);\
            Intent myIntent = new Intent(MainActivity.this, DisplayMovieActivity.class);
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_blockusers) {

        } else if (id == R.id.nav_editmovies) {

        } else if (id == R.id.nav_favoritemovies) {
            Intent myIntent = new Intent(MainActivity.this, FavMoviesActivity.class);
            MainActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_recomendations) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void populateTable(){

        TableRow newRow = new TableRow(this);
        //List<Movie> movies;
        //movies = global.moviesInApp;
        Collections.reverse(this.movies);

        int i = 0;
        TextView new_element1 = null;
        while (i<this.movies.size()){
            System.out.print("NOMBRE   "+this.movies.get(i).getName());
            new_element1 = setNewTextView(this.movies, i);
            i++;

            this.table.addView(new_element1);

        }
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

    @Override
    public void onClick(View view) {
        Intent detail_screen = new Intent(this, DisplayMovieActivity.class);
        System.out.println(String.valueOf(view.getId()));
        //System.out.println(R.id.main_imgReco1);
        int MovieId = (int)findViewById(view.getId()).getTag();
        //System.out.println("TAG " + MovieId);

        //set Movie
        this.setMovie(MovieId);

        detail_screen.putExtra("MovieId", String.valueOf(MovieId));
        startActivity(detail_screen);
    }

    private void setMovie(int _id) {
        ArrayList<Movie> movies = global.moviesInApp;
        Movie m = null;
        for (int i = 0; i < movies.size();i++) {
            m = movies.get(i);
            if (_id == m.getId()) {
                global.currentMovie = m;
                break;
            }
        }
    }




    private void searchMovie(String _criteria) {
        ArrayList<Movie> moviesFound = new ArrayList<>();
        //for (int i = 0;i < )
    }


}
