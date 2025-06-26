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

public class HighSugarFoodMenuActivity extends AppCompatActivity {

    private List<FoodItem> highSugarFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_sugar_food_menu);

        initializeHighSugarFoodItems();
        displayHighSugarFoodItems();
        setupListeners();
    }

    /**
     * Initializes the list of food items specifically for managing High Sugar (diabetic-friendly).
     * Ensure you have the drawable images (img_broccoli_fry, img_beans_salad, img_roast_pistachios)
     * in your res/drawable folder.
     */
    private void initializeHighSugarFoodItems() {
        highSugarFoodItems = new ArrayList<>();
        highSugarFoodItems.add(new FoodItem(
                "Broccoli Fry",
                "Rs 950.00",
                R.drawable.img_broccoli_fry, // Replace with your actual drawable
                "A simple and healthy broccoli stir-fry, rich in fiber and vitamins, with very low sugar content."
        ));
        highSugarFoodItems.add(new FoodItem(
                "Beans Salad",
                "Rs 850.00",
                R.drawable.img_beans_salad, // Replace with your actual drawable
                "A refreshing salad made with various beans, providing complex carbohydrates and fiber, good for blood sugar control."
        ));
        highSugarFoodItems.add(new FoodItem(
                "Roast Pistachios",
                "Rs 1050.00",
                R.drawable.img_roast_pistachios, // Replace with your actual drawable
                "Roasted pistachios are a healthy snack rich in protein and good fats, with a low glycemic index."
        ));
        // Add more specific high sugar (diabetic-friendly) food items if needed
    }

    /**
     * Displays the initialized food items on the UI.
     */
    private void displayHighSugarFoodItems() {
        // Food Item 1: Broccoli Fry
        ((ImageView) findViewById(R.id.imageView_high_sugar_food1)).setImageResource(highSugarFoodItems.get(0).getImageResId());
        ((TextView) findViewById(R.id.textView_high_sugar_food1_name)).setText(highSugarFoodItems.get(0).getName());
        ((TextView) findViewById(R.id.textView_high_sugar_food1_price)).setText(highSugarFoodItems.get(0).getPrice());

        // Food Item 2: Beans Salad
        ((ImageView) findViewById(R.id.imageView_high_sugar_food2)).setImageResource(highSugarFoodItems.get(1).getImageResId());
        ((TextView) findViewById(R.id.textView_high_sugar_food2_name)).setText(highSugarFoodItems.get(1).getName());
        ((TextView) findViewById(R.id.textView_high_sugar_food2_price)).setText(highSugarFoodItems.get(1).getPrice());

        // Food Item 3: Roast Pistachios
        ((ImageView) findViewById(R.id.imageView_high_sugar_food3)).setImageResource(highSugarFoodItems.get(2).getImageResId());
        ((TextView) findViewById(R.id.textView_high_sugar_food3_name)).setText(highSugarFoodItems.get(2).getName());
        ((TextView) findViewById(R.id.textView_high_sugar_food3_price)).setText(highSugarFoodItems.get(2).getPrice());
    }

    /**
     * Sets up click listeners for buttons on the High Sugar menu page.
     */
    private void setupListeners() {
        Button btnBack = findViewById(R.id.button_high_sugar_menu_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HealthStatusActivity
            }
        });

        // Setup "Place Order" buttons for each item
        findViewById(R.id.button_place_order_high_sugar_food1).setOnClickListener(v -> navigateToOrderDetails(highSugarFoodItems.get(0)));
        findViewById(R.id.button_place_order_high_sugar_food2).setOnClickListener(v -> navigateToOrderDetails(highSugarFoodItems.get(1)));
        findViewById(R.id.button_place_order_high_sugar_food3).setOnClickListener(v -> navigateToOrderDetails(highSugarFoodItems.get(2)));
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(HighSugarFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Pass the FoodItem object
        startActivity(intent);
    }
}
