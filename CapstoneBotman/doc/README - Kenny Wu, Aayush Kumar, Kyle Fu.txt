


VALHUNT
Authors: Kenny Wu, Aayush Kumar, Kyle Fu
Revision: 4/23/21


* 2D manhunt style, “play time”
   * Multiplayer server
   * Features can be varying complexity
      * Simple guns to lasers, shop system, goals
  
  
  

===============================================    


  



Introduction: 
Our program is a two player video game called “VALHUNT”. 
The goals of the game differ depending on the player and will oppose each other. 
The program/game is directed for people who enjoy playing small party games with each other.
The first goal (runner) is for the player to stay alive till the timer runs out and 
cross the finish line.[a] However if the runner were to be “defeated” by the hunter, 
he loses. The 2nd goal (hunter) is to stop the runner from crossing the finish line 
before time runs out. The game features multiple rounds where after each round 
the winner and loser gains a set of money (less of loser, more for winner). The money can 
be used to buy items: For each player, items that can help in combat to win rounds.[b]
The world will feature a 2d scrolling world where the world “scrolls'' 
vertically down as the timer countdown. This world will be seen with a top-down view[c].




Instructions:

Main menu
-Start Game
-How to Play
-Exit


Pause menu
-Resume
-Restart
-Back to main menu


Ingame controls
Hunter: Arrow keys to move, mouse left-click to shoot, rifle follows mouse pointer
Runner: WASD to move, space to shoot, G to rotate left, H to rotate right[d][e]
Escape to pause[f][g]




Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
* Rounds
   * Functioning Round Timer
   * First to 3 wins
   * Switch roles after each round[h]
* 2d scrolling world
   * Constantly scrolls down (sort of like Space Invaders)
   * Top-down view[i]
* Shop & cash system
   * Earn money for winning rounds, less for losing
      * Also through pickups (want-to-have)
   * Buy guns to inflict more damage[j]
   * Buy armor to protect against damage[k]
* Obstacles / Walls
   * Players can get pushed by the walls, and if a player gets pouches off screen, they “die”
      * Hunters will respawn if they “die”, but if a runner dies, the round ends and the hunter wins a point[l]
* Sprites and sound effects for players, items, etc.
   * Gun sounds
* Win condition
   * For a round, the runner needs to survive for thirty seconds to earn a point, and the hunter needs to kill the runner to earn a point
   * After one player earns 3 points, the game is over and that player wins


Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
* Multiplayer through internet (maybe with Firebase?)
* Hunter can buy three types of weapons:
   * Shotgun: many pellets, large spread, each pellet does little damage, magazine of 25 bullets, reload after 7 shots
   * Rifle: magazine of 120 bullets, reload after 30 bullets, each bullet does sufficient damage
   * Sniper: magazine of 20 bullets, reload after each bullet, one-shot kill
* Hunter respawn
   * If the hunter dies, they can respawn after a 5 second delay
   * The runner cannot respawn (hunter wins if they die)
* Powerups / Pickups
   * Get money laying on the ground
   * Pickups that heal you
* Abilities
   * Ex: Run boost, shield, invincibility for duration of time, invisibility, running through walls, etc.
* Better themed sprites for players, items, etc.
   * Japanese-themed?


Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
* Obstacle physics
   * Bullets and/or the players can slightly affect the path and rotation of obstacles
* Artificial Intelligence
   * Dumb bots to play against and boost morale
* Spectators
   * View in progress games




Class List:[m]
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]


* Main Class  - runs the program
* Sprite Class - contains the coordinate, rectangle hitboxes, images for the sprites, etc.
   * Player - abstract class representing a player
      * Hunter - extends the player class to represent the hunter
      * Runner - extends the player class to represent the runner
   * Weapon class - contains abstract methods and variables for weapons
      * Rifle - Extends from Weapon class - has own properties
      * Shotgun - Extends from Weapon class - has own properties
      * Sniper - Extends from Weapon class - has own properties
   * Obstacle - Obstacle class to block players 
   * Goal - the object for runner to reach to 
   * Bullet - Class to represent the bullets shot by the weapons
* GUI - abstract class containing helper methods to make menus[n]
   * ShopScreen - menu at the beginning of the round where both players shop for weapons[o]
   * PauseScreen - menu that shows up when the game is paused
   * MainMenu - main menu displayed when the program is launched
   * GameScreen - Screen that display the gameplay
   * InstructionScreen - Screen to display the instruction on how to play
   * WinScreen - Screen to display who won
   * Screen - Interface used to help create screens 




Credits:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
   * Kenny 
      * Sprite Package(Sprites, Weapons, etc.), sprite art assets
   * Aayush
      * Screens Package(except GameScreen) - MainScreen, Shop Screen, etc. 
   * Kyle
      * Game development(GameScreen, DrawingScreen, Main, Item classes)
   * Outside resources 
      * Processing Library - UI
      * Sound Effects - Kahoot, https://www.youtube.com/watch?v=DKoSh6J44SE, 
      	https://www.youtube.com/watch?v=zvj63k2ll4s)



