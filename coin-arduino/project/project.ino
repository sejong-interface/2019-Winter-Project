#include<SoftwareSerial.h>
#include <LiquidCrystal.h>
#include "ArduinoJson.h"
#include "EmonLib.h"


SoftwareSerial ESP8266(8, 9);
String ssid = "";
String password = "";
String domain = "192.168.0.46:8080";
String address = "/arduino";

EnergyMonitor coinWasher;
int washerId = 0;
int rmsV = 220; //전압의 RMS(Root Mean Square) 값

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
String msg;

void setup() {
  Serial.begin(9600);
  ESP8266.begin(9600);

  Serial.println("ESP8266 initialize");
  delay(100);

  lcd.begin(16, 2);

  while(!sendAT("AT","OK",3)) { }
  while(!sendAT("AT+CWMODE=3","OK",3)) { }
  while(!sendAT("AT+CWJAP=\"" + ssid + "\",\"" + password + "\"","OK",3)) { }

  coinWasher.current(1, 111.1);
}

void loop() {
  String httpRequest = "";
  String jsonData = "";
  double rmsI = coinWasher.calcIrms(1480);
  double rmsPower = rmsI * rmsI;
  
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();

  root["washerld"] = washerId;
  root["time"] = random(1, 61);
  root["amountElectric"] = rmsPower;
  root.printTo(jsonData);

  httpRequest = httpCom(jsonData);

  Serial.println(httpRequest);

  while (!sendAT("AT+CIPSTART=\"TCP\",\"192.168.0.46\",8080", "OK", 15)) {}
  while (!sendAT("AT+CIPSEND=" + (String)httpRequest.length(), "OK", 15)) {}
  
  ESP8266.println(httpRequest);
  Serial.println(httpRequest);

  delay(10000);
}


/*********** WIFI 관련 기능**********/
boolean sendAT(String command, char *response, int second){  
  ESP8266.setTimeout(second * 1000);
  ESP8266.println(command);
  
  boolean atResponse = ESP8266.find(response);
  
  ESP8266.setTimeout(second);
  Serial.println("AT Command : " + command + " state : " + atResponse);

  commandMsg(commandSplit(command), atResponse);

  return atResponse;  
}

String commandSplit(String command){
  String atCommand = "";
  
  for(int i=0; i < command.length(); i++){
    if(command[i] == '='){
      break;
    }
    
    atCommand += command[i];
  }
  return atCommand;
}

void commandMsg(String atCommand, boolean atResponse){
  if(atResponse == true){
    clean();
    showMsg(atCommand + "-" + "OK");
  }
  else{
    clean();
    showMsg(atCommand + "-" + "FAIL");
  }
}

String httpCom(String jsonData){
  String cmd = "";  
  cmd = "POST " + address + " HTTP/1.1" + "\r\n";
  cmd += "Host: " + domain + "\r\n";
  cmd += "Connection: close\r\n";
  cmd += "Content-Type: application/json\r\n";
  cmd += "Content-Length: ";
  cmd += (String)jsonData.length();
  cmd += "\r\n\r\n";
  cmd += jsonData;
  cmd += "\r\n\r\n";
  
  return cmd;
}

/*********** LCD 표시 기능 **********/
void showMsg(String status) {
  msg = status;
  
  Serial.println(msg);
  lcd.setCursor(0,0);
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
