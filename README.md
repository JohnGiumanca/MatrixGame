# Neo - The Shooter 
A shooter game on a led matrix with a joystick and a couple of buttons.

## Description
The project consists in a game of type shooter played on a 8x8 LED Matrix. You are using the joystick to move the sight and aim to a enemy target, the buttons allow you to shoot and to throw an explosive grenade. A LED screen shows you your status, the number of lifes, number of remaining grenades and score. A menu lets you start the game and see other informations like the score record.

### Game Walkthrough

First you enter the starting screen, at the press of the button you enter the menu.
The menu has 3 items:
* Start - starts the game
* Score - shows information about the last score and the record
* Instructions - shows how to play the game

The game has 3 levels. Your task is to aim with your joystick and shoot the enemys. If you find it dificult to aim at a specific target, you can use a grenade that does area damage. 

## Build and Installation
### Materials

Electronics:
* 1 Arduino Uno
* 1 LSD Screen
* 1 Potentiometer
* MAX7291 Matrix driver
* 8x8 LED Matrix
* Breadboards
* Wires
* 2 Buttons
* 1 Joystick
* 220 ohm resistors



### Build
The build it is quite simple. If you are a beginner, worry not because the project is entry level, you dont need any previous experience in arduino to do it. Before you start you need to accommodate yourself to some basic stuff. 
I recommend you use the [Arduino](https://www.arduino.cc/) site for tutorials, of course you can use any source you want on the internet. 
I will give you a list of things to check out:
* What is an Arduino Uno board
* How to upload a sketch from Arduino IDE
* How to use buttons
* How to use 8x8 LED Matrix
* How to use LCD Screen with a potentiometer
* How to use a joystick

### Install
The installation process is also simple. Download the file, open it with Arduino IDE and upload it to your board.

## Code 

```C
void moveSight() {
  xValue = analogRead(joyX);
  yValue = analogRead(joyY);
  xMap = map(xValue, 0, 900, 0, 7);
  yMap = map(yValue, 0, 900, 7, 0);
  lc.setLed(0, xMap, yMap, true);
  delay(30);
  if (aux_matrix[xMap][yMap] == false)
    lc.setLed(0, xMap, yMap, false);
  delay(30);
}
```
Read value form joystick, map it to the 8x8 matrix values, and turn on and off the sight LED
xMap and yMap are global, so every function will know where the sight is

```C
void scrollInFromRight (int line, char str1[]) {

  int i = strlen(str1);

  int index = 0;
  for (int k = 16; k <= i; k++) {
    for (int j = 0; j < 16; j++) {
      lcd.setCursor(j, line);
      lcd.print(str1[j + index]);

    }
    index++;

    button1 = digitalRead(buttonPin);
    if ( button1 == HIGH) {
      start_screen = false;
      menu_screen = true;
      lcd.clear();
      break;
    }

    delay(500);
  }
  if (!start_screen);
  delay(1000);
  
}
```
Because our display screens are limited in space, sometimes our text will be too long. With this function, the text will scroll from right to left. The function will display the first 16 letters from the text and after a delay, the next 16 and  so on till we get to the end of the text.

```C
void setup() {
  Serial.begin(115200);

  lc.shutdown(0, false);
  lc.setIntensity(0, 8);
  lc.clearDisplay(0);

  pinMode(buttonPin2, INPUT);
  pinMode(buttonPin, INPUT);

  lcd.begin(16, 2);
  lcd.clear();
  lcd.print("Neo-The Shooter");
}
```
Basic setup with clearing the displays and printing the text for the starting screen.

```C
while (lvl_number <= 3 && !lvl_complete)
  {
    if (lvl_setup)
    {
      ...
    }
    lcd.clear();
    lcd.print("Lives: ");
    lcd.print(nr_lives);
    lcd.setCursor(8, 0);
    lcd.print("Bombs: ");
    lcd.print(nr_bombs);
    lcd.setCursor(0, 1);
    lcd.print("Score: ");
    lcd.print(score);


    moveSight();
    fire();
    bomb();
```
At every given moment when a level is not complete, the user can aim, shoot and throw bomb. The level setup is done only once. We also display the number of lifes, score and number of bombs

```C
 if (lvl_complete && nr_lives != 0 && lvl_number <= 3){...}
 if (lvl_complete && nr_lives == 0){...}
 if (lvl_number > 3 && lvl_number != 100){...}
```
1. will get you to the next level setting the lvl_setup bool to true
2. will restart the level number back to 1 and reset all the values
3. will show an animation for beating the game and give the posibility to restart 

## Presentation
<p align="center">
<img src="https://i.imgur.com/FgHHLyU.jpg" width="420" height="315" style="float: right;">
<img src="https://i.imgur.com/lRkYTOK.jpg" width="420" height="315" style="float: right;">
<img src="https://i.imgur.com/tkGMV2Y.jpg" width="420" height="315" style="float: right;">
<img src="https://user-images.githubusercontent.com/19936310/46440568-78177a00-c76c-11e8-9fb5-656675cd640e.gif" width="420" height="315" style="float: right;"/>
</p>

### Full demo on [YouTube](https://www.youtube.com/watch?v=Et1WXffU3BE/).
