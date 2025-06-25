package com.s23010743.tatyrootsfinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView; // Import TextView

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth; // Import FirebaseAuth for logout

public class HomePage extends AppCompatActivity {

    // Declare UI elements
    private Button buttonDesiredFood;
    private Button buttonHealthyFood;
    private Button buttonMakeMyFood;
    private Button buttonNews;
    private Button buttonHealthyTips;
    private TextView textViewAppTitle; // Declare TextView for app title

    // Firebase Authentication instance (optional, for logout)
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_home_page.xml layout
        setContentView(R.layout.activity_home_page);

        // Initialize Firebase Auth (if you plan to add a logout feature)
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        initViews();

        // Set up click listeners for buttons
        setupListeners();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        textViewAppTitle = findViewById(R.id.textView_appTitle); // Initialize app title TextView
        buttonDesiredFood = findViewById(R.id.button_desiredFood);
        buttonHealthyFood = findViewById(R.id.button_healthyFood);
        buttonMakeMyFood = findViewById(R.id.button_makeMyFood);
        buttonNews = findViewById(R.id.button_news);
        buttonHealthyTips = findViewById(R.id.button_healthyTips);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     * For now, each button displays a Toast message. These can be replaced
     * with Intent calls to new activities later.
     */
    private void setupListeners() {
        buttonDesiredFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Desired Food functionality coming soon!", Toast.LENGTH_SHORT).show();
                // Example: Intent intent = new Intent(HomePage.this, DesiredFoodActivity.class);
                // startActivity(intent);
            }
        });

        buttonHealthyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Healthy Food functionality coming soon!", Toast.LENGTH_SHORT).show();
                // Example: Intent intent = new Intent(HomePage.this, HealthyFoodActivity.class);
                // startActivity(intent);
            }
        });

        buttonMakeMyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Make My Food functionality coming soon!", Toast.LENGTH_SHORT).show();
                // Example: Intent intent = new Intent(HomePage.this, MakeMyFoodActivity.class);
                // startActivity(intent);
            }
        });

        buttonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "News functionality coming soon!", Toast.LENGTH_SHORT).show();
                // Example: Intent intent = new Intent(HomePage.this, NewsActivity.class);
                // startActivity(intent);
            }
        });

        buttonHealthyTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Healthy Tips functionality coming soon!", Toast.LENGTH_SHORT).show();
                // Example: Intent intent = new Intent(HomePage.this, HealthyTipsActivity.class);
                // startActivity(intent);
            }
        });
    }

    // Optional: Add a logout method if you want to include a logout button/menu item
    private void logoutUser() {
        if (mAuth != null) {
            mAuth.signOut();
            // Navigate back to the LoginOptionActivity or WelcomeActivity after logout
            // Intent intent = new Intent(HomePage.this, LoginOptionActivity.class);
            // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // startActivity(intent);
            // finish();
            Toast.makeText(HomePage.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
