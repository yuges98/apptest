package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HighBPFoodMenuActivity extends AppCompatActivity {

    private List<FoodItem> highBPFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_bp_food_menu);

        initializeHighBPFoodItems();
        displayHighBPFoodItems();
        setupListeners();
    }

    /**
     * Initializes the list of food items specifically for High BP.
     * Ensure you have the drawable images (img_oats, img_watermelon_juice, img_fish_soup)
     * in your res/drawable folder.
     */
    private void initializeHighBPFoodItems() {
        highBPFoodItems = new ArrayList<>();
        highBPFoodItems.add(new FoodItem(
                "Oats",
                "Rs 450.00",
                R.drawable.img_oats, // Replace with your actual drawable
                "Oats are a whole-grain food, rich in fiber and beneficial for heart health and blood pressure management."
        ));
        highBPFoodItems.add(new FoodItem(
                "Watermelon Juice",
                "Rs 370.00",
                R.drawable.img_watermelon_juice, // Replace with your actual drawable
                "Watermelon is hydrating and contains citrulline, which may help reduce blood pressure."
        ));
        highBPFoodItems.add(new FoodItem(
                "Fish Soup",
                "Rs 600.00",
                R.drawable.img_fish_soup, // Replace with your actual drawable
                "A light and healthy soup, fish (especially fatty fish) is rich in Omega-3 fatty acids beneficial for cardiovascular health."
        ));
        // Add more specific high BP food items if needed
    }

    /**
     * Displays the initialized food items on the UI.
     * This method assumes specific IDs for TextViews and ImageViews in activity_high_bp_food_menu.xml
     */
    private void displayHighBPFoodItems() {
        // Food Item 1: Oats
        ((ImageView) findViewById(R.id.imageView_high_bp_food1)).setImageResource(highBPFoodItems.get(0).getImageResId());
        ((TextView) findViewById(R.id.textView_high_bp_food1_name)).setText(highBPFoodItems.get(0).getName());
        ((TextView) findViewById(R.id.textView_high_bp_food1_price)).setText(highBPFoodItems.get(0).getPrice());

        // Food Item 2: Watermelon Juice
        ((ImageView) findViewById(R.id.imageView_high_bp_food2)).setImageResource(highBPFoodItems.get(1).getImageResId());
        ((TextView) findViewById(R.id.textView_high_bp_food2_name)).setText(highBPFoodItems.get(1).getName());
        ((TextView) findViewById(R.id.textView_high_bp_food2_price)).setText(highBPFoodItems.get(1).getPrice());

        // Food Item 3: Fish Soup
        ((ImageView) findViewById(R.id.imageView_high_bp_food3)).setImageResource(highBPFoodItems.get(2).getImageResId());
        ((TextView) findViewById(R.id.textView_high_bp_food3_name)).setText(highBPFoodItems.get(2).getName());
        ((TextView) findViewById(R.id.textView_high_bp_food3_price)).setText(highBPFoodItems.get(2).getPrice());
    }

    /**
     * Sets up click listeners for buttons on the High BP menu page.
     */
    private void setupListeners() {
        Button btnBack = findViewById(R.id.button_high_bp_menu_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HealthStatusActivity
            }
        });

        // Setup "Place Order" buttons for each item
        findViewById(R.id.button_place_order_high_bp_food1).setOnClickListener(v -> navigateToOrderDetails(highBPFoodItems.get(0)));
        findViewById(R.id.button_place_order_high_bp_food2).setOnClickListener(v -> navigateToOrderDetails(highBPFoodItems.get(1)));
        findViewById(R.id.button_place_order_high_bp_food3).setOnClickListener(v -> navigateToOrderDetails(highBPFoodItems.get(2)));
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(HighBPFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Pass the FoodItem object
        startActivity(intent);
    }
}
