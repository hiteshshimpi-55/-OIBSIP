package com.example.task_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView unit_label,display_text;
    Button convert_button;
    ImageView switch_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.unit_input);
        unit_label = findViewById(R.id.unit_label);
        display_text = findViewById(R.id.converted_unit);
        switch_button = findViewById(R.id.switch_unit);
        convert_button = findViewById(R.id.button);

        switch_button.setOnClickListener(v -> {
            String unit = unit_label.getText().toString();
            if (unit.equals("KG")) {
                unit_label.setText("G");
                convert_button.setText("Convert to Kilo Grams");
            } else {
                unit_label.setText("KG");
                convert_button.setText("Convert to Grams");
            }
        });

        convert_button.setOnClickListener(v -> {
            String unit = unit_label.getText().toString();
            String input = editText.getText().toString();
            if (input.isEmpty()) {
                display_text.setText("Please enter a value");
            } else {
                double value = Double.parseDouble(input);
                if (unit.equals("KG")) {
                    double result = value * 1000;
                    display_text.setText(String.valueOf(result) + " g");
                } else {
                    double result = value / 1000;
                    display_text.setText(String.valueOf(result) + " kg");
                }
            }
        });
    }
}