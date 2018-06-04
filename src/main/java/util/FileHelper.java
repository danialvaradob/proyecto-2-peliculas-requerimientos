package util;


import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import domain.GlobalClass;
import domain.Movie;
import domain.RegularUser;


public class FileHelper {

    private static final String MOVIES_FILE_NAME = "moviesdata.txt";
    private static final String USERS_FILE_NAME = "usersdata.txt";
    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();


    private  GlobalClass global;



    public FileHelper() {};

    public void saveMovies(ArrayList<Movie> _movies, Activity context) {
        try {
            FileOutputStream fos =
                    new FileOutputStream(new File(context.getFilesDir(), MOVIES_FILE_NAME));
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);

            oos.writeObject(_movies);
            oos.close();
            fos.close();

        } catch (Exception e) {

        }

    }



    public  void saveUsers(ArrayList<RegularUser> _users,Activity context) {
        try {
            FileOutputStream fos =
                    new FileOutputStream(new File(context.getFilesDir(), USERS_FILE_NAME));
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);

            oos.writeObject(_users);
            oos.close();
            fos.close();


        } catch (Exception e) {}
    }

    public  ArrayList<Movie>loadMovies(Activity context) {
        ArrayList<Movie> movies= new ArrayList<>();
        try {
            FileInputStream fos =
                    new FileInputStream(new File(context.getFilesDir(), USERS_FILE_NAME));
            ObjectInputStream oos =
                    new ObjectInputStream(fos);

            // Read objects
            movies = (ArrayList<Movie>) oos.readObject();

            oos.close();
            fos.close();


        } catch (Exception e ) {}
        return movies;

    }

    public  void addMovie(Movie _movie, Activity context) {
        ArrayList<Movie> movies= new ArrayList<>();
        try {
            FileInputStream fos =
                    new FileInputStream(new File(context.getFilesDir(), MOVIES_FILE_NAME));
            ObjectInputStream oos =
                    new ObjectInputStream(fos);

            // Read objects
            movies = (ArrayList<Movie>) oos.readObject();

            oos.close();
            fos.close();


        } catch (Exception e ) {}
        movies.add(_movie);
        try {
            FileOutputStream fos =
                    new FileOutputStream(new File(context.getFilesDir(), MOVIES_FILE_NAME));
            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);

            oos.writeObject(movies);
            oos.close();
            fos.close();

        } catch (Exception e) {}

    }

    public  ArrayList<RegularUser>loadUsers(Activity context) {
        ArrayList<RegularUser> users= new ArrayList<>();
        try {

            FileInputStream fos =
                    new FileInputStream(new File(context.getFilesDir(), USERS_FILE_NAME));
            ObjectInputStream oos =
                    new ObjectInputStream(fos);

            // Read objects
            users = (ArrayList<RegularUser>) oos.readObject();

            oos.close();
            fos.close();




        } catch (Exception e ) {}
        return users;

    }






}
