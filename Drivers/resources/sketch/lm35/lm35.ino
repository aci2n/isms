const int inPin = 0;

void setup() {
  Serial.begin(9600);
}

void loop() {;
  Serial.print((analogRead(inPin) / 1024.0) * 500);
  Serial.print(",");
  delay(1000);
}