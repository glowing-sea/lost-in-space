package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MerchantTest {
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

