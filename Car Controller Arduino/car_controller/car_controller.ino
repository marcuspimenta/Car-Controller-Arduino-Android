
int led = 13;

void setup() {
    pinMode(led, OUTPUT);
    digitalWrite(led, HIGH);
    
    Serial.begin(9600);
}

void loop() {
    if (Serial.available() > 0) {
        char inChar = Serial.read();
        
        switch (inChar) {   
            case 'a':
              digitalWrite(led, HIGH);
              break;
            case 'b':
              digitalWrite(led, LOW);
              break;
        }
    }
}

