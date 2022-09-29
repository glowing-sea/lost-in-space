package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MerchantTest {

    @Test
    public void equalMerchant (){
        Item i1 = new Item(null, ItemType.ATK_Boost);
        Item i2 = new Item(null, ItemType.DEF_Boost);
        Merchant m1 = new Merchant("Jack", new Location(1,1),new String[]{"","",""}, Arrays.asList(new Trade(i1,i2), new Trade(i2,i1)));
        Merchant m2 = new Merchant("Jack", new Location(1,1),new String[]{"","",""}, Arrays.asList(new Trade(i1,i2), new Trade(i2,i1)));
        Merchant m3 = new Merchant("Amy", new Location(2,2),new String[]{"","",""}, Arrays.asList(new Trade(i1,i2), new Trade(i2,i1)));
        Merchant m4 = new Merchant("Amy", new Location(2,2),new String[]{"1","",""}, Arrays.asList(new Trade(i1,i2), new Trade(i2,i1)));
        Merchant m5 = new Merchant("Amy", new Location(2,2),new String[]{"1","1",""}, Arrays.asList(new Trade(i1,i2), new Trade(i2,i1)));
        Merchant m6 = new Merchant("Amy", new Location(2,2),new String[]{"1","1","1"}, Arrays.asList(new Trade(i1,i2), new Trade(i2,i1)));
        Merchant m7 = new Merchant("Amy", new Location(2,2),new String[]{"1","1","1"}, Arrays.asList(new Trade(i2,i2), new Trade(i1,i1)));
        assertEquals(m1,m1);
        assertEquals(m1,m2);
        assertNotEquals(m1,m3);
        assertNotEquals(m1,m4);
        assertNotEquals(m1,m5);
        assertNotEquals(m1,m6);
        assertNotEquals(m1,m7);
    }
    @Test
    public void nullMerchant (){
        Merchant merchant = new Merchant(null,null,null, null);
        assertEquals(GameConfiguration.DEFAULT_UNIT_NAME, merchant.getName());
        assertEquals(GameConfiguration.DEFAULT_UNIT_LOC, merchant.getLoc());
        assertEquals(GameConfiguration.DEFAULT_NPC_DIALOGUE, merchant.getDialogue());
        assertEquals(GameConfiguration.DEFAULT_TRADES, merchant.getTrades());
    }

    @Test
    public void dialogueNotWellFormed1 (){
        String[] dialogue = new String[]{ // Too short
                "Hello"
        };
        assertThrows(IllegalArgumentException.class, () -> {
            new Merchant(null,null,dialogue,null);
        });
    }

    @Test
    public void dialogueNotWellFormed2 (){
        String[] dialogue = new String[]{ // Wrong indicator
                "Bye","Bye","> Hi", "> Hi"
        };
        assertThrows(IllegalArgumentException.class, () -> {
            new Merchant(null,null,dialogue, null);
        });
    }

    @Test
    public void tradeNotWellFormed (){
        Item item = new Item(null,ItemType.HP_Boost);
        Trade trade = new Trade(item, item);
        List<Trade> trades = new ArrayList<>();
        trades.add(trade);
        trades.add(trade);
        trades.add(trade);
        trades.add(trade);
        trades.add(trade);
        trades.add(trade);
        trades.add(trade);
        assertThrows(IllegalArgumentException.class, () -> {
            new Merchant(null,null,null, trades);
        });
    }
}

