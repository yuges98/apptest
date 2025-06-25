package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton; // Import RadioButton
import android.widget.RadioGroup; // Import RadioGroup

import androidx.appcompat.app.AppCompatActivity;

public class HealthStatusActivity extends AppCompatActivity {

    // UI Elements
    private EditText editTextAge;
    private CheckBox checkBoxUlcer;
    private CheckBox checkBoxPressure;
    private RadioGroup radioGroupPressure;
    private RadioButton radioPressureHigh;
    private RadioButton radioPressureLow;
    private CheckBox checkBoxSugar;
    private RadioGroup radioGroupSugar;
    private RadioButton radioSugarHigh;
    private RadioButton radioSugarLow;
    private CheckBox checkBoxCholesterol;
    private RadioGroup radioGroupCholesterol;
    private RadioButton radioCholesterolHigh;
    private RadioButton radioCholesterolLow;
    private Button buttonBack;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_status);

        // Initialize UI elements
        initViews();

        // Set up listeners for checkboxes and buttons
        setupListeners();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        editTextAge = findViewById(R.id.editText_age_limit);
        checkBoxUlcer = findViewById(R.id.checkbox_ulcer);
        checkBoxPressure = findViewById(R.id.checkbox_pressure);
        radioGroupPressure = findViewById(R.id.radioGroup_pressure);
        radioPressureHigh = findViewById(R.id.radio_pressure_high);
        radioPressureLow = findViewById(R.id.radio_pressure_low);
        checkBoxSugar = findViewById(R.id.checkbox_sugar);
        radioGroupSugar = findViewById(R.id.radioGroup_sugar);
        radioSugarHigh = findViewById(R.id.radio_sugar_high);
        radioSugarLow = findViewById(R.id.radio_sugar_low);
        checkBoxCholesterol = findViewById(R.id.checkbox_cholesterol);
        radioGroupCholesterol = findViewById(R.id.radioGroup_cholesterol);
        radioCholesterolHigh = findViewById(R.id.radio_cholesterol_high);
        radioCholesterolLow = findViewById(R.id.radio_cholesterol_low);
        buttonBack = findViewById(R.id.button_back);
        buttonNext = findViewById(R.id.button_next);
    }

    /**
     * Sets up click listeners and change listeners for UI elements.
     */
    private void setupListeners() {
        // Listeners for main checkboxes to enable/disable their respective RadioGroups
        checkBoxPressure.setOnCheckedChangeListener((buttonView, isChecked) -> {
            radioGroupPressure.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (!isChecked) { // Clear selection if checkbox is unchecked
                radioGroupPressure.clearCheck();
            }
        });

        checkBoxSugar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            radioGroupSugar.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (!isChecked) {
                radioGroupSugar.clearCheck();
            }
        });

        checkBoxCholesterol.setOnCheckedChangeListener((buttonView, isChecked) -> {
            radioGroupCholesterol.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            if (!isChecked) {
                radioGroupCholesterol.clearCheck();
            }
        });

        // Initial state: hide radio groups
        radioGroupPressure.setVisibility(View.GONE);
        radioGroupSugar.setVisibility(View.GONE);
        radioGroupCholesterol.setVisibility(View.GONE);

        // Listener for the "Back" button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity (HomePage)
            }
        });

        // Listener for the "Next" button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectAndDisplayHealthData();
                // In a real app, you would process or save this data and then navigate
                // For example: Intent intent = new Intent(HealthStatusActivity.this, HealthyFoodSuggestionsActivity.class);
                // startActivity(intent);
            }
        });
    }

    /**
     * Collects all entered health data and displays it in a Toast message.
     * In a real application, this data would be saved or used for filtering.
     */
    private void collectAndDisplayHealthData() {
        String ageStr = editTextAge.getText().toString().trim();
        int age = -1;
        if (!TextUtils.isEmpty(ageStr)) {
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid age.", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(this, "Please enter your age.", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder healthSummary = new StringBuilder();
        healthSummary.append("Age: ").append(age).append("\n");
        healthSummary.append("Health Status:\n");

        if (checkBoxUlcer.isChecked()) {
            healthSummary.append("- Ulcer: Yes\n");
        } else {
            healthSummary.append("- Ulcer: No\n");
        }

        healthSummary.append("- Pressure: ");
        if (checkBoxPressure.isChecked()) {
            int selectedPressureId = radioGroupPressure.getCheckedRadioButtonId();
            if (selectedPressureId == R.id.radio_pressure_high) {
                healthSummary.append("High\n");
            } else if (selectedPressureId == R.id.radio_pressure_low) {
                healthSummary.append("Low\n");
            } else {
                healthSummary.append("Selected but not specified (High/Low)\n"); // Should not happen with proper UX
            }
        } else {
            healthSummary.append("Not selected\n");
        }

        healthSummary.append("- Sugar: ");
        if (checkBoxSugar.isChecked()) {
            int selectedSugarId = radioGroupSugar.getCheckedRadioButtonId();
            if (selectedSugarId == R.id.radio_sugar_high) {
                healthSummary.append("High\n");
            } else if (selectedSugarId == R.id.radio_sugar_low) {
                healthSummary.append("Low\n");
            } else {
                healthSummary.append("Selected but not specified (High/Low)\n");
            }
        } else {
            healthSummary.append("Not selected\n");
        }

        healthSummary.append("- Cholesterol: ");
        if (checkBoxCholesterol.isChecked()) {
            int selectedCholesterolId = radioGroupCholesterol.getCheckedRadioButtonId();
            if (selectedCholesterolId == R.id.radio_cholesterol_high) {
                healthSummary.append("High\n");
            } else if (selectedCholesterolId == R.id.radio_cholesterol_low) {
                healthSummary.append("Low\n");
            } else {
                healthSummary.append("Selected but not specified (High/Low)\n");
            }
        } else {
            healthSummary.append("Not selected\n");
        }

        Toast.makeText(this, healthSummary.toString(), Toast.LENGTH_LONG).show();
    }
}
