#include<SoftwareSerial.h>

SoftwareSerial ESP8266(2,3);

String SSID = "iptime";
String Password = "비밀번호";
String Domain = "riyenas0925.dothome.co.kr";
String Address = "/tree.php?time=";
int temp = 999;

boolean SendAT(String command, char *response, int second){
  String buffer = "";
  
  ESP8266.setTimeout(second * 1000);
  ESP8266.println(command);
  boolean AT_Response = ESP8266.find(response);
  
  ESP8266.setTimeout(second);

  Serial.println("AT Command : " + command + " state : " + AT_Response);

  return AT_Response;
}

void setup() {ㄴ
  Serial.begin(9600);
  ESP8266.begin(9600);

  Serial.println("ESP8266 초기화");
  delay(100);

  while(!SendAT("AT","OK",3)) { }
  while(!SendAT("AT+CWMODE=3","OK",3)) { }
  while(!SendAT("AT+CWJAP=\"" + SSID + "\",\"" + Password + "\"","OK",3)) { }
}

void loop() {
  String cmd;
  String buffer = "";
  
  cmd = "GET " + Address + (String)temp + " HTTP/1.1" + "\r\n";
  cmd += "Host: " + Domain + "\r\n";
  cmd += "Connection: close\r\n";
  cmd += "\r\n";
  
  while(!SendAT("AT+CIPSTART=\"TCP\",\"riyenas0925.dothome.co.kr\",80","OK",10)) { }
  while(!SendAT("AT+CIPSEND=" + (String)cmd.length(),"OK",10)){ }
  ESP8266.print(cmd);
  
  delay(50000);
}
