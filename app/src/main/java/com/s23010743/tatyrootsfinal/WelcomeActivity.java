package com.s23010743.tatyrootsfinal; // REMEMBER TO CHANGE THIS TO YOUR ACTUAL PACKAGE NAME

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    // Declare UI elements
    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_welcome.xml layout
        setContentView(R.layout.activity_welcome);

        // Initialize UI elements by finding them by their IDs from the layout
        initViews();

        // Set up click listeners for buttons
        setupListeners();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     * Follows good practice of separating view initialization.
     */
    private void initViews() {
        getStartedButton = findViewById(R.id.button_getStarted);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     * Encapsulates listener setup for better readability.
     */
    private void setupListeners() {
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When "Get Started" button is clicked, navigate to the Login Option Page
                navigateToLoginOptionPage();
            }
        });
    }

    /**
     * Handles navigation to the Login Option Page.
     * Uses an Intent to start the next Activity.
     * Note: LoginOptionActivity will be created based on your next UI.
     */
    private void navigateToLoginOptionPage() {
        // Create an Intent to start the LoginOptionActivity
        // Replace 'LoginOptionActivity.class' with the actual class name
        // once you create it. For now, this is a placeholder.
        Intent intent = new Intent(WelcomeActivity.this, LoginOptionActivity.class);
        startActivity(intent);
        // If you don't want the user to return to WelcomeActivity using the back button,
        // you can call finish() after starting the new activity.
        // finish();
    }
}