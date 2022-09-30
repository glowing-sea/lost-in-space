package tests;
import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the GameLevelUpRequirementClass
 */
public class GameLevelUpTest {

    // Initialise all fields in the GameConfiguration class


    State st;

    @BeforeEach
    public void setup(){
        GameStateExamples.initialise();
        st = GameStateExamples.TEST_STATE_TWO;
    }
    @Test
    public void locationOnly(){
        GameLevelUpRequirement require1 = new GameLevelUpRequirement(new Location(6,7), null, null);
        GameLevelUpRequirement require2 = new GameLevelUpRequirement(new Location(8,0), null, null);
        st.player.setLoc(new Location(6,7));
        assertTrue(require1.requirementSatisfied(st));
        st.player.setLoc(new Location(8,0));
        assertTrue(require2.requirementSatisfied(st));
    }

    @Test
    public void enemyOne(){
        List<Enemy> mustKill = new ArrayList<>();
        Item reward = new Item(null,ItemType.HP_Boost);
        Enemy enemy = new Enemy("easy",10,30,30,new Location(1,0),20,reward);
        mustKill.add(enemy);
        GameLevelUpRequirement require1 = new GameLevelUpRequirement(new Location(1,1), mustKill, null);
        assertFalse(require1.requirementSatisfied(st));
        st.enemies.remove(enemy);
        assertTrue(require1.requirementSatisfied(st));
    }

    @Test
    public void enemyAll(){
        List<Enemy> mustKill = new ArrayList<>();
        Item reward = new Item(null,ItemType.HP_Boost);
        Enemy e1 = new Enemy("easy",10,30,30,new Location(1,0),20,reward);
        Enemy e2 = new Enemy("strong",10,150,50,new Location(1,2),150,reward);
        Enemy e3 = new Enemy("tough",100,1000,80,new Location(2,1),350,reward);
        mustKill.add(e1);
        mustKill.add(e2);
        mustKill.add(e3);
        GameLevelUpRequirement require1 = new GameLevelUpRequirement(new Location(1,1), mustKill, null);
        assertFalse(require1.requirementSatisfied(st));
        st.enemies.remove(e1);
        assertFalse(require1.requirementSatisfied(st));
        st.enemies.remove(e2);
        assertFalse(require1.requirementSatisfied(st));
        st.enemies.remove(e3);
        assertTrue(require1.requirementSatisfied(st));
    }
    @Test
    public void itemOnly(){
        Item key = new Item("ABC",null);
        GameLevelUpRequirement require1 = new GameLevelUpRequirement(new Location(1,1), null, key);
        assertFalse(require1.requirementSatisfied(st));
        st.player.addItem(key);
        assertTrue(require1.requirementSatisfied(st));
    }
    @Test
    public void itemAndEnemy(){
        Item key = new Item("ABC",null);
        List<Enemy> mustKill = new ArrayList<>();
        Item reward = new Item(null,ItemType.HP_Boost);
        Enemy enemy = new Enemy("easy",10,30,30,new Location(1,0),20,reward);
        mustKill.add(enemy);
        GameLevelUpRequirement require1 = new GameLevelUpRequirement(new Location(1,1), mustKill, key);
        assertFalse(require1.requirementSatisfied(st));
        st.enemies.remove(enemy);
        assertFalse(require1.requirementSatisfied(st));
        st.player.addItem(key);
        assertTrue(require1.requirementSatisfied(st));
    }
}


