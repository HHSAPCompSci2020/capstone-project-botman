AP Computer Science Final Project - README Template


Instructions:
The first step in creating an excellent APCS final project is to write up a README. At this stage, this README file acts as your project proposal. Once you’ve filled in all components, Shelby will read through it and suggest edits. Ultimately, you need a document that adequately describes your project idea and we must agree on this plan.


Have one member of your group make a copy of this Google Doc. Then, they should share it with all other members so that every group member has edit permissions.


There’s a lot of parts of this document that you might not have full answers for yet. Because you haven’t written the program yet, it’s difficult to think about the instructions or which group members will do which parts. Even though this is hard to think about, you must have something in these sections that acts as your current plan. However, during the course of the project, you’ll continuously update this document. This means that you will not be held to exactly what you put here - components of this document can change (and it’s pretty common!).


There is one exception: the Features List section. Once Shelby OKs your README, the Features List section cannot be modified. For this reason, it is most important that you get a solid idea of what you want to make and the primary features it will have now.


Talk with your group. Consider drawing some pictures of what you think your project might look like. Be precise. When you’re ready, fill this out together. Each component in brackets below ( [these things] ) should be replaced with your ideas. Note that there are several sample READMEs posted on this assignment for you to use as guidance.


-------------------When README is finalized, remove everything above this line--------------------


VALHUNT
Authors: Kenny Wu, Aayush Kumar, Kyle Fu
Revision: 5/7/21


* 2D manhunt style, “play time”
   * Multiplayer server
   * Features can be varying complexity
      * Simple guns to lasers, shop system, goals
  
  
  

===============================================    


  



Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:
What does your program do?//
What problem does it solve? Why did you write it?
What is the story?
What are the rules? What is the goal?//
Who would want to use your program?//
What are the primary features of your program?//


Our program is a two player video game called “VALHUNT”. The goals of the game differ depending on the player and will oppose each other. The program/game is directed for people who enjoy playing small party games with each other.
The first goal (runner) is for the player to stay alive till the timer runs out and cross the finish line.[a] However if the runner were to be “defeated” by the hunter, he loses. The 2nd goal (hunter) is to stop the runner from crossing the finish line before time runs out.
The game features multiple rounds where after each round the winner and loser gains a set of money (less of loser, more for winner). The money can be used to buy items: For each player, items that can help in combat to win rounds.[b]
The world will feature a 2d scrolling world where the world “scrolls'' vertically down as the timer countdown. This world will be seen with a top-down view[c].




Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 
Where will you need to click? 
Will you have menus that need to be navigated? What will they look like? 
Do actions need to be taken in a certain order?]


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
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
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
* GUI - abstract class containing helper methods to make menus[n]
   * ShopMenu - menu at the beginning of the round where both players shop for weapons[o]
   * PauseMenu - menu that shows up when the game is paused
   * MainMenu - main menu displayed when the program is launched




Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
    Aayush -
    	* Finished Screen class
    	* Wrote out skeleton classes with getters and setters for the all the screens
    	* Implemented constructors for all screens
	Kenny -
		* Finished Sprite class
		* Wrote out skeleton classes with getters and setters for all sprites
		* Implemented constructors for all screens
	Kyle -
		* finished DrawingSurface
		* implemented screen switching
		* started working on GameScreen class
* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]


[a]What would be the purpose of having a timer if the stage scrolls automatically and the primary goal is to cross the finish line?
[b]This seems a little unbalanced. The person who wins gets more money, which gives them an advantage for the next round, which helps them to win again.
[c]Will there eventually be an "end" to the specific world after it scrolls a certain distance?
[d]I understand the hunter using the mouse to aim, but was the rotating for?
[e]what is the point of rotating if the runner can already move in all directions with wasd
[f]do the bullets have travel distance so aiming requires more skill or are the bullets hit scan?
[g]This sounds a bit unbalanced. It seems like it'll be a lot easier to maneuver the mouse and shoot the runner, which could make rounds pretty short.
[h]Does this mean that the controls for the hunter and runner would switch after each round?
[i]Would it be a set map or a randomly generated map?
[j]or maybe specific guns that do a lot of damage but shoot slowly or shoot fast and do little damage?
[k]Buying armor is good, but I feel like the runner could also increase their speed through upgrades would be good
[l]Where would the hunter respawn and would the hunter be given a grace period?
[m]Are you going to have a DrawingSurface like class? If not, what would the game function class be?
[n]Is this GUI class going to use processing methods or java.swing methods? How are you planning to switch between one screen or another?
[o]Does one player shop at a time? The mouse can only be used by one player, so will the hunter shop first, or the runner? Or, will you use keys to buy items so both players can shop?