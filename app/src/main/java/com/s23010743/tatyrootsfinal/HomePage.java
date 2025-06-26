package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import java.util.Random; // Import Random class

public class HomePage extends AppCompatActivity {

    // Declare UI elements
    private Button buttonDesiredFood;
    private Button buttonHealthyFood;
    private Button buttonMakeMyFood;
    private Button buttonVibes; // Changed variable name for clarity
    private Button buttonHealthyTips;
    private TextView textViewAppTitle;

    // Firebase Authentication instance (optional, for logout)
    private FirebaseAuth mAuth;

    // Array of daily lucky quotes
    private String[] dailyLuckyQuotes;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance();

        // Initialize Random for quotes
        random = new Random();

        // Initialize UI elements
        initViews();

        // Set up listeners
        setupListeners();

        // Initialize quotes array from string resources
        dailyLuckyQuotes = getResources().getStringArray(R.array.daily_lucky_quotes_array);
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        textViewAppTitle = findViewById(R.id.textView_appTitle);
        buttonDesiredFood = findViewById(R.id.button_desiredFood);
        buttonHealthyFood = findViewById(R.id.button_healthyFood);
        buttonMakeMyFood = findViewById(R.id.button_makeMyFood);
        buttonVibes = findViewById(R.id.button_news); // Still uses the original 'button_news' ID from XML
        buttonHealthyTips = findViewById(R.id.button_healthyTips);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     */
    private void setupListeners() {
        buttonDesiredFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, DesiredFoodMenuActivity.class);
                startActivity(intent);
            }
        });

        buttonHealthyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, HealthStatusActivity.class);
                startActivity(intent);
            }
        });

        buttonMakeMyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, IngredientSelectionActivity.class);
                startActivity(intent);
            }
        });

        // Listener for the "Vibes" button
        buttonVibes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRandomLuckyQuote();
            }
        });

        buttonHealthyTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, HealthTipsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Displays a random daily lucky quote as a Toast.
     */
    private void displayRandomLuckyQuote() {
        if (dailyLuckyQuotes != null && dailyLuckyQuotes.length > 0) {
            int randomIndex = random.nextInt(dailyLuckyQuotes.length);
            String quote = dailyLuckyQuotes[randomIndex];
            Toast.makeText(this, "Vibe Check: " + quote, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No vibes available today.", Toast.LENGTH_SHORT).show();
        }
    }

    // Optional: Add a logout method if you want to include a logout button/menu item
    private void logoutUser() {
        if (mAuth != null) {
            mAuth.signOut();
            Intent intent = new Intent(HomePage.this, LoginOptionActivity.class); // Or WelcomeActivity.class
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
