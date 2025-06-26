package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity extends AppCompatActivity {

    // UI Elements
    private ImageView foodImageView1; // First image (from FoodItem)
    private ImageView foodImageView2; // Second image (hardcoded for now to match screenshot)
    private TextView foodNameTextView; // This will now refer to the title TextView
    private TextView foodPriceTextView;
    private TextView foodDescriptionTextView;
    private Button placeOrderButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        // Initialize UI elements
        initViews();

        // Get data from the Intent
        FoodItem foodItem = (FoodItem) getIntent().getSerializableExtra("food_item");

        if (foodItem != null) {
            displayFoodDetails(foodItem);
        } else {
            Toast.makeText(this, "Food details not available.", Toast.LENGTH_SHORT).show();
            finish(); // Close activity if no data
        }

        // Set up listeners
        setupListeners(foodItem); // Pass foodItem to listener for order details
    }

    /**
     * Initializes UI elements by finding their IDs.
     */
    private void initViews() {
        foodImageView1 = findViewById(R.id.imageView_food_detail_1);
        foodImageView2 = findViewById(R.id.imageView_food_detail_2);
        foodNameTextView = findViewById(R.id.textView_food_detail_name_title); // Corrected ID reference
        foodPriceTextView = findViewById(R.id.textView_food_detail_price);
        foodDescriptionTextView = findViewById(R.id.textView_food_detail_description);
        placeOrderButton = findViewById(R.id.button_detail_place_order);
        backButton = findViewById(R.id.button_detail_back);
    }

    /**
     * Displays the details of the food item on the UI.
     * @param item The FoodItem object to display.
     */
    private void displayFoodDetails(FoodItem item) {
        foodImageView1.setImageResource(item.getImageResId());
        // For the second image, hardcode for now to match the screenshot's specific example for Kottu.
        // In a real app with diverse items, you'd need to extend FoodItem model to include a list/array of image IDs.
        if (item.getName().equals("Chicken Kottu")) {
            foodImageView2.setImageResource(R.drawable.img_chicken_kottu); // Placeholder for a second Kottu image
            foodImageView2.setVisibility(View.VISIBLE); // Ensure it's visible for Kottu
        } else {
            // For other items, hide the second image
            foodImageView2.setVisibility(View.GONE);
        }

        foodNameTextView.setText(item.getName());
        foodPriceTextView.setText(item.getPrice());
        foodDescriptionTextView.setText(item.getDescription());
    }

    /**
     * Sets up click listeners for buttons.
     * @param foodItem The FoodItem object, needed for order confirmation.
     */
    private void setupListeners(final FoodItem foodItem) {
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodItem != null) {
                    // Navigate to OrderDetailsActivity from FoodDetailActivity
                    Intent intent = new Intent(FoodDetailActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("food_item_for_order", foodItem); // Pass the FoodItem object
                    startActivity(intent);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to DesiredFoodMenuActivity
            }
        });
    }
}
