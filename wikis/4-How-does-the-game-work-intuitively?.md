# In `Main`

1. Load the initial game state from `GameConfiguration`.
2. Create a `Scanner` to keep listening to user inputs.
3. Print out the current game state.
3. In each loop, call `keyEventHandler` by passing through the current game state and the user's input to update the current game state.


# In `KeyEventHandler`

The function `keyEventHandler` is called when the user inputs a string command.

- For movement commands `w` `s` `a` `d` `ww` `ss` `aa` `dd`, call `public boolean move (State st, String direction, int speed)` in `Player` class.

- For interaction commands `fa` `fs` `fw` `fd` `buy-index` `A` `B` `G`, call `public static boolean interact(State st, String option, String value)` in `Player` class.

- For `save-filename`, call `public static void saveGame(State s, String filename)` in `Main`.

- For `load-filename`, call `public static void loadGame(State s, String fileName)` in `Main`.

- For `use` `drop` `view`, call `public static boolean takeOutItem(State st, String itemIndex, int action)` in `Player` class.

- For `q`, return `100` to `Main` (tells `Main` to quit the game)

- Any `invalid command` will output a message

After a user command has been processed, `keyEventHandler` continue to check

1. Whether the state is finished
2. If not, then check whether the current game state satisfy the requirement to proceed to the next level
3. If so, update the current game by changing the map, NPCs, merchant, etc. according to the initial game state of a level specified in `GameConfiguration` class.
4. Whether the player HP is 0
5. If so, return `100` to `Main` (tells `Main` to quit the game) 

In the end, return `0` to `Main` (tells `Main` to continue to listen to user's inputs and call `keyEventHandler` again)






