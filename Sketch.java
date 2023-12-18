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
  int[] intSnowX = new int[15];
  float[] fltSnowY = new float[15];
  int intSnowfall = 2;
  boolean[] blnShowSnow = new boolean[15];
  // Player Lives Variables 
  int intLives = 3;
  boolean blnPlayerAlive = true;
  
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
      intSnowX[i] = width * i / fltSnowY.length;
      blnShowSnow[i] = true;
    }
  }

  // Called repeatedly, anything drawn to the screen goes here
  public void draw() {
    if (blnPlayerAlive == true){
      // Reset background for animation effect 
      background(205, 230, 255);
      
      // Player Lives 
      stroke(255,70, 70);
      fill(255, 70, 70);
      if (intLives == 3){
        rect(555, 5, 10, 10);
        rect(570, 5, 10, 10);
        rect(585, 5, 10, 10);
      } else if (intLives == 2){
        rect(570, 5, 10, 10);
        rect(585, 5, 10, 10);
      } else if (intLives == 1){
        rect(585, 5, 10, 10);
      }
    
      // Draw Falling Snow 
      stroke(255);
      fill(255);
      for (int i = 0; i < fltSnowY.length; i++){
        if (blnShowSnow[i] == true){
          ellipse(intSnowX[i], fltSnowY[i], 25, 25);
          playerCollision();
          fltSnowY[i] += intSnowfall;

          if (fltSnowY[i] > height) {
            fltSnowY[i] = 0;
            blnShowSnow[i] = true;
          }
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
    for (int i = 0; i < fltSnowY.length; i++){
      if (intPlayerY - 15 < fltSnowY[i] + 12 && intPlayerY + 15 > fltSnowY[i] - 12){
        if (intPlayerX - 15 < intSnowX[i] + 12 && intPlayerX + 15 > intSnowX[i] - 12){        
          blnShowSnow[i] = false;
          intLives --;
          updateLives();
        }
      }
    }
  }

  public void updateLives(){
    if (intLives == 0){
      blnPlayerAlive = false;
    }
  }
  
  /**
   * A program that detects if the player clicks on a snow circle, and hides the respective circle 
   */
  public void mouseClicked(){
    for (int j = 0; j < fltSnowY.length; j++){
      if (mouseY < fltSnowY[j] + 12 && mouseY > fltSnowY[j] - 12){
        if (mouseX < intSnowX[j] + 12 && mouseX > intSnowX[j] - 12){
          blnShowSnow[j] = false;
        }
      }
    }
  }
}
