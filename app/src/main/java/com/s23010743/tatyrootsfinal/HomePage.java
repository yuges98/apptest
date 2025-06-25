package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    // Declare UI elements
    private Button buttonDesiredFood;
    private Button buttonHealthyFood;
    private Button buttonMakeMyFood;
    private Button buttonNews;
    private Button buttonHealthyTips;
    private TextView textViewAppTitle;

    // Firebase Authentication instance (optional, for logout)
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance();

        initViews();
        setupListeners();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        textViewAppTitle = findViewById(R.id.textView_appTitle);
        buttonDesiredFood = findViewById(R.id.button_desiredFood);
        buttonHealthyFood = findViewById(R.id.button_healthyFood);
        buttonMakeMyFood = findViewById(R.id.button_makeMyFood);
        buttonNews = findViewById(R.id.button_news);
        buttonHealthyTips = findViewById(R.id.button_healthyTips);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     */
    private void setupListeners() {
        buttonDesiredFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the DesiredFoodMenuActivity
                Intent intent = new Intent(HomePage.this, DesiredFoodMenuActivity.class);
                startActivity(intent);
            }
        });

        buttonHealthyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HealthStatusActivity
                Intent intent = new Intent(HomePage.this, HealthStatusActivity.class);
                startActivity(intent);
            }
        });

        buttonMakeMyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the IngredientSelectionActivity
                Intent intent = new Intent(HomePage.this, IngredientSelectionActivity.class);
                startActivity(intent);
            }
        });

        buttonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "News functionality coming soon!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonHealthyTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Healthy Tips functionality coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Optional: Add a logout method if you want to include a logout button/menu item
    private void logoutUser() {
        if (mAuth != null) {
            mAuth.signOut();
            Intent intent = new Intent(HomePage.this, LoginOptionActivity.class); // Or WelcomeActivity.class
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(HomePage.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
