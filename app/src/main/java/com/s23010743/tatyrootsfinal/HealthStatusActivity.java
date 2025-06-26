package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HealthStatusActivity extends AppCompatActivity {

    private EditText editTextAgeLimit;
    private CheckBox checkBoxPressure;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxCholesterol;
    private CheckBox checkBoxUlcer; // Declare Ulcer CheckBox
    private Button buttonBack;
    private Button buttonNext;

    private RadioGroup radioGroupPressureLevel;
    private RadioButton radioLowPressure;
    private RadioButton radioHighPressure;

    private RadioGroup radioGroupSugarLevel;
    private RadioButton radioLowSugar;
    private RadioButton radioHighSugar;

    private RadioGroup radioGroupCholesterolLevel;
    private RadioButton radioLowCholesterol;
    private RadioButton radioHighCholesterol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_status);

        initViews();
        setupListeners();
    }

    private void initViews() {
        editTextAgeLimit = findViewById(R.id.editText_age_limit);
        checkBoxPressure = findViewById(R.id.checkBox_pressure);
        checkBoxSugar = findViewById(R.id.checkBox_sugar);
        checkBoxCholesterol = findViewById(R.id.checkBox_cholesterol);
        checkBoxUlcer = findViewById(R.id.checkBox_ulcer); // Initialize Ulcer CheckBox
        buttonBack = findViewById(R.id.button_back);
        buttonNext = findViewById(R.id.button_next);

        // Pressure Level Radio Group
        radioGroupPressureLevel = findViewById(R.id.radioGroup_pressure_level);
        radioLowPressure = findViewById(R.id.radio_low_pressure);
        radioHighPressure = findViewById(R.id.radio_high_pressure);

        // Sugar Level Radio Group
        radioGroupSugarLevel = findViewById(R.id.radioGroup_sugar_level);
        radioLowSugar = findViewById(R.id.radio_low_sugar);
        radioHighSugar = findViewById(R.id.radio_high_sugar);

        // Cholesterol Level Radio Group
        radioGroupCholesterolLevel = findViewById(R.id.radioGroup_cholesterol_level);
        radioLowCholesterol = findViewById(R.id.radio_low_cholesterol);
        radioHighCholesterol = findViewById(R.id.radio_high_cholesterol);

        // Initial state of RadioGroups (hidden)
        radioGroupPressureLevel.setVisibility(View.GONE);
        radioGroupSugarLevel.setVisibility(View.GONE);
        radioGroupCholesterolLevel.setVisibility(View.GONE);
    }

    private void setupListeners() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity (e.g., HomePage)
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageLimitStr = editTextAgeLimit.getText().toString().trim();
                if (TextUtils.isEmpty(ageLimitStr)) {
                    Toast.makeText(HealthStatusActivity.this, "Please enter your age limit.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Priority for specific health conditions
                // Check for "Pressure" conditions and redirect
                if (checkBoxPressure.isChecked()) {
                    if (radioLowPressure.isChecked()) {
                        Intent intent = new Intent(HealthStatusActivity.this, LowBPFoodMenuActivity.class);
                        startActivity(intent);
                        return;
                    } else if (radioHighPressure.isChecked()) {
                        Intent intent = new Intent(HealthStatusActivity.this, HighBPFoodMenuActivity.class);
                        startActivity(intent);
                        return;
                    } else {
                        Toast.makeText(HealthStatusActivity.this, "Please select Pressure level (Low/High).", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Check for "Sugar" conditions and redirect
                if (checkBoxSugar.isChecked()) {
                    if (radioLowSugar.isChecked()) {
                        Intent intent = new Intent(HealthStatusActivity.this, LowSugarFoodMenuActivity.class);
                        startActivity(intent);
                        return;
                    } else if (radioHighSugar.isChecked()) {
                        Intent intent = new Intent(HealthStatusActivity.this, HighSugarFoodMenuActivity.class);
                        startActivity(intent);
                        return;
                    } else {
                        Toast.makeText(HealthStatusActivity.this, "Please select Sugar level (Low/High).", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Check for "Cholesterol" conditions and redirect
                if (checkBoxCholesterol.isChecked()) {
                    if (radioLowCholesterol.isChecked()) {
                        Intent intent = new Intent(HealthStatusActivity.this, LowCholesterolFoodMenuActivity.class);
                        startActivity(intent);
                        return;
                    } else if (radioHighCholesterol.isChecked()) {
                        Intent intent = new Intent(HealthStatusActivity.this, HighCholesterolFoodMenuActivity.class);
                        startActivity(intent);
                        return;
                    } else {
                        Toast.makeText(HealthStatusActivity.this, "Please select Cholesterol level (Low/High).", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // NEW: Check for "Ulcer" condition and redirect
                if (checkBoxUlcer.isChecked()) {
                    Intent intent = new Intent(HealthStatusActivity.this, UlcerFoodMenuActivity.class);
                    startActivity(intent);
                    return;
                }

                // If no specific health condition is selected or matched, go to the general menu
                Intent intent = new Intent(HealthStatusActivity.this, DesiredFoodMenuActivity.class);
                startActivity(intent);
                // finish(); // Optionally finish HealthStatusActivity if you don't want to come back
            }
        });

        // Listener for Pressure CheckBox
        checkBoxPressure.setOnCheckedChangeListener((buttonView, isChecked) -> {
            radioGroupPressureLevel.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (!isChecked) {
                radioGroupPressureLevel.clearCheck();
            }
        });

        // Listener for Sugar CheckBox
        checkBoxSugar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            radioGroupSugarLevel.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (!isChecked) {
                radioGroupSugarLevel.clearCheck();
            }
        });

        // Listener for Cholesterol CheckBox
        checkBoxCholesterol.setOnCheckedChangeListener((buttonView, isChecked) -> {
            radioGroupCholesterolLevel.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (!isChecked) {
                radioGroupCholesterolLevel.clearCheck();
            }
        });

        // No special listener for Ulcer checkbox if it doesn't have sub-options
    }
}
