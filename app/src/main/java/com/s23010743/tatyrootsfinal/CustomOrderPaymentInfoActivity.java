package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomOrderPaymentInfoActivity extends AppCompatActivity {

    private Button buttonCall;
    private Button buttonNext; // Assuming 'next' might lead to general order confirmation or home
    private Button buttonBack;

    // Customer care number (example)
    private static final String CUSTOMER_CARE_NUMBER = "tel:0711234567"; // Replace with your actual number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_order_payment_info);

        initViews();
        setupListeners();
    }

    private void initViews() {
        buttonCall = findViewById(R.id.button_call_customer_care);
        buttonNext = findViewById(R.id.button_custom_order_next);
        buttonBack = findViewById(R.id.button_custom_order_back);
    }

    private void setupListeners() {
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initiate a phone call
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse(CUSTOMER_CARE_NUMBER));
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                } else {
                    Toast.makeText(CustomOrderPaymentInfoActivity.this, "No phone app found to make a call.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // The "Next" button on this page will likely lead to the home screen or a generic confirmation
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For a custom order, payment is handled offline.
                // Redirect to HomePage or a general order confirmation indicating offline payment
                Toast.makeText(CustomOrderPaymentInfoActivity.this, "Custom order submitted. Please call to confirm payment.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CustomOrderPaymentInfoActivity.this, HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear back stack
                startActivity(intent);
                finish(); // Finish this activity
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to IngredientSelectionActivity
            }
        });
    }
}
