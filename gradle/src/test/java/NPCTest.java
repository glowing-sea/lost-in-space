package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {
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
