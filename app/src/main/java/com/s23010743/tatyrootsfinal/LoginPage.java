package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    // UI Elements
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgotPassword;

    // Firebase Authentication instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        initViews();

        // Set up listeners for buttons and text views
        setupListeners();
    }

    /**
     * Called when the activity is first created or recreated.
     * Check if user is already signed in (non-null) and update UI accordingly.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is already logged in, navigate to Home page
            navigateToHomePage();
        }
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        editTextEmail = findViewById(R.id.editText_email);
        editTextPassword = findViewById(R.id.editText_password);
        buttonLogin = findViewById(R.id.button_login);
        textViewForgotPassword = findViewById(R.id.textView_forgotPassword);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     */
    private void setupListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToForgotPasswordPage();
            }
        });
    }

    /**
     * Attempts to log in the user with provided email and password.
     * Handles input validation and Firebase authentication.
     */
    private void attemptLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Input validation
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginPage.this, R.string.empty_fields_error, Toast.LENGTH_SHORT).show();
            return; // Stop execution if fields are empty
        }

        // Show a loading indicator (optional, but good for UX)
        // You might want to show a ProgressBar here.

        // Firebase Sign In
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Hide loading indicator here if you showed one

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginPage.this, "Authentication Success. Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            navigateToHomePage();
                        } else {
                            // If sign in fails, display a message to the user.
                            // Get the specific error message from Firebase
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error.";
                            Toast.makeText(LoginPage.this, getString(R.string.login_failed_message, errorMessage), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /**
     * Navigates to the Home Page upon successful login.
     * Clears the back stack to prevent returning to login pages.
     */
    private void navigateToHomePage() {
        //Intent intent = new Intent(LoginPage.this, HomePage.class);
        // Flags to clear the back stack so user can't go back to login/welcome
       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(intent);
        //finish(); // Finish current activity
    }

    /**
     * Navigates to the Forgot Password Page.
     * Note: ForgotPasswordActivity will be created later.
     */
    private void navigateToForgotPasswordPage() {
        // Placeholder for ForgotPasswordActivity
        // Intent intent = new Intent(LoginPage.this, ForgotPasswordActivity.class);
        // startActivity(intent);
        Toast.makeText(LoginPage.this, "Forgot Password functionality coming soon!", Toast.LENGTH_SHORT).show();
    }
}
