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
    private Button buttonNext;
    private Button buttonBack;

    // The customer care number you provided
    private static final String CUSTOMER_CARE_NUMBER = "tel:0762845431";

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

                // Check if there's any app that can handle this intent
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                } else {
                    // This Toast indicates that no dialer app was found on the device/emulator
                    Toast.makeText(CustomOrderPaymentInfoActivity.this, "No phone or dialer app found on this device.", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to OrderConfirmationActivity
                Toast.makeText(CustomOrderPaymentInfoActivity.this, "Custom order submitted!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CustomOrderPaymentInfoActivity.this, OrderConfirmationActivity.class);
                startActivity(intent);
                finish(); // Finish this activity so user can't go back to it using back button
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
