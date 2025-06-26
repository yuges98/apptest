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

public class LowCholesterolFoodMenuActivity extends AppCompatActivity {

    private List<FoodItem> lowCholesterolFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_cholesterol_food_menu);

        initializeLowCholesterolFoodItems();
        displayLowCholesterolFoodItems();
        setupListeners();
    }

    /**
     * Initializes the list of food items specifically for Low Cholesterol.
     * Ensure you have the drawable images (img_pears_juice, img_apple_juice, img_fish_fry)
     * in your res/drawable folder.
     */
    private void initializeLowCholesterolFoodItems() {
        lowCholesterolFoodItems = new ArrayList<>();
        lowCholesterolFoodItems.add(new FoodItem(
                "Pears Juice",
                "Rs 950.00",
                R.drawable.img_pears_juice, // Replace with your actual drawable
                "Freshly squeezed pear juice, a good source of fiber and antioxidants, which can contribute to healthy cholesterol levels."
        ));
        lowCholesterolFoodItems.add(new FoodItem(
                "Apple Juice",
                "Rs 500.00",
                R.drawable.img_apple_juice, // Replace with your actual drawable
                "Refreshing apple juice, known for its soluble fiber (pectin) that may help lower cholesterol."
        ));
        lowCholesterolFoodItems.add(new FoodItem(
                "Fish Fry",
                "Rs 950.00",
                R.drawable.img_fish_fry, // Replace with your actual drawable
                "Lightly pan-fried fish, rich in Omega-3 fatty acids that are beneficial for managing cholesterol."
        ));
        // Add more specific low cholesterol food items if needed
    }

    /**
     * Displays the initialized food items on the UI.
     */
    private void displayLowCholesterolFoodItems() {
        // Food Item 1: Pears Juice
        ((ImageView) findViewById(R.id.imageView_low_cholesterol_food1)).setImageResource(lowCholesterolFoodItems.get(0).getImageResId());
        ((TextView) findViewById(R.id.textView_low_cholesterol_food1_name)).setText(lowCholesterolFoodItems.get(0).getName());
        ((TextView) findViewById(R.id.textView_low_cholesterol_food1_price)).setText(lowCholesterolFoodItems.get(0).getPrice());

        // Food Item 2: Apple Juice
        ((ImageView) findViewById(R.id.imageView_low_cholesterol_food2)).setImageResource(lowCholesterolFoodItems.get(1).getImageResId());
        ((TextView) findViewById(R.id.textView_low_cholesterol_food2_name)).setText(lowCholesterolFoodItems.get(1).getName());
        ((TextView) findViewById(R.id.textView_low_cholesterol_food2_price)).setText(lowCholesterolFoodItems.get(1).getPrice());

        // Food Item 3: Fish Fry
        ((ImageView) findViewById(R.id.imageView_low_cholesterol_food3)).setImageResource(lowCholesterolFoodItems.get(2).getImageResId());
        ((TextView) findViewById(R.id.textView_low_cholesterol_food3_name)).setText(lowCholesterolFoodItems.get(2).getName());
        ((TextView) findViewById(R.id.textView_low_cholesterol_food3_price)).setText(lowCholesterolFoodItems.get(2).getPrice());
    }

    /**
     * Sets up click listeners for buttons on the Low Cholesterol menu page.
     */
    private void setupListeners() {
        Button btnBack = findViewById(R.id.button_low_cholesterol_menu_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HealthStatusActivity
            }
        });

        // Setup "Place Order" buttons for each item
        findViewById(R.id.button_place_order_low_cholesterol_food1).setOnClickListener(v -> navigateToOrderDetails(lowCholesterolFoodItems.get(0)));
        findViewById(R.id.button_place_order_low_cholesterol_food2).setOnClickListener(v -> navigateToOrderDetails(lowCholesterolFoodItems.get(1)));
        findViewById(R.id.button_place_order_low_cholesterol_food3).setOnClickListener(v -> navigateToOrderDetails(lowCholesterolFoodItems.get(2)));
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(LowCholesterolFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Pass the FoodItem object
        startActivity(intent);
    }
}
