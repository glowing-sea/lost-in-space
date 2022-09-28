package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the GameLevelUpRequirementClass
 */
public class GameLevelUpTest {
    State st;
    GameLevelUpRequirement require1;
    GameLevelUpRequirement require2;
    @BeforeEach
    public void setup(){
        Player player = new Player("Ben",100,100,100,new Location(1,1),0,0);


        String[] mapTest = new String[] {
                "         ",
                " X       ",
                "------ --",
                "         ",
                "-- ------",
                "         ",
                "       H ",
                "---------",
                "H        "};
        char[] testWalls = new char[] {'-'};
        Map testMap = new Map(-1,mapTest,testWalls);
        st = new State(testMap,"abc",0, player,new ArrayList<>(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        require1 = new GameLevelUpRequirement(new Location(6,7));
        require2 = new GameLevelUpRequirement(new Location(8,0));
    }
    @Test
    public void levelUpWhenReachALocation(){
        st.player.setLoc(new Location(6,7));
        assertTrue(require1.requirementSatisfied(st));
        st.player.setLoc(new Location(8,0));
        assertTrue(require2.requirementSatisfied(st));
    }
}


