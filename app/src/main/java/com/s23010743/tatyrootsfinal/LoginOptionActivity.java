package com.s23010743.tatyrootsfinal; // REMEMBER TO CHANGE THIS TO YOUR ACTUAL PACKAGE NAME

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginOptionActivity extends AppCompatActivity {

    // Declare UI elements
    private Button buttonLogin;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_login_option.xml layout
        setContentView(R.layout.activity_login_option);

        // Initialize UI elements
        initViews();

        // Set up click listeners for buttons
        setupListeners();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     * Follows good practice of separating view initialization.
     */
    private void initViews() {
        buttonLogin = findViewById(R.id.button_login);
        buttonSignUp = findViewById(R.id.button_signUp);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     * Encapsulates listener setup for better readability.
     */
    private void setupListeners() {
        // Listener for the "Log In" button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLoginPage();
            }
        });

        // Listener for the "Sign Up" button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUpPage();
            }
        });
    }

    /**
     * Handles navigation to the Login Page.
     * Uses an Intent to start the LoginPage Activity.
     * Note: LoginPageActivity will be created based on your next UI.
     */
    private void navigateToLoginPage() {
        Intent intent = new Intent(LoginOptionActivity.this, LoginPage.class);
        startActivity(intent);
        // Optional: finish() if you don't want to come back to this activity
        // finish();
    }

    /**
     * Handles navigation to the Sign Up Page.
     * Uses an Intent to start the SignUpPage Activity.
     * Note: SignUpPage will be created based on your next UI.
     */
    private void navigateToSignUpPage() {
        Intent intent = new Intent(LoginOptionActivity.this, SignUpPage.class);
        startActivity(intent);
        // Optional: finish() if you don't want to come back to this activity
        // finish();
    }
}