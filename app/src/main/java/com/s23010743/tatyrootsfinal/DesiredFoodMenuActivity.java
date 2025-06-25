package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DesiredFoodMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desired_food_menu);

        // Set up click listeners for the "Place Order" buttons and the "Back" button
        setupListeners();
    }

    /**
     * Sets up click listeners for interactive UI elements on the menu page.
     */
    private void setupListeners() {
        // Find the "Place Order" buttons for each food item and set listeners
        Button btnPlaceOrderKottu = findViewById(R.id.button_place_order_kottu);
        Button btnPlaceOrderFriedRice = findViewById(R.id.button_place_order_fried_rice);
        Button btnPlaceOrderNoodles = findViewById(R.id.button_place_order_noodles);

        // Listener for Chicken Kottu "Place Order"
        btnPlaceOrderKottu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DesiredFoodMenuActivity.this, "Chicken Kottu added to order!", Toast.LENGTH_SHORT).show();
                // You would add actual ordering logic here
            }
        });

        // Listener for Chicken Fried Rice "Place Order"
        btnPlaceOrderFriedRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DesiredFoodMenuActivity.this, "Chicken Fried Rice added to order!", Toast.LENGTH_SHORT).show();
                // You would add actual ordering logic here
            }
        });

        // Listener for Chicken Noodles "Place Order"
        btnPlaceOrderNoodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DesiredFoodMenuActivity.this, "Chicken Noodles added to order!", Toast.LENGTH_SHORT).show();
                // You would add actual ordering logic here
            }
        });

        // Set up listener for the "Back" button
        Button btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the HomePage
                finish(); // This will simply close the current activity and go back to the previous one on the stack
            }
        });

        // Optional: Set up listeners for "More Info" TextViews if you want them to be clickable
        TextView tvMoreInfoKottu = findViewById(R.id.textView_more_info_kottu);
        tvMoreInfoKottu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DesiredFoodMenuActivity.this, "Showing more info for Chicken Kottu!", Toast.LENGTH_SHORT).show();
                // Example: Intent to a detailed food item page
            }
        });

        TextView tvMoreInfoFriedRice = findViewById(R.id.textView_more_info_fried_rice);
        tvMoreInfoFriedRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DesiredFoodMenuActivity.this, "Showing more info for Chicken Fried Rice!", Toast.LENGTH_SHORT).show();
            }
        });

        TextView tvMoreInfoNoodles = findViewById(R.id.textView_more_info_noodles);
        tvMoreInfoNoodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DesiredFoodMenuActivity.this, "Showing more info for Chicken Noodles!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
