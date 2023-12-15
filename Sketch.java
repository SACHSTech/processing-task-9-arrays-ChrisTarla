/** Instructions 
 * A sketch that simulates snow falling; down arrow on keyboard makes snow falls faster, up arrow makes snow fall slower 
 * Add a blue player circle that is controlled by WASD keys; add three squares at the top right of the screen for player lives; player loses a life 
 * everytime it collides with snowflake; game ends when all lives are lost and screen clears to white 
 * Add mouse control so players can click on snowflakes and make them disappear while using WASD keys with other hand (may need bigger snowflakes) 
 * 
 * TIP: have a related array of x values for snowflakes and initialize them to be at randomly generated locations (just like the y values) 
 * instead of setting your x-values of the snowballs to be a function of the randomly generated y 
 * TIP: introduce an additional "ballHideStatus" related array to store the state of each snowball; elements will be defaulted to false, and when a 
 * snowball is clicked on, the corresponding ballHideStatus will become true and the snowball will no longer be drawn to the screen 
 */

import processing.core.PApplet;

public class Sketch extends PApplet {
	// Variables 
  boolean blnUp;
  boolean blnLeft;
  boolean blnDown;
  boolean blnRight;
  int intPlayerX = 200;
  int intPlayerY = 375;

  // Called once at the beginning of execution, put your size all in this method
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  // Called once at the beginning of execution.  Add initial set up values here i.e background, stroke, fill etc. 
  public void setup() {
    background(210, 255, 173);
  }

  // Called repeatedly, anything drawn to the screen goes here
  public void draw() {
	  while (blnUp == true){
      if (intPlayerY > 25){
        intPlayerY -= 1;
      }
    }
    ellipse(intPlayerX, intPlayerY, 50, 50);
  }

  public void keyPressed(){
    if (key == 'W' || key == 'w'){
      blnUp = true;
    } else if (key == 'A' || key == 'a'){
      blnLeft = true;
    } else if (key == 'S' || key == 's'){
      blnDown = true;
    } else if (key == 'D' || key == 'd'){
      blnRight = true;
    }
  }

  /*public void keyReleased(){
    if (key == 'W' || key == 'w'){
      blnUp = false;
    } else if (key == 'A' || key == 'a'){
      blnLeft = false;
    } else if (key == 'S' || key == 's'){
      blnDown = false;
    } else if (key == 'D' || key == 'd'){
      blnRight = false;
    }
  }*/
}
