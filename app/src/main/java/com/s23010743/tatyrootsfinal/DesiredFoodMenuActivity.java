package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DesiredFoodMenuActivity extends AppCompatActivity {

    // List to hold our food items
    private List<FoodItem> foodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desired_food_menu);

        // Initialize food items
        initializeFoodItems();

        // Set up click listeners for the buttons
        setupListeners();
    }

    /**
     * Initializes the list of FoodItem objects with their details.
     * Descriptions are made more comprehensive for the detail page.
     */
    private void initializeFoodItems() {
        foodItems = new ArrayList<>();
        foodItems.add(new FoodItem(
                "Chicken Kottu",
                "Rs 950.00",
                R.drawable.img_chicken_kottu, // Make sure you have this drawable
                "Kottu is a popular Sri Lankan street food dish made from finely chopped roti (Godhamba roti) mixed with sautéed vegetables, scrambled egg, and tender pieces of chicken. It's prepared on a heated iron griddle, creating distinctive sounds as it's chopped. Seasoned with a blend of aromatic spices, it's a delicious, hearty, and satisfying meal, often served with a curry sauce."
        ));
        foodItems.add(new FoodItem(
                "Chicken Fried Rice",
                "Rs 850.00",
                R.drawable.img_chicken_fried_rice, // Make sure you have this drawable
                "A beloved staple, Chicken Fried Rice features fluffy rice stir-fried with succulent chicken chunks, mixed vegetables (typically carrots, green beans, and peas), and scrambled egg, seasoned with soy sauce and other Asian flavors. A satisfying and complete meal."
        ));
        foodItems.add(new FoodItem(
                "Chicken Noodles",
                "Rs 1050.00",
                R.drawable.img_chicken_noodles, // Make sure you have this drawable
                "Stir-fried noodles combined with tender chicken pieces and a vibrant assortment of fresh vegetables like bell peppers, onions, and cabbage. Tossed in a rich, savory sauce, this dish offers a delightful texture and a full-bodied flavor, making it a popular choice for a quick yet satisfying meal."
        ));
        // Add more food items if needed
    }

    /**
     * Sets up click listeners for interactive UI elements on the menu page.
     */
    private void setupListeners() {
        // Find the "Place Order" buttons for each food item and set listeners
        Button btnPlaceOrderKottu = findViewById(R.id.button_place_order_kottu);
        Button btnPlaceOrderFriedRice = findViewById(R.id.button_place_order_fried_rice);
        Button btnPlaceOrderNoodles = findViewById(R.id.button_place_order_noodles);

        // Find "More Info" TextViews
        TextView tvMoreInfoKottu = findViewById(R.id.textView_more_info_kottu);
        TextView tvMoreInfoFriedRice = findViewById(R.id.textView_more_info_fried_rice);
        TextView tvMoreInfoNoodles = findViewById(R.id.textView_more_info_noodles);

        // Listener for Chicken Kottu "Place Order" (on menu page)
        btnPlaceOrderKottu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOrderDetails(foodItems.get(0)); // Pass the first item (Kottu)
            }
        });

        // Listener for Chicken Fried Rice "Place Order" (on menu page)
        btnPlaceOrderFriedRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOrderDetails(foodItems.get(1)); // Pass the second item (Fried Rice)
            }
        });

        // Listener for Chicken Noodles "Place Order" (on menu page)
        btnPlaceOrderNoodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOrderDetails(foodItems.get(2)); // Pass the third item (Noodles)
            }
        });

        // Listener for Chicken Kottu "More Info" (navigates to detail page)
        tvMoreInfoKottu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFoodDetail(foodItems.get(0)); // Pass the first item (Kottu)
            }
        });

        // Listener for Chicken Fried Rice "More Info" (navigates to detail page)
        tvMoreInfoFriedRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFoodDetail(foodItems.get(1)); // Pass the second item (Fried Rice)
            }
        });

        // Listener for Chicken Noodles "More Info" (navigates to detail page)
        tvMoreInfoNoodles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFoodDetail(foodItems.get(2)); // Pass the third item (Noodles)
            }
        });

        // Set up listener for the "Back" button
        Button btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will simply close the current activity and go back to the previous one on the stack
            }
        });
    }

    /**
     * Navigates to the FoodDetailActivity, passing the selected FoodItem.
     * @param item The FoodItem object to display details for.
     */
    private void navigateToFoodDetail(FoodItem item) {
        Intent intent = new Intent(DesiredFoodMenuActivity.this, FoodDetailActivity.class);
        intent.putExtra("food_item", item); // Pass the FoodItem object
        startActivity(intent);
    }

    /**
     * Navigates to the OrderDetailsActivity, passing the selected FoodItem.
     * @param item The FoodItem object to order.
     */
    private void navigateToOrderDetails(FoodItem item) {
        Intent intent = new Intent(DesiredFoodMenuActivity.this, OrderDetailsActivity.class);
        intent.putExtra("food_item_for_order", item); // Use a distinct key for clarity
        startActivity(intent);
    }
}
