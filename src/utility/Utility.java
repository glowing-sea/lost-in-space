package utility;

import base.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    //private static final String ROOT_DIR = "C:/temp";
    private static String jsonString = "{\"level\":1, \"dialogue\":\"one\", \"enemies\":[{\"name\":\"Evil\", \"hp\":10, \"atk\": 10, \"def\": 11, \"location\":{\"x\":0, \"y\":0}, \"isDead\": false}]}";
    private static String jsonStringSmall = "{\"level\":1, \"dialogue\":\"one\"}";

    public static void main(String[] args) {
        //writeToJSON(s);
        //readFromJSON();
    }

    public static void writeToFile(String fileName, String data) {

        try {
            FileWriter fw = new FileWriter( fileName);

            fw.write(data);

            fw.close();
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }
    }

    public static void writeToJSON(String fn, State s) {

        Map map;
        Player player;
        List<Enemy> enemies;
        int level;
        String dialogue;

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        //System.out.println(gson.toJson(s));

        writeToFile(fn, gson.toJson(s));
    }

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
                s = (State) g.fromJson(jr, CUS_LIST_TYPE);
                //System.out.println(s.toString());

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
