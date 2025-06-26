package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView; // Added for food item images

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LowBPFoodMenuActivity extends AppCompatActivity {

    private List<FoodItem> lowBPFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_bp_food_menu);

        initializeLowBPFoodItems();
        displayLowBPFoodItems();
        setupListeners();
    }

    /**
     * Initializes the list of food items specifically for Low BP.
     * Ensure you have the drawable images (img_dates_cake, img_green_gram, img_chicken_soup)
     * in your res/drawable folder.
     */
    private void initializeLowBPFoodItems() {
        lowBPFoodItems = new ArrayList<>();
        lowBPFoodItems.add(new FoodItem(
                "Dates Cake",
                "Rs 700.00",
                R.drawable.img_dates_cake, // Replace with your actual drawable
                "A delicious and energy-rich cake, dates are known to be beneficial for blood pressure."
        ));
        lowBPFoodItems.add(new FoodItem(
                "Green Gram",
                "Rs 550.00",
                R.drawable.img_green_gram, // Replace with your actual drawable
                "Green gram is a good source of protein and fiber, often recommended for a balanced diet."
        ));
        lowBPFoodItems.add(new FoodItem(
                "Chicken Soup",
                "Rs 650.00",
                R.drawable.img_chicken_soup, // Replace with your actual drawable
                "A comforting and nutritious soup, often used for its hydrating properties and mild flavor."
        ));
        // Add more specific low BP food items if needed
    }

    /**
     * Displays the initialized food items on the UI.
     * This method assumes specific IDs for TextViews and ImageViews in activity_low_bp_food_menu.xml
     */
    private void displayLowBPFoodItems() {
        // Food Item 1: Dates Cake
        ((ImageView) findViewById(R.id.imageView_low_bp_food1)).setImageResource(lowBPFoodItems.get(0).getImageResId());
        ((TextView) findViewById(R.id.textView_low_bp_food1_name)).setText(lowBPFoodItems.get(0).getName());
        ((TextView) findViewById(R.id.textView_low_bp_food1_price)).setText(lowBPFoodItems.get(0).getPrice());

        // Food Item 2: Green Gram
        ((ImageView) findViewById(R.id.imageView_low_bp_food2)).setImageResource(lowBPFoodItems.get(1).getImageResId());
        ((TextView) findViewById(R.id.textView_low_bp_food2_name)).setText(lowBPFoodItems.get(1).getName());
        ((TextView) findViewById(R.id.textView_low_bp_food2_price)).setText(lowBPFoodItems.get(1).getPrice());

        // Food Item 3: Chicken Soup
        ((ImageView) findViewById(R.id.imageView_low_bp_food3)).setImageResource(lowBPFoodItems.get(2).getImageResId());
        ((TextView) findViewById(R.id.textView_low_bp_food3_name)).setText(lowBPFoodItems.get(2).getName());
        ((TextView) findViewById(R.id.textView_low_bp_food3_price)).setText(lowBPFoodItems.get(2).getPrice());
    }

    /**
     * Sets up click listeners for buttons on the Low BP menu page.
     */
    private void setupListeners() {
        Button btnBack = findViewById(R.id.button_low_bp_menu_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HealthStatusActivity
            }
        });

        // Setup "Place Order" buttons for each item
        findViewById(R.id.button_place_order_low_bp_food1).setOnClickListener(v -> navigateToOrderDetails(lowBPFoodItems.get(0)));
        findViewById(R.id.button_place_order_low_bp_food2).setOnClickListener(v -> navigateToOrderDetails(lowBPFoodItems.get(1)));
        findViewById(R.id.button_place_order_low_bp_food3).setOnClickListener(v -> navigateToOrderDetails(lowBPFoodItems.get(2)));
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(LowBPFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Pass the FoodItem object
        startActivity(intent);
    }
}
