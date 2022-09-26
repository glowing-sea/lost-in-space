package utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import base.Enemy;
import base.Map;
import base.Player;
import base.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Albert Yu
 *
 * This class contains methods that serve as utility functions for this game.
 *
 */
public class Utility {

    // Following used for testing purposes
    private static String jsonString = "{\"level\":1, \"dialogue\":\"one\", \"enemies\":[{\"name\":\"Evil\", \"hp\":10, \"atk\": 10, \"def\": 11, \"location\":{\"x\":0, \"y\":0}, \"isDead\": false}]}";
    private static String jsonStringSmall = "{\"level\":1, \"dialogue\":\"one\"}";

    /**
     * Main method used for testing purposes
     */
    public static void main(String[] args) {
        // used for testing purposes:
        //writeToJSON(s);
        //readFromJSON();
    }

    /**
     * Writes String data to a persistent file.
     * @param fileName
     * @param data
     */
    public static void writeToFile(String fileName, String data) {

        try {
            FileWriter fw = new FileWriter( fileName);

            fw.write(data);

            fw.close();
        }
        catch(IOException ioe) {
            System.err.println(ioe);
        }
    }

    /**
     * Writes a State object instance to a persistent JSON file.
     * @param fn
     * @param s
     */
    public static void writeToJSON(String fn, State s) {

        Map map;
        Player player;
        List<Enemy> enemies;
        int level;
        String dialogue;

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        writeToFile(fn, gson.toJson(s));
    }

    /**
     * Reads a persistent JSON file, and places the data onto a State object.
     * @param filename
     * @return State
     */
    public static State readFromJSON(String filename) {
        try {
            String line;
            String buildStr="";
            BufferedReader in = new BufferedReader(new FileReader(filename));

            JsonReader jr = null;
            final Type CUS_LIST_TYPE = new TypeToken<State>() {}.getType();

            Gson g = new Gson();
            try {
                jr = new JsonReader(in);
            }
            catch(Exception e) {
                System.err.println(e);
            }

            State s;

            if(jr != null ) {
                s = g.fromJson(jr, CUS_LIST_TYPE);

                in.close();
                return s;
            }
            else {
                s = null;
                in.close();
                return s;
            }
        }
        catch(Exception e) {
            System.err.println(e);
            return null;
        }
    }
}