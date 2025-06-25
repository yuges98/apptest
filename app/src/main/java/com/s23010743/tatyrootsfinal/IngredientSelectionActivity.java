package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar; // For loading indicator
import android.widget.Toast;
import android.widget.TextView; // Make sure TextView is imported

import androidx.appcompat.app.AppCompatActivity;

// For API Call (You'll need a library for HTTP requests, like OkHttp or Volley)
// For simplicity and demonstration in a basic Android Studio project,
// we will simulate the API call with a placeholder.
// In a real project, you would add a dependency like:
// implementation("com.squareup.okhttp3:okhttp:4.9.3")
// And implement the actual network request.

public class IngredientSelectionActivity extends AppCompatActivity {

    // UI Elements
    private EditText editTextIngredients;
    private Button buttonBack;
    private Button buttonNext;
    private ProgressBar progressBar; // Loading indicator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_selection);

        // Initialize UI elements
        initViews();

        // Set up listeners for buttons
        setupListeners();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        editTextIngredients = findViewById(R.id.editText_ingredients);
        buttonBack = findViewById(R.id.button_back);
        buttonNext = findViewById(R.id.button_next);
        progressBar = findViewById(R.id.progressBar_loading); // Initialize ProgressBar
    }

    /**
     * Sets up click listeners for interactive UI elements.
     */
    private void setupListeners() {
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
                processIngredientsAndGenerateFood();
            }
        });
    }

    /**
     * Processes the entered ingredients and simulates calling a generative AI API
     * to get food suggestions or recipes.
     */
    private void processIngredientsAndGenerateFood() {
        String ingredients = editTextIngredients.getText().toString().trim();

        if (TextUtils.isEmpty(ingredients)) {
            Toast.makeText(this, "Please type your ingredients.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show loading indicator
        progressBar.setVisibility(View.VISIBLE);
        buttonNext.setEnabled(false); // Disable button during loading

        // Simulate an API call with a delay. In a real app, you would make a network request.
        // For actual API calls, you'd use a library like OkHttp or implement an AsyncTask/Coroutine
        // to perform the network request on a background thread.
        // The following is a placeholder for the actual API call logic.

        // Example of a placeholder for the Gemini API call:
        // You would integrate your actual API call here.
        // For a real scenario, you'd make a HTTP POST request to the Gemini API endpoint.
        // Given your previous interactions, you know the structure for making a fetch call
        // if this were a web environment. In Android, you'd use OkHttp/Volley/Retrofit.

        String prompt = "Generate a simple recipe using the following ingredients: " + ingredients + ". Provide steps and estimated cooking time.";

        // --- Start of simulated API call (replace with actual network code) ---
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Simulate a response from the AI
                String simulatedApiResponse = "Here's a simple recipe for you!\n\n" +
                        "**Dish Name:** Quick Veggie Stir-fry\n\n" +
                        "**Ingredients:**\n" +
                        "- " + ingredients + "\n" +
                        "- 1 tbsp soy sauce\n" +
                        "- 1 tsp ginger (grated)\n" +
                        "- 1 tsp garlic (minced)\n" +
                        "- 1 tbsp oil\n\n" +
                        "**Instructions:**\n" +
                        "1. Heat oil in a pan. Add ginger and garlic, sauté for 30 seconds.\n" +
                        "2. Add your ingredients and stir-fry for 5-7 minutes until tender-crisp.\n" +
                        "3. Stir in soy sauce. Cook for 1 more minute.\n" +
                        "4. Serve hot with rice or noodles.\n\n" +
                        "**Cooking Time:** Approximately 15-20 minutes.";

                // Hide loading indicator
                progressBar.setVisibility(View.GONE);
                buttonNext.setEnabled(true);

                // Display the generated recipe (for now, in a Toast)
                // In a real app, you might start a new activity to show this recipe in a better format.
                Toast.makeText(IngredientSelectionActivity.this, simulatedApiResponse, Toast.LENGTH_LONG).show();
                Log.d("IngredientSelection", "Generated Recipe: " + simulatedApiResponse); // Log for debugging

                // Optional: Navigate to a RecipeDisplayActivity here
                // Intent intent = new Intent(IngredientSelectionActivity.this, RecipeDisplayActivity.class);
                // intent.putExtra("recipe_text", simulatedApiResponse);
                // startActivity(intent);

            }
        }, 2000); // Simulate 2-second network delay
        // --- End of simulated API call ---
    }
}
