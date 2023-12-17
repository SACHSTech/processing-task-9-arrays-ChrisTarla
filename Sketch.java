/** Instructions 
 * Player loses a life everytime it collides with snowflake  
 * Add mouse control so players can click on snowflakes and make them disappear while using WASD keys with other hand 
 * 
 * TIP: introduce an additional "ballHideStatus" related array to store the state of each snowball; elements will be defaulted to false, and when a 
 * snowball is clicked on, the corresponding ballHideStatus will become true and the snowball will no longer be drawn to the screen 
 */

import processing.core.PApplet;

public class Sketch extends PApplet {
	// Player Variables 
  boolean blnUp = false;
  boolean blnLeft = false;
  boolean blnDown = false;
  boolean blnRight = false;
  int intPlayerX = 300;
  int intPlayerY = 575;
  // Snow Variables 
  int intSnowX;
  float[] fltSnowY = new float[15];
  int intSnowfall = 2;
  // Player Lives Variables 
  boolean blnLife1 = true;
  boolean blnLife2 = true;
  boolean blnLife3 = true;
  
  // Called once at the beginning of execution, put your size all in this method
  public void settings() {
	// put your size call here
    size(600, 600);
  }

  // Called once at the beginning of execution.  Add initial set up values here i.e background, stroke, fill etc. 
  public void setup() {
    background(205, 230, 255);
    for (int i = 0; i < fltSnowY.length; i++){
      fltSnowY[i] = random(0, 450);
    }
  }

  // Called repeatedly, anything drawn to the screen goes here
  public void draw() {
    if (blnLife3 == true){
      // Reset background for animation effect 
      background(205, 230, 255);
      
      // Player Lives 
      stroke(255,70, 70);
      fill(255, 70, 70);
      if (blnLife1 == true){
        rect(555, 5, 10, 10);
      }
      if (blnLife2 == true){
        rect(570, 5, 10, 10);
      }
      if (blnLife3 == true){
        rect(585, 5, 10, 10);
      }
    
      // Draw Falling Snow 
      stroke(255);
      fill(255);
      for (int i = 0; i < fltSnowY.length; i++){
        intSnowX = width * i / fltSnowY.length;
        ellipse(intSnowX, fltSnowY[i], 25, 25);
        fltSnowY[i] += intSnowfall;
        playerCollision();

        if (fltSnowY[i] > height) {
          fltSnowY[i] = 0;
        }
      }
        
      // Draw player and movements  
      playerMovement();
      fill(130, 195, 250);
      ellipse(intPlayerX, intPlayerY, 30, 30);
    } else {
      background(255);
    }
  }

  /**
   * A program that changes the x and y value of the provided variables based on the conditions of the user keyboard input 
   */
  public void playerMovement(){
    if (blnUp == true && intPlayerY >= 25){
      intPlayerY -= 3;
    } else if (blnDown == true && intPlayerY <= 575){
      intPlayerY += 3;
    } else if (blnLeft == true && intPlayerX >= 25){
      intPlayerX -= 3;
    } else if (blnRight == true && intPlayerX <= 575){
      intPlayerX += 3;
    }
  }

  /**
   * A program that reads the user keyboard input and changes values according to the pressed keys 
   */
  public void keyPressed(){
    if (key == 'W' || key == 'w'){
      blnUp = true;
    } else if (key == 'A' || key == 'a'){
      blnLeft = true;
    } else if (key == 'S' || key == 's'){
      blnDown = true;
    } else if (key == 'D' || key == 'd'){
      blnRight = true;
    } else if (key == CODED){
      if (keyCode == UP){
        intSnowfall = 1;
      } else if (keyCode == DOWN){
        intSnowfall = 4;
      }
    }
  }

  /**
   * A program that stops the user keyaord input and resets values according to the pressed keys 
   */
  public void keyReleased(){
    if (key == 'W' || key == 'w'){
      blnUp = false;
    } else if (key == 'A' || key == 'a'){
      blnLeft = false;
    } else if (key == 'S' || key == 's'){
      blnDown = false;
    } else if (key == 'D' || key == 'd'){
      blnRight = false;
    } else if (key == CODED){
      if (keyCode == UP){
        intSnowfall = 2;
      } else if (keyCode == DOWN){
        intSnowfall = 2;
      }
    }
  }

  /**
   * A program that detects if the player circle collides with the snow, and deducts a life accordingly 
   */
  public void playerCollision(){
    /* check if intPlayerX is between these 2 values of each snow circle, and if intPlayerY is between these 2 values of each snow circle
       if yes, check which lives are still true, and make the leftmost one false  
    */

    for (int i = 0; i < fltSnowY.length; i++){
      if (intPlayerX - 15 < intSnowX + 12 && intPlayerX + 15 > intSnowX - 12){
        if (intPlayerY - 15 < fltSnowY[i] + 12 && intPlayerY + 15 > fltSnowY[i] - 12){
          if (blnLife1 == true){
            blnLife1 = false;
          } else if (blnLife1 == false){
            blnLife2 = false;
          } else if (blnLife2 == false){
            blnLife3 = false;
          }
        }
      }
    }
  }
  
  /**
   * A program that detects if the player clicks on a snow circle, and hides the respective circle 
   */
  public void mouseClicked(){
    /* check if MouseX is between these 2 values of each snow circle, and if MouseY is between these 2 values of each snow circle 
       if yes, make that snow circle disappear by making a hide status variable true 
    */
  }
}
