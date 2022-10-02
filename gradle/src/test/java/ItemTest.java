package tests;
import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the Item Class
 * @author Haoting Chen
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
    public void equalItemsTest(){
        Item i1 = new Item(null,ItemType.ATK_Boost);
        Item i2 = new Item(null,ItemType.ATK_Boost);
        Item i3 = new Item(null,ItemType.DEF_Boost);
        Item i5 = new Item("Key To Level 2", new Location(3,5));
        Item i6 = new Item("Key To Level 1", null);
        assertEquals(i1, i1);
        assertEquals(i1, i2);
        assertNotEquals(i1, i3);
        assertNotEquals(i5, i6);
        List<Item> itemList = new ArrayList<>();
        itemList.add(i5);
        assertFalse(itemList.contains(i6));
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

}
