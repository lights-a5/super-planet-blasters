# super-planet-blasters
DESIGN DOCUMENT - Team 13
_____________________________________________________________________________________________________________

Project Name:	Super Planet Blasters

Core Features:
A Top Down endless forward scrolling shooter for the android phones and tablets.
Some features include:

•	Stationary and moving enemies
•	 power ups
•	levels of difficulty 
•	start screen

Advanced Features:
Some extra things we hope to be able to include
•	boss battles
•	Skins or themes
•	“Character Dialogue”

An overview of the design / approach
The application will need to create a GUI menu on the startup. 
1.	It will display the game logo/Title and background image
2.	Play theme music
3.	Display buttons
_____________________________________________________________________________________________________________

The GUI interface will wait for user input. It will create a new game instance on start,
mute the sound if the sound off button is pressed. 
Once the game starts It will begin the game music and the background scrolling. 
We will have patterns and rules set up for each level of gameplay. Small enemies that 
spawn from the top and large enemies that spawn and move slowly. This continues until the user ship is
destroyed and the “game over” button will show. From that the process can start again at the main menu screen. 
Gameplay itself will consist of the main ship constantly firing as the user moves it 
around by touch (stretch goal could be gyro if the device allows). Waves of small ships
will come in randomly and shoot lasers that can kill the main 
user ship. On intervals, bigger ships that are meant to appear stationary will appear and
shoot as well. For a stretch, we would also make big guns on planets target the user for 
a few seconds and give a warning before firing a laser in the direction of the last 
position of the user ship. As the game progresses, we will have a type of timer increment
the level counter of the game which will increase the difficulty, causing more ships to
appear or shoot faster. We hope to get the core parts completed so that we can add
“bosses” at certain level increments which will stay at the top of the screen and 
shoot many bullets until “dead” or until the user is “dead”. Music and simple sound
effects will play throughout the game.
Most of these different objects react while the game is progressing. 
We are using a framework called libGDX which handles things such as game 
sprites changes activities to screens. 

