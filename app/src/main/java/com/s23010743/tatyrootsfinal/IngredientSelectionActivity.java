package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IngredientSelectionActivity extends AppCompatActivity {

    private EditText editTextIngredients;
    private Button buttonBack;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_selection);

        initViews();
        setupListeners();
    }

    private void initViews() {
        editTextIngredients = findViewById(R.id.editText_ingredients);
        buttonBack = findViewById(R.id.button_back_ingredients);
        buttonNext = findViewById(R.id.button_next_ingredients);
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
                String ingredients = editTextIngredients.getText().toString().trim();

                if (TextUtils.isEmpty(ingredients)) {
                    Toast.makeText(IngredientSelectionActivity.this, "Please type your food recipe ingredients.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Redirect to the new CustomOrderPaymentInfoActivity
                Intent intent = new Intent(IngredientSelectionActivity.this, CustomOrderPaymentInfoActivity.class);
                // Optionally pass the ingredients if they need to be displayed/processed on the next page
                intent.putExtra("custom_ingredients", ingredients);
                startActivity(intent);
                // No finish() here, so user can return to this page if needed
            }
        });
    }
}
