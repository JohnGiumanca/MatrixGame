#include "LedControl.h"
#include <LiquidCrystal.h>
#define joyX A0
#define joyY A1

const int rs = 9, en = 6, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);     //display

int xMap, yMap, xValue, yValue;
LedControl lc = LedControl(12, 11, 10, 1); //matrix

const int buttonPin = 8;     // button
const int buttonPin2 =  13;
int button1 = 0, last_button1 = 0;
int button2 = 0, last_button2 = 0;

bool aux_matrix[8][8];
bool start_screen = true;
bool menu_screen = false;
bool lvl_setup = true;
bool lvl_complete = false;
bool beat_game = false;
bool show_animation = true;
int nr_lives = 3;
int nr_bombs = 3;
int lvl_number = 100;
int score = 0;
int lscore = 0;
int hscore = 1000;
int selector = 1;
int info = 0;

bool cool_face[8][8] = {
  0, 0, 0, 0, 0, 0, 0, 0,
  1, 1, 1, 1, 1, 1, 1, 1,
  1, 1, 1, 0, 1, 1, 1, 0,
  1, 1, 1, 0, 1, 1, 1, 0,
  0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0,
  1, 1, 1, 1, 1, 1, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0,
};

bool sad_face[8][8] = {
  0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0,
  0, 1, 1, 0, 0, 1, 1, 0,
  0, 1, 1, 0, 0, 1, 1, 0,
  0, 0, 0, 0, 0, 0, 0, 0,
  0, 1, 1, 1, 1, 1, 0, 0,
  1, 0, 0, 0, 0, 0, 1, 0,
  0, 0, 0, 0, 0, 0, 0, 0,
};



void enemy1(int x, int y) {

  for (int i = x; i <= x + 1; i++)
    for (int j = y; j <= y + 1; j++) {
      lc.setLed(0, i, j, true);
      aux_matrix[i][j] = true;
    }

}

void enemy2(int x, int y) {

  for (int i = x; i <= x + 1; i++) {
    lc.setLed(0, i, y, true);
    aux_matrix[i][y] = true;
  }

}

void enemy3(int x, int y) {


  for (int j = y; j <= y + 1; j++) {
    lc.setLed(0, x, j, true);
    aux_matrix[x][j] = true;
  }

}

void enemy4(int x, int y) {

  lc.setLed(0, x, y, true);
  aux_matrix[x][y] = true;


}

void level1() {
  enemy1(0, 0);
  enemy1(5, 1);
  enemy1(6, 4);
  enemy2(3, 5);
  enemy2(5, 7);
  enemy3(1, 3);
  enemy4(1, 6);
  enemy4(3, 0);
  enemy4(3, 2);

}

void level2() {

  enemy1(0, 2);
  enemy1(6, 5);
  enemy1(3, 5);
  enemy3(6, 0);
  enemy2(1, 0);
  enemy2(0, 5);
  enemy2(6, 6);
  enemy4(4, 1);
  enemy4(4, 3);
  enemy4(1, 7);


}

void level3() {
  enemy4(0,7);
  enemy4(1,1);
  enemy4(1,3);
  enemy4(1,5);
  enemy4(3,0);
  enemy4(3,2);
  enemy4(3,6);
  enemy4(5,1);
  enemy4(5,3);
  enemy4(5,5);
  enemy4(5,7);
  enemy4(7,0);
  enemy4(7,2);
  enemy4(7,4);
  enemy4(7,6);


}
void enemyDown(int x , int y) {

  for (int i = x - 1; i <= x + 1; i++)
    for (int j = y - 1; j <= y + 1; j++)
      if (i < 8 && i >= 0 && j < 8 && j >= 0) {
        lc.setLed(0, i, j, false);
        aux_matrix[i][j] = false;
      }
}

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

void fire() {
  button1 = digitalRead(buttonPin);

  if ( button1 == HIGH && button1 != last_button1) {
    if ( aux_matrix[xMap][yMap] == true) {
      enemyDown(xMap, yMap);
      score += 100;
    }
    else {
      nr_lives--;
      if (score > 0)
        score -= 100;
    }
  }
  last_button1 = button1;
}

void bomb() {

  button2 = digitalRead(buttonPin2);

  if ( button2 == HIGH && button2 != last_button2 && nr_bombs != 0) {
    enemyDown(xMap, yMap);
    score += 100;
    nr_bombs--;

  }
  last_button2 = button2;
}

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

void printFace(bool face[8][8]) {

  lc.clearDisplay(0);

  for (int row = 0; row < 8; row++)
    for (int col = 0; col < 8; col++)
    {
      if (face[col][row] == 1)
        lc.setLed(0, row, 7 - col, true); //artificiu pentru afisare corecta

    }
}

void menu() {

  if (selector == 4)
    selector = 1;
  lcd.clear();

  switch (selector) {
    case 1:
      lcd.print("<-----Menu----->");
      lcd.setCursor(0, 1);
      lcd.print("--> Start");
      break;

    case 2:
      if (info == 2) {
        lcd.print("Last Score: ");
        lcd.setCursor(12, 0);
        lcd.print(lscore);
        lcd.setCursor(0, 1);
        lcd.print("High Score: ");
        lcd.setCursor(12, 1);
        lcd.print(hscore);
      }
      else {
        lcd.print("Start");
        lcd.setCursor(0, 1);
        lcd.print("--> Score");
      }
      break;

    case 3:
      if (info == 3) {
        lcd.print("Bt 1-Fire(1dot)");
        lcd.setCursor(0, 1);
        lcd.print("Bt 2-Bomb(3x3dots)");
      }
      else {
        lcd.print("Score");
        lcd.setCursor(0, 1);
        lcd.print("--> Instructions");
      }
      break;
  }
  delay(100);

  button2 = digitalRead(buttonPin2);
  if ( button2 == HIGH && button2 != last_button2) {
    selector++;
  }
  last_button2 = button2;

  button1 = digitalRead(buttonPin);
  if ( button1 == HIGH && button1 != last_button1 && selector == 1) {
    lvl_number = 1;
    menu_screen = false;
  }

  if ( button1 == HIGH && button1 != last_button1 && selector == 2) {
    if (info == 0)
      info = 2;
    else
      info = 0;
  }
  if ( button1 == HIGH && button1 != last_button1 && selector == 3) {
    if (info == 0)
      info = 3;
    else
      info = 0;

  }
  last_button1 = button1;




}

void finalAnimation() {
  for (int row = 0; row < 8; row++)
    for (int col = 0; col < 8; col++) {

      lc.setLed(0, col, row, true); // turns on LED at col, row
      delay(25);
    }

  for (int row = 0; row < 8; row++)
    for (int col = 0; col < 8; col++) {

      lc.setLed(0, col, row, false); // turns off LED at col, row
      delay(25);
    }
}





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

void loop() {

  if (start_screen)
    scrollInFromRight(1, "Press button to continue...");

  if (menu_screen)
    menu();

  while (lvl_number <= 3 && !lvl_complete)
  {
    if (lvl_setup)
    {
      lc.clearDisplay(0);
      switch (lvl_number) {
        case 1:
          level1();
          break;
        case 2:
          level2();
          break;
        case 3:
          level3();
          break;
      }
      lvl_setup = false;

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


    lvl_complete = true;
    for (int row = 0; row < 8; row++)
      for (int col = 0; col < 8; col++)
        if ( aux_matrix[row][col] == 1)
          lvl_complete = false;

    if (lvl_complete) {
      printFace(cool_face);
    }



    if (nr_lives == 0) {
      printFace(sad_face);
      lvl_complete = true;
    }
  }




  if (lvl_complete && nr_lives != 0 && lvl_number <= 3) {
    if (lvl_number == 3) {
      lvl_number++;
    }
    lcd.clear();
    lcd.print("Hold button for");
    lcd.setCursor(0, 1);
    lcd.print("the next level!");
    delay(1000);
    button1 = digitalRead(buttonPin);
    if ( button1 == HIGH) {
      lvl_number++;
      lvl_complete = false;
      lvl_setup = true;
    }
  }

  if (lvl_complete && nr_lives == 0) {
    lcd.clear();
    lcd.print("You missed!");
    lcd.setCursor(0, 1);
    lcd.print("Hold to return... ");
    delay(1000);
    button1 = digitalRead(buttonPin);
    if ( button1 == HIGH) {
      lvl_number = 100;
      lvl_complete = false;
      lvl_setup = true;
      nr_lives = 3;
      lscore = score;
      if (score > hscore)
        hscore = score;
      score = 0;
      menu_screen = true;

    }
  }

  if (lvl_number > 3 && lvl_number != 100) {
    lcd.clear();
    lcd.print("Congratulations!");
    lcd.setCursor(0, 1);
    lcd.print("You won! ");

    button1 = digitalRead(buttonPin);
    if ( button1 == HIGH) {
      lvl_number = 100;
      lvl_complete = false;
      lvl_setup = true;
      nr_lives = 3;
      lscore = score;
      if (score > hscore)
        hscore = score;
      score = 0;
      menu_screen = true;
    }

    if (show_animation) {
      finalAnimation();
      show_animation = false;
    }

    lcd.clear();
    lcd.print("Press button to");
    lcd.setCursor(0, 1);
    lcd.print("return to menu! ");
    delay(100);
  }





}
