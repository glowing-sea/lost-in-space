package tests;
import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;
import utility.RobotInstructions;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Haoting Chen
 */

public class NPCTest {

    State st;

    @BeforeEach
    public void setup() {
        st = GameStateExamples.TEST_STATE_ONE;
    }

    @Test
    public void readAllDialogue(){
        assertNull(st.interacting);
        String inputs = "ss fa A A G";
        RobotInstructions.inputReader(st,inputs);
        RobotInstructions.inputReader(st,inputs);
        assertNull(st.interacting);
        inputs = "fa B B G";
        RobotInstructions.inputReader(st,inputs);
        assertNull(st.interacting);
    }

    @Test
    public void sayGoodbyeImmediately(){
        assertNull(st.interacting);
        String inputs = "ss fa G";
        RobotInstructions.inputReader(st,inputs);
        assertNull(st.interacting);
    }

    @Test
    public void sayGoodbyeMidway(){
        assertNull(st.interacting);
        String inputs = "ss fa A G";
        RobotInstructions.inputReader(st,inputs);
        assertNull(st.interacting);
    }


    @Test
    public void equalNPC (){
        NPC n1 = new NPC("Jack",new Location(1,1),null);
        NPC n2 = new NPC("Jack",new Location(1,1),null);
        NPC n3 = new NPC("Amy",new Location(1,1),null);
        NPC n4 = new NPC("Amy",new Location(5,4),null);
        NPC n5 = new NPC("Amy",new Location(5,4),new String[] {"Something Different","",""});
        assertEquals(n1,n1);
        assertEquals(n1,n2);
        assertNotEquals(n1,n3);
        assertNotEquals(n1,n4);
        assertNotEquals(n1,n5);
    }

    @Test
    public void nullNPC (){
        NPC npc = new NPC(null,null,null);
        assertEquals(GameConfiguration.DEFAULT_UNIT_NAME, npc.getName());
        assertEquals(GameConfiguration.DEFAULT_UNIT_LOC, npc.getLoc());
        assertEquals(GameConfiguration.DEFAULT_NPC_DIALOGUE, npc.getDialogue());
    }

    @Test
    public void dialogueNotWellFormed1 (){
        String[] dialogue = new String[]{ // Too short
                "Hello"
        };
        assertThrows(IllegalArgumentException.class, () -> {
            new NPC(null,null,dialogue);
        });
    }

    @Test
    public void dialogueNotWellFormed2 (){
        String[] dialogue = new String[]{ // Wrong indicator
                "Bye","Bye","> Question1", "> Question2", "< Answer1", "Answer2"
        };
        assertThrows(IllegalArgumentException.class, () -> {
            new NPC(null,null,dialogue);
        });
    }
    @Test
    public void dialogueNotWellFormed3 (){
        String[] dialogue = new String[]{ // Too short
                "Bye","Bye","> Question1", "> Question2", "< Answer1"
        };
        assertThrows(IllegalArgumentException.class, () -> {
            new NPC(null,null,dialogue);
        });
    }

}
