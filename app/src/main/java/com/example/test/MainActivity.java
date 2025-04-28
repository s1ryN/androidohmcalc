package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText napetiInput, proudInput, odporInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        napetiInput = findViewById(R.id.napeti);
        proudInput = findViewById(R.id.proud);
        odporInput = findViewById(R.id.odpor);
    }

    // VÝPOČET OHMOVA ZÁKONA
    public void calculateRest(View view) {
        String napetiStr = napetiInput.getText().toString().trim();
        String proudStr = proudInput.getText().toString().trim();
        String odporStr = odporInput.getText().toString().trim();

        int pocetZadanych = 0;
        if (!napetiStr.isEmpty()) pocetZadanych++;
        if (!proudStr.isEmpty()) pocetZadanych++;
        if (!odporStr.isEmpty()) pocetZadanych++;


        if (pocetZadanych != 2) {
            Toast.makeText(this, "Zadej přesně dvě hodnoty pro výpočet!", Toast.LENGTH_SHORT).show();
            return;
        }

        Double napeti = null;
        Double proud = null;
        Double odpor = null;

        if (!napetiStr.isEmpty()) napeti = Double.parseDouble(napetiStr);
        if (!proudStr.isEmpty()) proud = Double.parseDouble(proudStr);
        if (!odporStr.isEmpty()) odpor = Double.parseDouble(odporStr);


        // Výpočet podle Ohmova zákona: U = I * R
        if (napeti == null) {
            // Napětí
            if(odpor == 0){
                napetiInput.setText("∞");
            }else {
                napeti = proud * odpor;
                napetiInput.setText(String.format("%.2f", napeti));
            }
        } else if (proud == null) {
            // Proud
            proud = napeti / odpor;
            proudInput.setText(String.format("%.2f", proud));
        } else if (odpor == null) {
            // Odpor
            odpor = napeti / proud;
            odporInput.setText(String.format("%.2f", odpor));
        }
    }

    // RESET
    public void reset(View view) {
        napetiInput.setText("");
        proudInput.setText("");
        odporInput.setText("");
    }
}
