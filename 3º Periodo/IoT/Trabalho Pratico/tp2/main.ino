#include <Arduino.h>

const int led_red = 8;
const int led_ylw = 12;
const int led_grn = 13;

const int btn_red = 2;
const int btn_ylw = 4;
const int btn_grn = 7;

int btn_red_state = 0;
int btn_ylw_state = 0;
int btn_grn_state = 0;

void setup()
{
  // Led's
  pinMode(led_red, OUTPUT);
  pinMode(led_ylw, OUTPUT);
  pinMode(led_grn, OUTPUT);
  
  // Btn's
  pinMode(btn_red, INPUT);
  pinMode(btn_ylw, INPUT);
  pinMode(btn_grn, INPUT);
  
  // Start = LOW
  digitalWrite(led_red, 0);
  digitalWrite(led_ylw, 0);
  digitalWrite(led_grn, 0);
}

void loop()
{
  btn_red_state = digitalRead(btn_red);
  btn_ylw_state = digitalRead(btn_ylw);
  btn_grn_state = digitalRead(btn_grn);
  
  if(btn_red_state == 1) digitalWrite(led_red, 1);
  else digitalWrite(led_red, 0);
  if(btn_ylw_state == 1) digitalWrite(led_ylw, 1);
  else digitalWrite(led_ylw, 0);
  if(btn_grn_state == 1) digitalWrite(led_grn, 1);
  else digitalWrite(led_grn, 0);
}