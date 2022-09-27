package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the Item Class
 */
public class ItemTest {
    State st;

    @BeforeEach
    public void setup(){
        Item i1 = new Item(new Location(-1,-1), ItemType.HP_Boost);
        Item i2 = new Item(new Location(-1,-1), ItemType.ATK_Boost);
        Item i3 = new Item(new Location(-1,-1), ItemType.DEF_Boost);
        Item i4 = new Item(new Location(-1,-1), ItemType.EXP_Boost);
        Item i5 = new Item(new Location(-1,-1), ItemType.Inventory_Boost);

        Player player = new Player("Ben",100,100,100,new Location(1,1),0,0);
        player.addItem(i1);
        player.addItem(i2);
        player.addItem(i3);
        player.addItem(i4);
        player.addItem(i5);

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
    }


    @Test
    public void useItemTest(){
        st.player.getInventory().get(0).useItem(st);
        st.player.getInventory().get(1).useItem(st);
        st.player.getInventory().get(2).useItem(st);
        st.player.getInventory().get(3).useItem(st);
        st.player.getInventory().get(4).useItem(st);

        assertEquals(120, st.player.getHp());
        assertEquals(120, st.player.getAtk());
        assertEquals(120, st.player.getDef());
        assertEquals(20, st.player.getExp());
        assertEquals(8, st.player.getCapacity());
    }

    @Test
    public void interact(){
        st.player.getInventory().get(0).setValid(false);
        st.player.getInventory().get(1).setValid(false);
        assertFalse(st.player.getInventory().get(0).interact(st));
        assertFalse(st.player.getInventory().get(0).interact(st));
    }
}
