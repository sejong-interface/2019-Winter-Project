#include<SoftwareSerial.h>

SoftwareSerial ESP8266(2,3);

String ssid = "interface518";
String password = "518interface";
String domain = "192.168.0.161:8080";
String address = "/arduino/washers/";
int tempWasherId = 999;

boolean sendAT(String command, char *response, int second){  
  ESP8266.setTimeout(second * 1000);
  ESP8266.println(command);
  
  boolean atResponse = ESP8266.find(response);
  
  ESP8266.setTimeout(second);
  Serial.println("AT Command : " + command + " state : " + atResponse);
  
  return atResponse;
}

String httpCom(int tempWasherld){
  String cmd = "";
  
  cmd = "GET " + address + (String)tempWasherId + " HTTP/1.1" + "\r\n";
  cmd += "Host: " + domain + "\r\n";
  cmd += "Connection: close\r\n";
  cmd += "\r\n";
  
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
}

void loop() {
  String httpRequest = httpCom(999);
  
  while(!sendAT("AT+CIPSTART=\"TCP\",\"192.168.0.161\",8080","OK",15)) { }
  while(!sendAT("AT+CIPSEND=" + httpRequest.length(),"OK",15)) { }
  ESP8266.println(httpRequest);
  
  delay(10000);
}
