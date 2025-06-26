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

public class LowSugarFoodMenuActivity extends AppCompatActivity {

    private List<FoodItem> lowSugarFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_sugar_food_menu);

        initializeLowSugarFoodItems();
        displayLowSugarFoodItems();
        setupListeners();
    }

    /**
     * Initializes the list of food items specifically for Low Sugar.
     * Ensure you have the drawable images (img_quinoa_salad, img_chicken_breast, img_vegetable_stir_fry)
     * in your res/drawable folder.
     */
    private void initializeLowSugarFoodItems() {
        lowSugarFoodItems = new ArrayList<>();
        lowSugarFoodItems.add(new FoodItem(
                "Quinoa Salad",
                "Rs 800.00",
                R.drawable.img_quinoa_salad, // Replace with your actual drawable
                "A nutritious and low-carb salad with quinoa, fresh vegetables, and a light dressing, ideal for managing sugar levels."
        ));
        lowSugarFoodItems.add(new FoodItem(
                "Grilled Chicken Breast",
                "Rs 1200.00",
                R.drawable.img_chicken_breast, // Replace with your actual drawable
                "Lean grilled chicken breast, a great source of protein with minimal sugar content."
        ));
        lowSugarFoodItems.add(new FoodItem(
                "Vegetable Stir-fry",
                "Rs 750.00",
                R.drawable.img_vegetable_stir_fry, // Replace with your actual drawable
                "A vibrant mix of fresh, non-starchy vegetables stir-fried to perfection, low in sugar and high in nutrients."
        ));
        // Add more specific low sugar food items if needed
    }

    /**
     * Displays the initialized food items on the UI.
     */
    private void displayLowSugarFoodItems() {
        // Food Item 1: Quinoa Salad
        ((ImageView) findViewById(R.id.imageView_low_sugar_food1)).setImageResource(lowSugarFoodItems.get(0).getImageResId());
        ((TextView) findViewById(R.id.textView_low_sugar_food1_name)).setText(lowSugarFoodItems.get(0).getName());
        ((TextView) findViewById(R.id.textView_low_sugar_food1_price)).setText(lowSugarFoodItems.get(0).getPrice());

        // Food Item 2: Grilled Chicken Breast
        ((ImageView) findViewById(R.id.imageView_low_sugar_food2)).setImageResource(lowSugarFoodItems.get(1).getImageResId());
        ((TextView) findViewById(R.id.textView_low_sugar_food2_name)).setText(lowSugarFoodItems.get(1).getName());
        ((TextView) findViewById(R.id.textView_low_sugar_food2_price)).setText(lowSugarFoodItems.get(1).getPrice());

        // Food Item 3: Vegetable Stir-fry
        ((ImageView) findViewById(R.id.imageView_low_sugar_food3)).setImageResource(lowSugarFoodItems.get(2).getImageResId());
        ((TextView) findViewById(R.id.textView_low_sugar_food3_name)).setText(lowSugarFoodItems.get(2).getName());
        ((TextView) findViewById(R.id.textView_low_sugar_food3_price)).setText(lowSugarFoodItems.get(2).getPrice());
    }

    /**
     * Sets up click listeners for buttons on the Low Sugar menu page.
     */
    private void setupListeners() {
        Button btnBack = findViewById(R.id.button_low_sugar_menu_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HealthStatusActivity
            }
        });

        // Setup "Place Order" buttons for each item
        findViewById(R.id.button_place_order_low_sugar_food1).setOnClickListener(v -> navigateToOrderDetails(lowSugarFoodItems.get(0)));
        findViewById(R.id.button_place_order_low_sugar_food2).setOnClickListener(v -> navigateToOrderDetails(lowSugarFoodItems.get(1)));
        findViewById(R.id.button_place_order_low_sugar_food3).setOnClickListener(v -> navigateToOrderDetails(lowSugarFoodItems.get(2)));
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(LowSugarFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Pass the FoodItem object
        startActivity(intent);
    }
}
