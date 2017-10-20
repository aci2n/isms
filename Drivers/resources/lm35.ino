void setup() {
  Serial.begin(9600);
}

void loop() {;
  Serial.write(analogRead(0));
  delay(1000);
}