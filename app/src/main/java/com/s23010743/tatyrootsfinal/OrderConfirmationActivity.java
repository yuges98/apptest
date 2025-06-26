package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmationActivity extends AppCompatActivity {

    private TextView confirmationMessageTextView;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        confirmationMessageTextView = findViewById(R.id.textView_confirmation_message);
        homeButton = findViewById(R.id.button_confirmation_home);

        // Display the confirmation message
        confirmationMessageTextView.setText(getString(R.string.order_prepared_message));

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the HomePage and clear the activity stack
                Intent intent = new Intent(OrderConfirmationActivity.this, HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Finish this activity
            }
        });
    }
}
