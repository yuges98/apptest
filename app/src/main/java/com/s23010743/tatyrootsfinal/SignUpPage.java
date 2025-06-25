package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView; // Make sure TextView is imported

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPage extends AppCompatActivity {

    // UI Elements
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextPhoneNumber; // New field
    private TextInputEditText editTextNickName;    // New field
    private Button buttonSignUp;

    // Firebase Authentication instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_signup_page.xml layout
        setContentView(R.layout.activity_signup_page);

        // Initialize Firebase Auth
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
        editTextEmail = findViewById(R.id.editText_email_signup);
        editTextPassword = findViewById(R.id.editText_password_signup);
        editTextPhoneNumber = findViewById(R.id.editText_phoneNumber_signup); // Initialize Phone Number
        editTextNickName = findViewById(R.id.editText_nickName_signup);       // Initialize Nick Name
        buttonSignUp = findViewById(R.id.button_signUp_register);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     */
    private void setupListeners() {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistration();
            }
        });
    }

    /**
     * Attempts to register the user with provided email, password, phone number, and nickname.
     * Handles input validation and Firebase authentication.
     */
    private void attemptRegistration() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim(); // Get Phone Number
        String nickName = editTextNickName.getText().toString().trim();       // Get Nick Name

        // Input validation
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(nickName)) {
            Toast.makeText(SignUpPage.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return; // Stop execution if fields are empty
        }

        // Basic password length check (Firebase usually has its own, but good client-side UX)
        if (password.length() < 6) {
            Toast.makeText(SignUpPage.this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show a loading indicator (optional)

        // Firebase User Registration
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Hide loading indicator here if you showed one

                        if (task.isSuccessful()) {
                            // Registration success
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpPage.this, "Registration Success! Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();

                            // You can add logic here to save phoneNumber and nickName to Firestore or Realtime Database
                            // using the user.getUid() as a key. (This is a future step)

                            navigateToHomePage(); // Navigate to Home page after successful registration
                        } else {
                            // If sign up fails, display a message to the user.
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown registration error.";
                            Toast.makeText(SignUpPage.this, "Registration Failed: " + errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /**
     * Navigates to the Home Page upon successful registration.
     * Clears the back stack to prevent returning to signup/login pages.
     */
    private void navigateToHomePage() {
        Intent intent = new Intent(SignUpPage.this, HomePage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finish current activity
    }
}
