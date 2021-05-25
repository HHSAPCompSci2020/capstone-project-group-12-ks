Legends of Kenjiro and the Infinite Dungeon
Authors: Srikrishna Joshi, Kinjal Govil
Revision: 4/20/21


Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:
What does your program do?
Our program will be a top-down view 2D dungeon crawler type game which utilizes a system of rooms through which the player has to progress through to get to the boss. The game will allow multiple people to enter this magical world at the same time and interact with the world together. The program will create enemies as the player goes to the next room and will require all the enemies to be killed before the player can move on.
What problem does it solve? Why did you write it?
Lack of good 2D dungeon crawler games. We felt there was a market for a good 2D dungeon crawler game and we sought to fill this gap with a featureful interactive experience with a combination of hack and slash combat and intense boss fights. 
What is the story?
A young adventurer named Kenjiro finds himself awake with his friend Takumi in a dungeon without their gear. Kenjiro and his friend Takumi must find a way out of the dungeon and back to the outside world. As Kenjiro and his friend traverse through the dungeon they come across monsters which drop new weapons they had never seen before. As they continue through the dungeon Kenjiro and his friend discover something much larger is amiss. They meet strange and powerful people who possess knowledge unknown to the outside world. Will they ever find their way out and get to the bottom of this?
What are the rules? What is the goal?
The rules of this dungeon is that the main characters, Kenjiro and his friends, are controlled by individual client users. They have health bars that must not be depleted or else the game ends. The overall objective of the game is to reach the end of the dungeon and exit it, to finally return home. This can be completed by following the storyline and exploring the dungeon.
Who would want to use your program?
Hardcore gamers and old generation gamers because this type of game has been gone long out of fashion and people struggle to this day to find new and incoming dungeon crawler games such as the one we are creating.
What are the primary features of your program?]
The users should be able to traverse the dungeon using the WASD keys and fight using weapons and keyboard interaction. The game will have multiplayer to allow everyone to be present in the same virtual world.


Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 
* AWSD to move
* Space bar to attack
* Shift to Activate Weapon Special Move


Where will you need to click? 
Clicking will only be necessary during story mode. Left click during story mode to move to next subtitle. Other than that no clicking will be necessary


Will you have menus that need to be navigated? What will they look like? 
No menus


Do actions need to be taken in a certain order?]
The story line must be followed in order to access the next phase of the game


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
* Various monsters
*  At least one boss fights in the dungeon. A room in a dungeon where a stronger than average monster fights you. This would be the last obstacle or a checkpoint in the game.
*  A way to win or lose. This can be determined using health bars and having a finite story that ends.
* Unique weapons with perks and special ability. Extra rare weapons will have a special perk allowing the player to use a special ability. Couple examples of these abilities could be throwing 4 daggers in an arc every 30 seconds, teleporting a small distance, increasing damage for a limited amount of time. These special abilities will be activated with the right mouse click and should have cooldowns,
* Firebase based Multiplayer (2 players for now). One player will control Kenjiro and the Other player will control Takumi
* Rudimentary Storyline developed by using subtitles. As you progress through the game subtitles will appear explaining what is happening in the story. These will most likely occur after or before entering the next dungeon room.




Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
* If possible in addition to rooms with fights against mobs we would also like to perhaps implement a unique boss fights and treasure rooms
* Implement a save feature. The game would write to a save file stored locally. The next time the game opens the game status could be set to the information in the save file to restart from the same position.
*  Multiple levels with varying difficulty and length. The dungeon would have a varying number of rooms and the monsters would have more or less health and deal more or less damage.
* Inventory Menu that can be used to equip armor, weapons, and gear. This can be used to manage the player's loot. Could potentially include a trading option with the other players in the game
*  At least one puzzle room in the dungeon. A room where there a varying clues and puzzles that the user and the 2nd player will have to solve together to go on to the next room.
* Various weapons, and gear: When defeating monsters weapons and gear should be dropped. The monsters and gear should at least have some variety so that the game does not become boring.
* More effort is put into the story lines and story aspect of the game, this would include physically making images of the characters that appear when the character is speaking. Cleaning up texts and subtitles


Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
*  We can voice-act in it if there is time. Instead of subtitles to progress the story audio clips of narration or dialogue could be played.
* Adding new audio sound effects. As different actions are taken audio clips would be played to enhance the experience. We can also create music soundtracks to play in the background as the users play the game. 
*  We can add new and interesting NPC characters which handle outside quests (like kill 30 spiders). These would be used to improve player stats or abilities.
* Maybe create a cutscene using blender to add to the story. This would again add to the progression of the story when the player enters a new room a prerecorded video would be played to show what happened in the story.




Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]


We don't exactly know what classes we will need yet, we will most likely find out as we are coding for the project. Some general classes we plan to add for now
* Player
   * Draws the player. And implements key interaction
* Enemy
   * A superclass all monsters extend
* GameBoard
   * The main class of the game that manages all the other classes, should manage graphics as well. Has majority of multiplayer interaction
* Room(super class)
   * The super class that all rooms inert from.Has basic room related stuff(size, doors, etc)
* BossRoom, EnemyRoom
   * Various subclasses of Room that specific specialized features
* Weapon
   * A weapon that has modifiable fields for various weapon strengths
* Boss
   * A subclass of enemy that represents the boss.
* EnemyData
   * A class representing the data on the database for enemies
* EnemyManager
   * A class that manages all the enemies actions
* HealthBar
   * A class that stores health and displays a health bar
* Main
   * Starts the program
* PlayerData
   * Stores the data for the player on the database


Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
   * Firebase library
   * Setup code from firebase demos
   * Background pngs are from
      * https://www.reddit.com/r/PixelArt/comments/bi59sh/my_first_go_at_a_top_down_rogue_like_dungeon_tile/
   * Original png for Player (Blue hair guy)
      * https://fb.ru/article/182885/spraytyi-dlya-igr---chto-eto-takoe
   * Original Zombie png for Enemy
      * http://clipart-library.com/clip-art/zombie-png-transparent-15.htm
   * Original Knight png (not used in game currently, was intended for different enemy types)
      * https://favpng.com/png_view/knight-knight-pixel-art-cartoon-png/egdjAQVu
   * Original Spider png (not used in game currently, was intended for different enemy types)
      * https://www.shutterstock.com/image-vector/vector-pixel-art-spider-isolated-cartoon-1137690707
   * Original Boss Image for Boss
      * https://www.pngkey.com/detail/u2q8r5q8o0a9w7w7_blue-flame-boss-fire-enemy-pixel-art/
   * Original Weapon Images (none in use currently)
      * https://www.istockphoto.com/vector/pixel-weapons-for-games-icons-vector-set-gm492258666-76213573?irgwc=1&cid=IS&utm_medium=affiliate&utm_source=TinEye&clickid=Uy636NTdTxyLWTm0RHQK3XRkUkBzTOScE0BtRA0&utm_term=&utm_campaign=&utm_content=435504&irpid=77643
      * https://depositphotos.com/175013198/stock-illustration-set-of-weapon-icons-in.html




* Kinjal
   * Set up eclipse project
   * Implementation of multiplayer
      * Used: Gameboard, PlayerData, Player, EnemyManager, Enemy, EnemyData
      * Wrote database all interaction code
      * Designed database schema
      * Implemented conflict resolution
      * Optimized construction and drawing game objects to reduce database lag
      * Initiating the objects from the database correctly
   *  Modified our existing code to add collision detections
      * Collidable Interface
      * Designed the collision detection organization system
      * Implemented collision detection for all things in game.




* Srikrishna
   * Implemented drawing 
      * Sprites
      * Graphics (Gameboard)
      * Animation
         * Running animation for player
         * Swing animation for weapon
   * PhotoShop + Art Studios
      * Used Photoshop and Art platforms like Clip Art Studio paint to crop and create pngs for the game (literally all the pngs used in the game other than the background pngs were cropped/created by me)
      * When doing so i would have to use the one original image to create 2-12 images for animation
   * Room System
      * Moving from room to room
      * Drawing correct room background
      * Enemies Spawning for rooms
      * Boss Room + EnemyRoom functionality
      * Subtitles and game story were coded by me including the scripts
   * Coded (when excluding collisions)
      * BossRoom
      * Room
      * EnemyRoom
      * Player 
      * Enemy
      * Boss
      * Weapon
      * GameBoard
      * EnemyManager (with Kinjal’s help (database heavy))
      * HealthBar