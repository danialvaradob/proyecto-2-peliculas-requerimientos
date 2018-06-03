package util;


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

    private static final String MOVIES_FILE_NAME = "moviesFile.txt";
    private static final String USERS_FILE_NAME = "usersFile.dat";
    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();


    private  GlobalClass global;

    public static void saveMovies(ArrayList<Movie> _movies) {
        try {
            FileOutputStream f = new FileOutputStream(new File(PATH + "/" + MOVIES_FILE_NAME));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(_movies);
            o.close();
            f.close();

        } catch (Exception e) {}

    }

    public static void saveUsers(ArrayList<RegularUser> _users) {
        try {
            FileOutputStream f = new FileOutputStream(new File(PATH + "/" + USERS_FILE_NAME));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(_users);
            o.close();
            f.close();

        } catch (Exception e) {}
    }

    public static ArrayList<Movie>loadMovies() {
        ArrayList<Movie> movies= new ArrayList<>();
        try {
            FileInputStream fi = new FileInputStream(new File(PATH + "/" + MOVIES_FILE_NAME));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            movies = (ArrayList<Movie>) oi.readObject();

            oi.close();
            fi.close();


        } catch (Exception e ) {}
        return movies;

    }
    public static ArrayList<RegularUser>loadUsers() {
        ArrayList<RegularUser> users= new ArrayList<>();
        try {
            FileInputStream fi = new FileInputStream(new File(PATH + "/" + USERS_FILE_NAME));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            users = (ArrayList<RegularUser>) oi.readObject();

            oi.close();
            fi.close();


        } catch (Exception e ) {}
        return users;

    }






}
