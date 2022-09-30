package tests;
import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {

    // Initialise all fields in the GameConfiguration class
    @BeforeAll
    public static void initialise(){
        GameConfiguration.initialise();
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
