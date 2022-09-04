package src.main;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // System.out.println("+-----------+\n0123456789\n1\n2\n3\n4\n5\n6\n7\n8\n9\n+-----------+");

        // make a map
        // make a character
        // make enemies
        // create a game state

        // create state

        // loop input *

        // update state

        // display state

        // back to *

        // if state.progress == 1, finish

        State st = new State(GameConfiguration.LEVEL0_MAP, GameConfiguration.LEVEL0_PLAYER, GameConfiguration.LEVEL0_ENEMY, GameConfiguration.LEVEL0_DIALOGUE, 0);
        System.out.println(st);

        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.next();

            switch (input){
                case "w" -> st.player.forward();
                case "s" -> st.player.backward();
                case "d" -> st.player.right();
                case "a" -> st.player.left();
            }

            if(input.equals("f")) {
                if(st.finish()){
                    break;
                }else {
                    Location current =  st.player.getLoc();
                    Location destination = GameConfiguration.LEVEL0_LEVEL_UP.getLocation();
                    System.out.println("You are in (" + current.getX() + ", " + current.getY() + "), go to: (" + destination.getX() + ", " + destination.getY() + ")!");
                }
            }
            System.out.println(st);
        } while (!Objects.equals(input, "q"));




        State st1 = new State(GameConfiguration.LEVEL1_MAP, GameConfiguration.LEVEL1_PLAYER, GameConfiguration.LEVEL1_ENEMY, GameConfiguration.LEVEL1_DIALOGUE, 1);
        System.out.println(st1);
    }
}
