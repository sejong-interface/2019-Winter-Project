#include<SoftwareSerial.h>
#include <LiquidCrystal.h>
#include "ArduinoJson.h"
#include "EmonLib.h"


SoftwareSerial ESP8266(8, 9);
String ssid = "interface518";
String password = "518interface";
String domain = "192.168.0.46";
String port = "8080";
String address = "/arduino/";

EnergyMonitor coinWasher;
int washerId = 0;
int rmsV = 220;

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
String msg;

void setup() {
  Serial.begin(9600);
  ESP8266.begin(9600);

  Serial.println("ESP8266 initialize");
  delay(100);

  lcd.begin(16, 2);

  pinMode(6, INPUT);
  pinMode(7, INPUT);
  pinMode(10, INPUT);
  pinMode(13, INPUT);
  
  washerId = checkDeviceId();

  while(!sendAT("AT","OK",3)) { }
  while(!sendAT("AT+CWMODE=3","OK",3)) { }
  while(!sendAT("AT+CWJAP=\"" + ssid + "\",\"" + password + "\"","OK",3)) { }

  coinWasher.current(1, 111.1);
}

void loop() {
  String httpRequest = "";
  String jsonData = "";
  double rmsI = coinWasher.calcIrms(1480);
  long rmsPower = rmsI * rmsI;
  
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();

  root["id"] = washerId;
  root["electricPower"] = rmsPower;
  root.printTo(jsonData);

  httpRequest = httpCom(jsonData);

  while (!sendAT("AT+CIPSTART=\"TCP\",\"" + domain + "\"," + port, "OK", 15)) {}
  while (!sendAT("AT+CIPSEND=" + (String) httpRequest.length(), "OK", 15)) {}

  amountElectricMsg(rmsPower);
  
  ESP8266.println(httpRequest);
  Serial.println(httpRequest);

  delay(1000);
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

void amountElectricMsg(long power){
  clean();
  showMsg("Power : " + (String) power);
}

String httpCom(String jsonData){
  String cmd = "";
  cmd.concat("POST " + address + (String) washerId + " HTTP/1.1\r\n");
  cmd.concat("Host: " + domain + "\r\n");
  cmd.concat("Connection: close\r\n");
  cmd.concat("Content-Type: application/json\r\n");
  cmd.concat("Content-Length: " + (String) jsonData.length() + "\r\n\r\n");
  cmd.concat(jsonData + "\r\n\r\n");

  return cmd;
}

/*********** LCD 표시 기능 **********/
void showMsg(String data) {
  msg = data;
  
  Serial.println(msg);
  lcd.setCursor(0,0);
  lcd.print(msg);
  lcd.display();
}

void clean() {
  lcd.clear();
  msg = "";
}

/*********** id 선택 **********/
int checkDeviceId(){
  boolean sw1 = digitalRead(6);
  boolean sw2 = digitalRead(7);
  boolean sw3 = digitalRead(10);
  boolean sw4 = digitalRead(13);
  
  return binaryToDecimal(sw1, sw2, sw3, sw4);
}

int binaryToDecimal(boolean sw1, boolean sw2, boolean sw3, boolean sw4){
  return sw1 * 1 + sw2 * 2 + sw3 * 4 + sw4 * 8;
}
