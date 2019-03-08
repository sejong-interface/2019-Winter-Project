#include "EmonLib.h"

EnergyMonitor coinWasher;
int washerId = 0;
int rmsV = 220; //전압의 RMS(Root Mean Square) 값

void setup()
{
  Serial.begin(9600);
  coinWasher.current(0, 111.1);
}

void loop()
{
  double rmsI = coinWasher.calcIrms(1480);
  double rmsPower = rmsI * rmsV;
  
  //Serial.println(rmsI); //전류의 RMS(Root Mean Square) 값
  Serial.println(rmsPower); //피상전력 (Irms * Vrms) 값
}
