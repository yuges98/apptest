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
import com.google.firebase.firestore.FirebaseFirestore; // Import FirebaseFirestore
import java.util.HashMap; // Import HashMap
import java.util.Map; // Import Map

public class SignUpPage extends AppCompatActivity {

    // UI Elements
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextPhoneNumber;
    private TextInputEditText editTextNickName;
    private Button buttonSignUp;

    // Firebase Authentication and Firestore instances
    private FirebaseAuth mAuth;
    private FirebaseFirestore db; // Declare FirebaseFirestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the activity_signup_page.xml layout
        setContentView(R.layout.activity_signup_page);

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance(); // Initialize FirebaseFirestore

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
        editTextPhoneNumber = findViewById(R.id.editText_phoneNumber_signup);
        editTextNickName = findViewById(R.id.editText_nickName_signup);
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
     * Attempts to register the user with provided email and password using Firebase Authentication.
     * Upon successful authentication, it then saves additional user details (phone number, nickname)
     * to Firebase Firestore. Finally, navigates to the Home page.
     */
    private void attemptRegistration() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String nickName = editTextNickName.getText().toString().trim();

        // Input validation
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(nickName)) {
            Toast.makeText(SignUpPage.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Basic password length check
        if (password.length() < 6) {
            Toast.makeText(SignUpPage.this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show a loading indicator (optional, you can add a ProgressBar here)
        // For example: showProgressBar();

        // Step 1: Firebase User Registration (Email/Password)
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> authTask) {
                        if (authTask.isSuccessful()) {
                            // Registration success
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid(); // Get the unique user ID from Firebase Auth

                                // Step 2: Save additional user details to Firestore
                                Map<String, Object> userData = new HashMap<>();
                                userData.put("email", email); // Store email for reference
                                userData.put("phoneNumber", phoneNumber);
                                userData.put("nickName", nickName);
                                // You can add other profile details here if needed

                                // Save data in a 'users' collection with the user's UID as the document ID
                                db.collection("users").document(userId)
                                        .set(userData)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> firestoreTask) {
                                                // Hide loading indicator here (if you showed one)
                                                // For example: hideProgressBar();

                                                if (firestoreTask.isSuccessful()) {
                                                    Toast.makeText(SignUpPage.this, "Registration Success! Welcome " + nickName, Toast.LENGTH_SHORT).show();
                                                    // Step 3: Navigate to Home page after both Auth and Firestore are successful
                                                    navigateToHomePage();
                                                } else {
                                                    // Firestore save failed
                                                    Toast.makeText(SignUpPage.this, "Failed to save user data: " + firestoreTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    // Optional: Consider deleting the Firebase Auth user if Firestore save fails
                                                    user.delete(); // This would delete the auth user if their data couldn't be saved.
                                                }
                                            }
                                        });

                            } else {
                                // Should not happen if authTask.isSuccessful()
                                // Hide loading indicator here
                                Toast.makeText(SignUpPage.this, "Registration successful but user is null.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Firebase Authentication registration failed
                            // Hide loading indicator here
                            String errorMessage = authTask.getException() != null ? authTask.getException().getMessage() : "Unknown registration error.";
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
