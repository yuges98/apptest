package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CardPaymentActivity extends AppCompatActivity {

    private ImageView foodImageView;
    private TextView foodNameTextView;
    private TextView foodPriceTextView;
    private EditText editTextCardNumber;
    private EditText editTextExpiry;
    private EditText editTextCvv;
    private EditText editTextCardHolderName;
    private Button buttonPayNow;
    private Button buttonBack;
    private ProgressBar progressBarPayment;

    private FoodItem foodItem; // To hold the food item details
    private String customerName; // To hold the customer's name
    private String customerAddress; // To hold the customer's address

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        initViews();

        // Retrieve data passed from OrderDetailsActivity
        Intent intent = getIntent();
        foodItem = (FoodItem) intent.getSerializableExtra("food_item_for_card_payment");
        customerName = intent.getStringExtra("customer_name");
        customerAddress = intent.getStringExtra("customer_address");

        if (foodItem != null) {
            displayFoodDetails();
        } else {
            Toast.makeText(this, "Payment details not available.", Toast.LENGTH_SHORT).show();
            finish();
        }

        setupListeners();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        foodImageView = findViewById(R.id.imageView_card_payment_food);
        foodNameTextView = findViewById(R.id.textView_card_payment_food_name);
        foodPriceTextView = findViewById(R.id.textView_card_payment_food_price);
        editTextCardNumber = findViewById(R.id.editText_card_number);
        editTextExpiry = findViewById(R.id.editText_expiry_date);
        editTextCvv = findViewById(R.id.editText_cvv);
        editTextCardHolderName = findViewById(R.id.editText_card_holder_name);
        buttonPayNow = findViewById(R.id.button_pay_now);
        buttonBack = findViewById(R.id.button_card_payment_back);
        progressBarPayment = findViewById(R.id.progressBar_card_payment);
    }

    /**
     * Displays the food item details on the card payment screen.
     */
    private void displayFoodDetails() {
        foodImageView.setImageResource(foodItem.getImageResId());
        foodNameTextView.setText(foodItem.getName());
        foodPriceTextView.setText(foodItem.getPrice());
    }

    /**
     * Sets up click listeners for the buttons.
     */
    private void setupListeners() {
        buttonPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processCardPayment();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to OrderDetailsActivity
            }
        });
    }

    /**
     * Simulates processing the card payment.
     * In a real app, this would involve actual payment gateway SDK calls.
     */
    private void processCardPayment() {
        String cardNumber = editTextCardNumber.getText().toString().trim();
        String expiryDate = editTextExpiry.getText().toString().trim();
        String cvv = editTextCvv.getText().toString().trim();
        String cardHolderName = editTextCardHolderName.getText().toString().trim();

        // Basic validation (for simulation purposes)
        if (TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(expiryDate) || TextUtils.isEmpty(cvv) || TextUtils.isEmpty(cardHolderName)) {
            Toast.makeText(this, "Please fill all card details.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show loading indicator
        progressBarPayment.setVisibility(View.VISIBLE);
        buttonPayNow.setEnabled(false); // Disable button during processing
        buttonBack.setEnabled(false);

        // Simulate network delay for payment processing
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Hide loading indicator
                progressBarPayment.setVisibility(View.GONE);
                buttonPayNow.setEnabled(true);
                buttonBack.setEnabled(true);

                // Simulate successful payment
                Toast.makeText(CardPaymentActivity.this, "Payment successful!", Toast.LENGTH_SHORT).show();

                // Navigate to Order Confirmation Page
                Intent intent = new Intent(CardPaymentActivity.this, OrderConfirmationActivity.class);
                intent.putExtra("order_summary_item", foodItem.getName());
                intent.putExtra("order_summary_price", foodItem.getPrice());
                intent.putExtra("order_summary_payment", "Card Payment");
                intent.putExtra("customer_name", customerName); // Pass back for confirmation
                intent.putExtra("customer_address", customerAddress); // Pass back for confirmation
                startActivity(intent);
                finish(); // Finish CardPaymentActivity
            }
        }, 2500); // Simulate 2.5 seconds payment processing
    }
}
