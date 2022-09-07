package src.main;
import java.util.Objects;
import java.util.Scanner;

/**
 * This the main class where the game runs.
 */

public class Main {
    public static void main(String[] args) {

        // Get and print the initial game state from starr
        State state = GameConfiguration.starr[0];
        System.out.println(state);

        // Set up the scanner
        Scanner scanner = new Scanner(System.in);
        String input;

        // keep taking key input until a "q" is found.
        do {
            input = scanner.next();

            // Update the game state according to the inputs
            switch (input){
                case "w" -> state.player.forward(state.map);
                case "s" -> state.player.backward(state.map);
                case "d" -> state.player.right(state.map);
                case "a" -> state.player.left(state.map);
                case "i" -> getDestination(state);
                case "f" -> interact(state);
            }

            // If the requirement to moving to the next game level is satisfied, move to the next game level.
            if (GameConfiguration.LEVEL0_LEVEL_UP.requirementSatisfied(state))
                state.gameLevelUp(GameConfiguration.starr[state.level + 1]);
            if (GameConfiguration.LEVEL1_LEVEL_UP.requirementSatisfied(state))
                state.gameLevelUp(GameConfiguration.starr[state.level + 1]);
            // Print out the updated game state
            System.out.println(state);
        } while (!Objects.equals(input, "q"));
    }

    //This will be useful! don't delete it
    public static void interact(State st){
        // In the future, this function contains the interaction with different object;

        //interact with enemies nearby you!
        if(st.enemies != null && !st.enemies.isEmpty()) { //there exist enemies

            for (Enemy enemy : st.enemies) {
                // if an enemy is nearby player, fight against it
                if (st.player.nearby(enemy.getLoc())) {
                    while (!enemy.isDead) { //fight until death!

                        int enemyatk = st.player.getDef() - enemy.getAtk();// enemy attack you
                        if(enemyatk <0) {st.player.setHp(st.player.getHp() + enemyatk);}

                        int youratk = enemy.getDef() - st.player.getAtk(); // you attack enemy
                        if(youratk <0) {enemy.setHp(enemy.getHp() + youratk);}

                        System.out.println(enemy.getHp());
                        if (enemy.getHp() <= 0) {
                            enemy.isDead = true;
                            refreshLocation(st,enemy.getLoc(),' ');
                            System.out.println("you win! left hp: "+st.player.getHp());
                        }
                    }
                    Location tomb = new Location(-1, -1); //sent enemies to tomb, get out of my way
                    enemy.setLoc(tomb);
                }
            }
        }
        //fixme interact with inventory player can interact with
    }


    public static void getDestination(State st){
        Map map = st.map;
        Location current =  st.player.getLoc(); //player location
        Location destination = new Location(-1, -1); //avoid exception
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap()[i].length(); j++) {
                if (map.getMap()[i].charAt(j) == 'H') {
                    destination = new Location(i, j);
                }
            }
        }
        System.out.println("You are in (" + current.getX() + ", " + current.getY() + "), go to: (" + destination.getX() + ", " + destination.getY() + ")!");

    }
    /**
     * input location, the char you will replace with
     * output replace the location with the char you set
     * @param location
     * @param newchar
     * @param st
     */
    // usage: kill enemies
    public static void refreshLocation(State st, Location location, char newchar){ // entre the new string and location where you want the string to be
        int X = location.getX();
        int Y = location.getY();
        Map map = st.map;
        if(X<0||X>8||Y<0||Y>8){
            throw new NullPointerException("this is <0 or >8");
        }else{
//            Map newmap = new Map(this.mapID,this.map,this.walls);
            String[] thenew = map.getMap();
            char[] newline = thenew[X].toCharArray();
            newline[Y] = newchar;
            thenew[X] = String.valueOf(newline);
            st.map = new Map(map.getMapID(),thenew,map.getWalls());

        }
    }
}
