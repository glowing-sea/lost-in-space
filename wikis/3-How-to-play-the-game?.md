This game is developed by Haoting Chen, Albert Yu, William Barter, Mohan Balaji Paranthaman and Zhishang Bian. This game is developed by our own in intellij using java 18

To start, run the program base.Main in master branch gradle/src/main/java/base/Main.java

basic operation
- Type in `w`,`s`,`a`,`d` in terminal to walk
- Type the direction twice to move two unit distance at a time `ww`, `ss`, `aa`, `dd`
- Type in `fw`,`fa`,`fs`,`fd` in terminal to interact with unit that is in your w,a,s,d directions respectfully


Symbol on map
- `-` `|` `+` are walls that you cannot walk through
- `X` is you
- `E` is enemy, interact with them means fight against them
- `N` is NPC, interact with them means talk with them
- `M` is merchant, interact with them means talk with them and do trading with them
- `I` is unknown item, interact with them means pick up them and put them in your bag


Talking:
- interact with NPC
- Type in `A` or `(A)` to say option A dialogue to NPC
- Type in `B` or `(B)` to say option B dialogue to NPC
- Type in `G` to leave
- You can only move after you leave NPC 

Using-item:
- Type in `view-1` to view detail of item 1 in your bag
- Type in `use-1` to use item 1 in your bag
- Type in `use-2` to use item 2 in your bag
- Type in `drop-1` to discard item 1 in your bag
- You must have enough capacity in your bag when you collecting items from ground

Trading with merchant:
- Type in `buy-1` when talking with merchant to buy do trade 1
- Type in `G` to leave

Proceed to the next level:
- Go to the `H` point on the map
- You may need to kill specific enemies to go to the next map
- You may need to process a key item

Saving and Loading:
- Type in `save-filename` to save it in `./saves/filename.json`
- Type in `load-filename` to load a game in `./saves/filename.json`

Other commands:
- Type in `q` to leave the game
- Type in `tips` to view a simple guide
- Type in "levelup" to access next level
- Type in `quest` to see where the player should go