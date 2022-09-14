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
    private static final String ROOT_DIR = "C:/temp";
    private static String jsonString = "{\"level\":1, \"dialogue\":\"one\", \"enemies\":[{\"name\":\"Evil\", \"hp\":10, \"atk\": 10, \"def\": 11, \"location\":{\"x\":0, \"y\":0}, \"isDead\": false}]}";
    private static String jsonStringSmall = "{\"level\":1, \"dialogue\":\"one\"}";

    public static void main(String[] args) {

        String[] map = new String[] {
                "    -----",
                "         ",
                "    +--  ",
                "    |    ",
                "    +----",
                "       A ",
                "      AHA",
                "---------",
                "         "};

        Enemy e1 = new Enemy("Evil",10, 10, 10, new Location(0,0), false);
        Enemy e2 = new Enemy("Pevil",10, 10, 10, new Location(20,10), false);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);

        Item i1 = new Item(new Location(1,1), Item_Type.EX_Boost);
        List<Item> i = new ArrayList<>();
        i.add(i1);

        Player p1 = new Player("talon", 100, 100, 100, new Location(10,20), 0, 1);

        State s = new State(new Map(0, map, new char[] {'-', '+', '|'}), p1, enemies,  "dual", 10, i);

        //writeToJSON(s);
        readFromJSON();
    }

    public static void writeToFile(String fileName, String data) {

        try {
            FileWriter fw = new FileWriter(ROOT_DIR + "/" + fileName);

            fw.write(data);

            fw.close();
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }
    }

    public static void writeToJSON(State s) {

        Map map;
        Player player;
        List<Enemy> enemies;
        int level;
        String dialogue;

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        System.out.println(gson.toJson(s));

        writeToFile("utility_testing_txt.json", gson.toJson(s));
    }

    public static void readFromJSON() {
        try {
            String line;
            String buildStr="";
            BufferedReader in = new BufferedReader(new FileReader(ROOT_DIR + "/" + "utility_testing_txt.json"));

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
                System.out.println(s.toString());
            }
            else {
                s = null;
            }

            in.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }



    class SmallerState {

        int level;
        String dialogue;
        public SmallerState(int l, String d) {
            level = l;
            dialogue = d;
        }

        public int getLevel() {
            return level;
        }

        public String getDialogue() {
            return dialogue;
        }

        public String getEnemy() {
            String jsonString = "{\"enemies\":{\"name\":\"Evil\", \"hp\":10}}";

            return jsonString;
        }
    }
}
