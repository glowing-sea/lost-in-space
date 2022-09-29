package base;

/**
 * @param TRADE_IN  The item that the merchant accept
 * @param TRADE_OUT The item that the merchant will give to the player
 *
 * @Albert Yu: FIXME this class is not required:
 * 1. Design is not so great.
 * 2. Also, when implementing load function, these fixed final constants cause a run-time error.
 */
public record Trade(Item TRADE_IN, Item TRADE_OUT) {
}