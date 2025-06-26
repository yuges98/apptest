package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler; // For simulating API call delay
import android.util.Log; // For logging
import android.widget.ProgressBar; // Import ProgressBar

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailsActivity extends AppCompatActivity {

    // UI Elements
    private TextView foodNameTextView;
    private ImageView foodImageView;
    private TextView foodPriceTextView;
    private EditText editTextName;
    private EditText editTextAddress;
    private Button buttonAddLocation;
    private RadioGroup radioGroupPayment;
    private RadioButton radioCashOnDelivery;
    private RadioButton radioCardPayment;
    private Button buttonConfirmOrder;
    private Button buttonBack;
    private ProgressBar progressBarLocation; // Correctly declared as ProgressBar

    // The API Key provided by the user (for Geocoding API simulation)
    private static final String GOOGLE_GEOCODING_API_KEY = "AIzaSyB7y1TUXcgWPumac6R2QxgfX9u3l2IffWM";

    // Store selected FoodItem to pass to subsequent activities
    private FoodItem currentFoodItem;
    private String customerName; // Declared here to be accessible for CardPaymentActivity
    private String customerAddress; // Declared here to be accessible for CardPaymentActivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Initialize UI elements
        initViews();

        // Get FoodItem data from the Intent
        currentFoodItem = (FoodItem) getIntent().getSerializableExtra("food_item_for_order");

        if (currentFoodItem != null) {
            displayFoodDetails(currentFoodItem);
        } else {
            Toast.makeText(this, "Order details not available.", Toast.LENGTH_SHORT).show();
            finish(); // Close activity if no data
        }

        // Set up listeners
        setupListeners(currentFoodItem); // Pass currentFoodItem
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        foodNameTextView = findViewById(R.id.textView_order_food_name);
        foodImageView = findViewById(R.id.imageView_order_food);
        foodPriceTextView = findViewById(R.id.textView_order_food_price);
        editTextName = findViewById(R.id.editText_customer_name);
        editTextAddress = findViewById(R.id.editText_customer_address);
        buttonAddLocation = findViewById(R.id.button_add_location);
        radioGroupPayment = findViewById(R.id.radioGroup_payment);
        radioCashOnDelivery = findViewById(R.id.radio_cash_on_delivery);
        radioCardPayment = findViewById(R.id.radio_card_payment);
        buttonConfirmOrder = findViewById(R.id.button_confirm_order);
        buttonBack = findViewById(R.id.button_back_order_details);
        progressBarLocation = (ProgressBar) findViewById(R.id.progressBar_location_fetch); // Explicit cast
    }

    /**
     * Displays the details of the selected food item on the Order Details page.
     * @param item The FoodItem object to display.
     */
    private void displayFoodDetails(FoodItem item) {
        foodNameTextView.setText(item.getName());
        foodImageView.setImageResource(item.getImageResId());
        foodPriceTextView.setText(item.getPrice());
    }

    /**
     * Sets up click listeners for buttons and radio group.
     * @param foodItem The FoodItem object to be ordered.
     */
    private void setupListeners(final FoodItem foodItem) {
        buttonAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLocationAndPopulateAddress(); // This method should not navigate or finish the activity
            }
        });

        buttonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerName = editTextName.getText().toString().trim(); // Capture values here
                customerAddress = editTextAddress.getText().toString().trim(); // Capture values here
                int selectedPaymentId = radioGroupPayment.getCheckedRadioButtonId();

                if (TextUtils.isEmpty(customerName) || TextUtils.isEmpty(customerAddress)) {
                    Toast.makeText(OrderDetailsActivity.this, "Please enter your name and address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedPaymentId == -1) {
                    Toast.makeText(OrderDetailsActivity.this, "Please select a payment method.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedPaymentId == R.id.radio_cash_on_delivery) {
                    String paymentMethod = "Cash on Delivery";
                    // For Cash on Delivery, directly go to confirmation
                    Intent intent = new Intent(OrderDetailsActivity.this, OrderConfirmationActivity.class);
                    intent.putExtra("order_summary_item", foodItem.getName());
                    intent.putExtra("order_summary_price", foodItem.getPrice());
                    intent.putExtra("order_summary_payment", paymentMethod);
                    startActivity(intent);
                    finish(); // Finish this activity so user can't go back to it using back button
                } else if (selectedPaymentId == R.id.radio_card_payment) {
                    // For Card Payment, go to the CardPaymentActivity
                    Intent intent = new Intent(OrderDetailsActivity.this, CardPaymentActivity.class);
                    // Pass essential order details to the card payment screen
                    intent.putExtra("food_item_for_card_payment", foodItem);
                    intent.putExtra("customer_name", customerName); // Pass collected customer name
                    intent.putExtra("customer_address", customerAddress); // Pass collected customer address
                    startActivity(intent);
                    // Do NOT finish OrderDetailsActivity here, in case the user goes back from CardPaymentActivity
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to DesiredFoodMenuActivity
            }
        });
    }

    /**
     * Simulates fetching location data using Google Geocoding API and populates the address field.
     */
    private void fetchLocationAndPopulateAddress() {
        // Show loading indicator and disable the button
        progressBarLocation.setVisibility(View.VISIBLE);
        buttonAddLocation.setEnabled(false);

        // Hardcoded coordinates for demonstration (e.g., a location in Colombo, Sri Lanka)
        double latitude = 6.9271;
        double longitude = 79.8612;

        // Construct the Google Geocoding API URL (for logging demonstration)
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                + latitude + "," + longitude + "&key=" + GOOGLE_GEOCODING_API_KEY;

        Log.d("LocationFetch", "Geocoding API URL: " + apiUrl);

        // Simulate network delay and response using a Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Simulate a successful API response and address extraction
                String fetchedAddress = "24, Galle Road, Colombo 03, Sri Lanka"; // Mock address

                editTextAddress.setText(fetchedAddress);
                Toast.makeText(OrderDetailsActivity.this, "Location added successfully!", Toast.LENGTH_SHORT).show();

                // Hide loading indicator and re-enable the button
                progressBarLocation.setVisibility(View.GONE);
                buttonAddLocation.setEnabled(true);

                // In a real scenario, you would perform an actual HTTP request here
                // using a library like OkHttp or Volley, parse the JSON response,
                // and then update the UI with the real fetched address.
            }
        }, 2000); // Simulate 2 seconds network delay
    }
}
