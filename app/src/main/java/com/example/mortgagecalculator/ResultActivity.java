package com.example.mortgagecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    private TextView emiResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize TextView
        emiResultTextView = findViewById(R.id.emiResultTextView);

        // Get the Intent and retrieve the data
        Intent intent = getIntent();
        String emiResult = intent.getStringExtra("EMI_RESULT");

        // Display the EMI result
        emiResultTextView.setText("Your Monthly Mortgage Payment is " + emiResult);
    }
}
