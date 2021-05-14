Legends of Kenjiro and the Infinite Dungeon
Authors: Srikrishna Joshi, Kinjal Govil
Revision: 4/20/21


Introduction: 
[In a few paragraphs totaling about ½ page, introduce the high-level concept of your program. What this looks like depends a lot on what type of thing you are making. An introduction for an application will look different than one for a game. In general, your introduction should address questions like these:
What does your program do?
Our program will be a top-down view 2D dungeon crawler type game which utilizes something like boss fights and puzzle rooms.[a][b][c] We will be able to implement concepts like chests and loot while developing an interesting storyline. We will also try to maybe implement some horror aspect to the game as well. The world we depict the game in will utilize magic, bows, swordsmanship, and etc. This game will also be multiplayer.
What problem does it solve? Why did you write it?
Lack of good 2D dungeon crawler games. We felt there was a market for a good 2D dungeon crawler game and we sought to fill this gap with a featureful interactive experience with a combination of hack and slash combat head scratching puzzles and intense boss fights. 
What is the story?
A young adventurer named Kenjiro finds himself awake with his friend Takumi in a dungeon without their gear. Kenjiro and his friend Takumi must find a way out of the dungeon and back to the outside world. As Kenjiro and his friend traverse through the dungeon they come across monsters which drop new weapons they had never seen before. As they continue through the dungeon Kenjiro and his friend discover something much larger is amiss. They meet strange and powerful people who possess knowledge unknown to the outside world. Will they ever find their way out and get to the bottom of this?
What are the rules? What is the goal?
The rules of this dungeon is that the main characters, Kenjiro and his friends are controlled by individual client users. They have health bars that must not be depleted or else the game ends. The overall objective of the game is to reach the end of the dungeon and exit it finally returning home. This can be completed by following the storyline and exploring the dungeon.
Who would want to use your program?
Hardcore gamers and old generation gamers because this type of game has been gone long out of fashion and people struggle to this day to find new and incoming dungeon crawler games such as the one we are creating.
What are the primary features of your program?]
The users should be able to traverse the dungeon using the WASD keys and fight using weapons and mouse interaction. We may be able to add a menu and inventory system with trade features if there is time.


Instructions:
[Explain how to use the program. This needs to be specific: 
Which keyboard keys will do what? 
* AWSD to move
* Left Mouse Click to initiate attack (attack depends on weapon equipped)
* Right Mouse Click if weapon has a special ability to activate it
* E to pick up items and interact with the world
* Wherever the mouse is placed that will be the direction the user’s avatar faces towards


Where will you need to click? 


You can click at any time although there is no point when there are no monsters or enemies nearby. If there are enemies nearby and your cursor is aligned with them when you click it the weapon should attack the monster if it is within the weapons range.


Will you have menus that need to be navigated? What will they look like? 
At the beginning there will be a start menu. This start menu will have the options of START, CHARACTER SELECTION, and VIEW INSTRUCTIONS. If we decide to have a inventory or trade menu those will be menus that are traverable as well. These specific menus will not be very complicated and should be rather simple if we decide to code them.


Do actions need to be taken in a certain order?]
The story line must be followed in order to access the next phase of the game


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
[These are features that we agree you will definitely have by the project due date. A good final project would have all of these completed. At least 5 are required. Each feature should be fully described (at least a few full sentences for each)]
*  Various monsters
*  At least one boss fights in the dungeon. A room in a dungeon where a stronger than average monster fights you. This would be the last obstacle or a checkpoint in the game.
*  A way to win or lose. This can be determined using health bars and having a finite story that ends.
* Unique weapons with perks and special ability. Extra rare weapons will have a special perk allowing the player to use a special ability. Couple examples of these abilities could be throwing 4 daggers in an arc every 30 seconds, teleporting a small distance, increasing damage for a limited amount of time. These special abilities will be activated with the right mouse click and should have cooldowns,
* Firebase based Multiplayer (2 players for now). One player will control Kenjiro and the Other player will control Takumi
* Rudimentary Storyline developed by using subtitles. As you progress through the game subtitles will appear explaining what is happening in the story. These will most likely occur after or before entering the next dungeon room.


Want-to-have Features:
[These are features that you would like to have by the project due date, but you’re unsure whether you’ll hit all of them. A good final project would have perhaps half of these completed. At least 5 are required. Again, fully describe each.]
* Server based Multiplayer to enable more players. Using a server application as a client status receiver and broadcaster to update the rest of the clients. This way the clients do not have to communicate with each other allowing for more players to join.
* Chest system and loot system with rarity types and unique weapon and gear perks. Chests in the dungeon can be opened to access items inside. Items can have varying abilities or rarities.
* If possible in addition to rooms with fights against mobs we would also like to perhaps implement a unique boss fights and treasure rooms
* Implement a save feature. The game would write to a save file stored locally. The next time the game opens the game status could be set to the information in the save file to restart from the same position.
*  Multiple levels with varying difficulty and length. The dungeon would have a varying number of rooms and the monsters would have more or less health and deal more or less damage.
* Inventory Menu that can be used to equip armor, weapons, and gear. This can be used to manage the player's loot. I can also adapt this to include a trading option with the other players in the game
* Srikrishna can draw the characters in the story and we can have the images of them appear when lines of that character are being spoken in the story. 
*  At least one puzzle room in the dungeon. A room where there a varying clues and puzzles that the user and the 2nd player will have to solve together to go on to the next room.
* Various weapons, and gear: When defeating monsters weapons and gear should be dropped[d]. The monsters and gear should at least have some variety so that the game does not become boring.
* More effort is put into the story lines and story aspect of the game, this would include physically making images of the characters that appear when the character is speaking. Cleaning up texts and subtitles


Stretch Features:
[These are features that we agree a fully complete version of this program would have, but that you probably will not have time to implement. A good final project does not necessarily need to have any of these completed at all. At least 3 are required. Again, fully describe each.]
*  We can voice act in it if there is time. Instead of subtitles to progress the story audio clips of narration or dialogue could be played.
* Adding new audio sound effects. As different actions are taken audio clips would be played to enhance the experience. We can also create music soundtracks to play in the background as the users play the game. 
*  We can add new and interesting NPC characters which handle outside quests (like kill 30 spiders). These would be used to improve player stats or abilities.
* Maybe create a cutscene using blender to add to the story. This would again add to the progression of the story when the player enters a new room a prerecorded video would be played to show what happened in the story.




Class List:
[This section lists the Java classes that make up the program and very briefly describes what each represents. It’s totally fine to put this section in list format and not to use full sentences.]


We don't exactly know what classes we will need yet, we will most likely find out as we are coding for the project. Some general classes we plan to add for now
* PlayerAvatar
   * This class will intake and manage the information about the avatar that user is controlling. It will use the data given by the users clicks and key inputs to change its field variables that represent the users avatar
* Mob
   * A superclass all monsters extend
* Mob SubClass
   * A specific type of mob
* GameBoard
   * The main class of the game that manages all the other classes, should manage graphics as well
* MessageSender
   * Sends update Information to other clients
* MessageReceiver
   * Receives messages from other client/user and send them to the game or player class for decryption
* Room(super class)
   * The super class that all rooms inert from.Has basic room related stuff(size, doors, etc)
* PuzzleRoom, BossRoom, MobRoom, StoryRoom
   * Various subclasses of Room that specific specialized features
* Weapon(super class)
   * A weapon super has defining characteristics of any weapon
* Weapon(subclasses)
   * Various subclasses of weapons that have special characteristics
* Projectile
   * A class representing a projectile that some weapons may utilize


Credits:
[Gives credit for project components. This includes both internal credit (your group members) and external credit (other people, websites, libraries). To do this:
* List the group members and describe how each member contributed to the completion of the final program. This could be classes written, art assets created, leadership/organizational skills exercises, or other tasks. Initially, this is how you plan on splitting the work.
* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
   * We are going to use images from online
   * Firebase library
   * Setup code from firebase demos
* Kinjal
   * Set up eclipse project
   * Implementation of multiplayer
      * Used: Gameboard, PlayerData, Player
   *  Modified our existing code to add collision detections
      * Collidable Interface
      * Added Collision detection loop between players in Gameboard
* Srikrishna
   * Coded the
      * GameBoard
      * Player
      * Enemy
      * EnemyManager
      * Room
      * EnemyRoom
      * Weapon
   * [a]How will you implement/code boss fights and puzzle rooms? It's a little unclear what these concepts are.
[b]_Marked as resolved_
[c]_Re-opened_
[d]How will it be dropped? Will there be a place to drop weapons/gear or will they "fall on" the player?