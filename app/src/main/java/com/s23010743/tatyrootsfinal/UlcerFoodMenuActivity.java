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

public class UlcerFoodMenuActivity extends AppCompatActivity {

    private List<FoodItem> ulcerFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulcer_food_menu);

        initializeUlcerFoodItems();
        displayUlcerFoodItems();
        setupListeners();
    }

    /**
     * Initializes the list of food items specifically for Ulcer.
     * Ensure you have the drawable images (img_banana_juice, img_vegetable_soup, img_boiled_eggs)
     * in your res/drawable folder.
     */
    private void initializeUlcerFoodItems() {
        ulcerFoodItems = new ArrayList<>();
        ulcerFoodItems.add(new FoodItem(
                "Banana Juice",
                "Rs 150.00",
                R.drawable.img_banana_juice, // Replace with your actual drawable
                "Soothing and easy to digest, banana juice can help calm an irritated stomach lining."
        ));
        ulcerFoodItems.add(new FoodItem(
                "Vegetable Soup",
                "Rs 450.00",
                R.drawable.img_vegetable_soup, // Replace with your actual drawable
                "A light and nourishing soup made with mild vegetables, gentle on the digestive system."
        ));
        ulcerFoodItems.add(new FoodItem(
                "Boiled Eggs",
                "Rs 350.00",
                R.drawable.img_boiled_eggs, // Replace with your actual drawable
                "Boiled eggs are a good source of protein and are generally well-tolerated by those with ulcers."
        ));
        // Add more specific ulcer-friendly food items if needed
    }

    /**
     * Displays the initialized food items on the UI.
     */
    private void displayUlcerFoodItems() {
        // Food Item 1: Banana Juice
        ((ImageView) findViewById(R.id.imageView_ulcer_food1)).setImageResource(ulcerFoodItems.get(0).getImageResId());
        ((TextView) findViewById(R.id.textView_ulcer_food1_name)).setText(ulcerFoodItems.get(0).getName());
        ((TextView) findViewById(R.id.textView_ulcer_food1_price)).setText(ulcerFoodItems.get(0).getPrice());

        // Food Item 2: Vegetable Soup
        ((ImageView) findViewById(R.id.imageView_ulcer_food2)).setImageResource(ulcerFoodItems.get(1).getImageResId());
        ((TextView) findViewById(R.id.textView_ulcer_food2_name)).setText(ulcerFoodItems.get(1).getName());
        ((TextView) findViewById(R.id.textView_ulcer_food2_price)).setText(ulcerFoodItems.get(1).getPrice());

        // Food Item 3: Boiled Eggs
        ((ImageView) findViewById(R.id.imageView_ulcer_food3)).setImageResource(ulcerFoodItems.get(2).getImageResId());
        ((TextView) findViewById(R.id.textView_ulcer_food3_name)).setText(ulcerFoodItems.get(2).getName());
        ((TextView) findViewById(R.id.textView_ulcer_food3_price)).setText(ulcerFoodItems.get(2).getPrice());
    }

    /**
     * Sets up click listeners for buttons on the Ulcer menu page.
     */
    private void setupListeners() {
        Button btnBack = findViewById(R.id.button_ulcer_menu_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HealthStatusActivity
            }
        });

        // Setup "Place Order" buttons for each item
        findViewById(R.id.button_place_order_ulcer_food1).setOnClickListener(v -> navigateToOrderDetails(ulcerFoodItems.get(0)));
        findViewById(R.id.button_place_order_ulcer_food2).setOnClickListener(v -> navigateToOrderDetails(ulcerFoodItems.get(1)));
        findViewById(R.id.button_place_order_ulcer_food3).setOnClickListener(v -> navigateToOrderDetails(ulcerFoodItems.get(2)));
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(UlcerFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Pass the FoodItem object
        startActivity(intent);
    }
}
