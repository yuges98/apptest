package com.s23010743.tatyrootsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout; // Import LinearLayout
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView; // Import CardView
import androidx.core.content.ContextCompat; // For getting colors programmatically

public class HealthTipsActivity extends AppCompatActivity {

    // UI Elements
    private TextView textViewHealthTipsTitle;
    private LinearLayout linearLayoutTipsContainer; // Container for dynamic tip cards
    private Button buttonRefreshTips;
    private Button buttonBack;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        // Initialize UI elements
        initViews();

        // Set up listeners for buttons
        setupListeners();

        // Generate tips automatically when the page loads
        generateAndDisplayTips();
    }

    /**
     * Initializes all UI elements by finding their respective IDs in the layout.
     */
    private void initViews() {
        textViewHealthTipsTitle = findViewById(R.id.textView_health_tips_title);
        linearLayoutTipsContainer = findViewById(R.id.linearLayout_tips_container);
        buttonRefreshTips = findViewById(R.id.button_refresh_tips);
        buttonBack = findViewById(R.id.button_back);
        progressBarLoading = findViewById(R.id.progressBar_loading_tips);
    }

    /**
     * Sets up click listeners for interactive UI elements.
     */
    private void setupListeners() {
        // Listener for the "Refresh Tips" button
        buttonRefreshTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateAndDisplayTips();
            }
        });

        // Listener for the "Back" button
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity (HomePage)
            }
        });
    }

    /**
     * Simulates fetching health tips from a generative AI model (e.g., Gemini API).
     * Displays a loading indicator and then updates the LinearLayout with generated tip cards.
     */
    private void generateAndDisplayTips() {
        // Clear existing tips
        linearLayoutTipsContainer.removeAllViews();

        // Show loading indicator and disable buttons
        progressBarLoading.setVisibility(View.VISIBLE);
        buttonRefreshTips.setEnabled(false);
        buttonBack.setEnabled(false);

        // Simulate API call delay (replace with actual network request in a real app)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Define individual health tips as plain strings
                String[] healthTips = {
                        "<b>Stay Hydrated:</b> Drink plenty of water throughout the day. It aids digestion, nutrient absorption, and skin health.",
                        "<b>Eat Balanced Meals:</b> Include a variety of fruits, vegetables, lean proteins, and whole grains. Prioritize fresh, unprocessed foods.",
                        "<b>Regular Physical Activity:</b> Aim for at least 30 minutes of moderate exercise most days of the week. This boosts mood, energy, and overall health.",
                        "<b>Prioritize Sleep:</b> Get 7-9 hours of quality sleep each night. Good sleep is crucial for physical and mental recovery.",
                        "<b>Manage Stress:</b> Practice mindfulness, meditation, yoga, or hobbies to reduce stress. Chronic stress negatively impacts health.",
                        "<b>Limit Processed Foods:</b> Reduce intake of sugary drinks, fast food, and highly processed snacks. They offer little nutritional value and can lead to health issues.",
                        "<b>Practice Portion Control:</b> Be mindful of serving sizes to avoid overeating, even with healthy foods.",
                        "<b>Regular Check-ups:</b> Visit your doctor regularly for preventive care and screenings, even if you feel healthy."
                };

                // Add each tip as a CardView
                for (String tip : healthTips) {
                    addHealthTipCard(tip);
                }

                // Hide loading indicator and enable buttons
                progressBarLoading.setVisibility(View.GONE);
                buttonRefreshTips.setEnabled(true);
                buttonBack.setEnabled(true);

                Toast.makeText(HealthTipsActivity.this, "Health tips generated!", Toast.LENGTH_SHORT).show();
            }
        }, 2500); // Simulate 2.5-second delay
    }

    /**
     * Creates and adds a CardView containing a single health tip to the container.
     * @param tipContentHtml The health tip text, possibly with basic HTML tags (like <b>).
     */
    private void addHealthTipCard(String tipContentHtml) {
        // Create a new CardView
        CardView cardView = new CardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.card_margin_bottom)); // Add bottom margin
        cardView.setLayoutParams(cardParams);
        cardView.setRadius(getResources().getDimension(R.dimen.card_corner_radius)); // Rounded corners
        cardView.setCardElevation(getResources().getDimension(R.dimen.card_elevation)); // Add a subtle shadow
        cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.tasty_roots_card_background)); // Set card background color

        // Create a LinearLayout inside the CardView for padding and content
        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        innerLayout.setOrientation(LinearLayout.VERTICAL);
        innerLayout.setPadding(
                (int) getResources().getDimension(R.dimen.card_padding_horizontal),
                (int) getResources().getDimension(R.dimen.card_padding_vertical),
                (int) getResources().getDimension(R.dimen.card_padding_horizontal),
                (int) getResources().getDimension(R.dimen.card_padding_vertical)
        );

        // Create a TextView for the tip content
        TextView tipTextView = new TextView(this);
        tipTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        tipTextView.setTextColor(ContextCompat.getColor(this, R.color.text_color_card_content)); // Set text color
        tipTextView.setTextSize(getResources().getDimension(R.dimen.tip_text_size)); // Set text size
        tipTextView.setLineSpacing(getResources().getDimension(R.dimen.tip_line_spacing_extra), 1.0f); // Adjust line spacing

        // Convert HTML string to Spanned text for TextView
        Spanned formattedTips;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formattedTips = Html.fromHtml(tipContentHtml, Html.FROM_HTML_MODE_COMPACT);
        } else {
            formattedTips = Html.fromHtml(tipContentHtml);
        }
        tipTextView.setText(formattedTips);

        // Add TextView to innerLayout, and innerLayout to CardView
        innerLayout.addView(tipTextView);
        cardView.addView(innerLayout);

        // Add CardView to the main container
        linearLayoutTipsContainer.addView(cardView);
    }
}
