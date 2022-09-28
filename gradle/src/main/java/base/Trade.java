package base;

/**
 * @param TRADE_IN  The item that the merchant accept
 * @param TRADE_OUT The item that the merchant will give to the player
 */
public record Trade(Item TRADE_IN, Item TRADE_OUT) {
}