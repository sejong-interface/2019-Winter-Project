#include <LiquidCrystal.h>

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

String msg;

void setup() {
  lcd.begin(16, 2);
  Serial.begin(9600);
  while (!Serial) {
    ;
  }
}

void loop() {    
  if (Serial.available() > 0) {
    char c = Serial.read();
    if (c == '\n') {                              
      showMsg();                
      clean();                
    } else {
      msg += c;
    }
  }
}

void showMsg() {  
  Serial.println(msg);
  lcd.print(msg);
  lcd.display();
  
  for (int i=3; i>0; i--) {            
    lcd.setCursor(0,1);
    lcd.print("clear after " + String(i) + "sec");
    delay(1000);
  }
}

void clean() {
  lcd.clear();
  msg = "";
}
