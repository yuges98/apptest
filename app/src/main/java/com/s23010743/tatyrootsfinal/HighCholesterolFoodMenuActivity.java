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

public class HighCholesterolFoodMenuActivity extends AppCompatActivity {

    private List<FoodItem> highCholesterolFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_cholesterol_food_menu);

        initializeHighCholesterolFoodItems();
        displayHighCholesterolFoodItems();
        setupListeners();
    }

    /**
     * Initializes the list of food items specifically for High Cholesterol.
     * Ensure you have the drawable images (img_brown_rice_curry, img_rice_salmon_curry, img_whole_wheat_pasta)
     * in your res/drawable folder.
     */
    private void initializeHighCholesterolFoodItems() {
        highCholesterolFoodItems = new ArrayList<>();
        highCholesterolFoodItems.add(new FoodItem(
                "Brown Rice & Curry",
                "Rs 550.00",
                R.drawable.img_brown_rice_curry, // Replace with your actual drawable
                "A wholesome meal with brown rice and a flavorful vegetable curry, promoting good digestion and low cholesterol."
        ));
        highCholesterolFoodItems.add(new FoodItem(
                "Rice & Salmon Curry",
                "Rs 850.00",
                R.drawable.img_rice_salmon_curry, // Replace with your actual drawable
                "A hearty meal featuring salmon, rich in Omega-3 fatty acids, served with rice for a balanced diet."
        ));
        highCholesterolFoodItems.add(new FoodItem(
                "Whole Wheat Pasta",
                "Rs 550.00",
                R.drawable.img_whole_wheat_pasta, // Replace with your actual drawable
                "A healthy alternative to regular pasta, whole wheat pasta is rich in fiber and beneficial for cholesterol management."
        ));
        // Add more specific high cholesterol food items if needed
    }

    /**
     * Displays the initialized food items on the UI.
     */
    private void displayHighCholesterolFoodItems() {
        // Food Item 1: Brown Rice & Curry
        ((ImageView) findViewById(R.id.imageView_high_cholesterol_food1)).setImageResource(highCholesterolFoodItems.get(0).getImageResId());
        ((TextView) findViewById(R.id.textView_high_cholesterol_food1_name)).setText(highCholesterolFoodItems.get(0).getName());
        ((TextView) findViewById(R.id.textView_high_cholesterol_food1_price)).setText(highCholesterolFoodItems.get(0).getPrice());

        // Food Item 2: Rice & Salmon Curry
        ((ImageView) findViewById(R.id.imageView_high_cholesterol_food2)).setImageResource(highCholesterolFoodItems.get(1).getImageResId());
        ((TextView) findViewById(R.id.textView_high_cholesterol_food2_name)).setText(highCholesterolFoodItems.get(1).getName());
        ((TextView) findViewById(R.id.textView_high_cholesterol_food2_price)).setText(highCholesterolFoodItems.get(1).getPrice());

        // Food Item 3: Whole Wheat Pasta
        ((ImageView) findViewById(R.id.imageView_high_cholesterol_food3)).setImageResource(highCholesterolFoodItems.get(2).getImageResId());
        ((TextView) findViewById(R.id.textView_high_cholesterol_food3_name)).setText(highCholesterolFoodItems.get(2).getName());
        ((TextView) findViewById(R.id.textView_high_cholesterol_food3_price)).setText(highCholesterolFoodItems.get(2).getPrice());
    }

    /**
     * Sets up click listeners for buttons on the High Cholesterol menu page.
     */
    private void setupListeners() {
        Button btnBack = findViewById(R.id.button_high_cholesterol_menu_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HealthStatusActivity
            }
        });

        // Setup "Place Order" buttons for each item
        findViewById(R.id.button_place_order_high_cholesterol_food1).setOnClickListener(v -> navigateToOrderDetails(highCholesterolFoodItems.get(0)));
        findViewById(R.id.button_place_order_high_cholesterol_food2).setOnClickListener(v -> navigateToOrderDetails(highCholesterolFoodItems.get(1)));
        findViewById(R.id.button_place_order_high_cholesterol_food3).setOnClickListener(v -> navigateToOrderDetails(highCholesterolFoodItems.get(2)));
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(HighCholesterolFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Pass the FoodItem object
        startActivity(intent);
    }
}
