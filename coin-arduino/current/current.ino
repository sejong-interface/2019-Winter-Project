#include "EmonLib.h"

EnergyMonitor coinWasher;
int washerId = 0;
int rmsV = 220; //전압의 RMS(Root Mean Square) 값

void setup()
{
  Serial.begin(9600);
  coinWasher.current(1, 111.1);
}

void loop()
{
  double rmsI = coinWasher.calcIrms(1480);
  double rmsPower = rmsI * rmsI;

  /*
  root["washerld"] = washerId;
  root["time"] = random(1, 61);
  root["amountElectric"] = rmsPower;
  root.printTo(jsonData);
  */
  
  Serial.println(rmsI); //전류의 RMS(Root Mean Square) 값
  Serial.print(rmsI * rmsV); //피상전력 (Irms * Vrms) 값
}
