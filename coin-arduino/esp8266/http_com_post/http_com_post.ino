#include<SoftwareSerial.h>
#include<ArduinoJson.h>

SoftwareSerial ESP8266(2, 3);

String ssid = "interface518";
String password = "518interface";
String domain = "192.168.0.46:8080";
String address = "/arduino";

boolean sendAT(String command, char *response, int second){  
  ESP8266.setTimeout(second * 1000);
  ESP8266.println(command);
  
  boolean atResponse = ESP8266.find(response);
  
  ESP8266.setTimeout(second);
  Serial.println("AT Command : " + command + " state : " + atResponse);
  
  return atResponse;
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

void setup() {
  Serial.begin(9600);
  ESP8266.begin(9600);

  Serial.println("ESP8266 initialize");
  delay(100);

  while(!sendAT("AT","OK",3)) { }
  while(!sendAT("AT+CWMODE=3","OK",3)) { }
  while(!sendAT("AT+CWJAP=\"" + ssid + "\",\"" + password + "\"","OK",3)) { }

  randomSeed(analogRead(0));
}

void loop() {
  String httpRequest = "";
  String jsonData = "";
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();

  root["washerld"] = random(0, 2);
  root["time"] = random(1, 61);
  root["amountElectric"] = random(0, 100);
  root.printTo(jsonData);

  httpRequest = httpCom(jsonData);

  while (!sendAT("AT+CIPSTART=\"TCP\",\"192.168.0.46\",8080", "OK", 15)) { }
  while (!sendAT("AT+CIPSEND=" + (String)httpRequest.length(), "OK", 15)) { }
  ESP8266.println(httpRequest);
  Serial.println(httpRequest);

  delay(10000);
}
