package com.example.mortgagecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    // Declare UI elements
    private EditText mortgageAmountEditText;
    private EditText interestRateEditText;
    private EditText loanTenureEditText;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        mortgageAmountEditText = findViewById(R.id.mortgageAmountEditText);
        interestRateEditText = findViewById(R.id.interestRateEditText);
        loanTenureEditText = findViewById(R.id.loanTenureEditText);
        calculateButton = findViewById(R.id.calculateButton);

        // Inside onCreate method, after initializing UI elements
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEMI();
            }
        });

    }
    private void calculateEMI() {
        // Get input values as strings
        String principalStr = mortgageAmountEditText.getText().toString();
        String interestRateStr = interestRateEditText.getText().toString();
        String tenureStr = loanTenureEditText.getText().toString();

        // Validate inputs
        if (principalStr.isEmpty() || interestRateStr.isEmpty() || tenureStr.isEmpty()) {
            // In calculateEMI method, replace the Toast with specific error messages
            if (principalStr.isEmpty()) {
                mortgageAmountEditText.setError("Please enter the mortgage amount");
                return;
            }

            if (interestRateStr.isEmpty()) {
                interestRateEditText.setError("Please enter the interest rate");
                return;
            }

            if (tenureStr.isEmpty()) {
                loanTenureEditText.setError("Please enter the loan tenure");
                return;
            }
        }


            // Convert strings to numeric values
        double principal = Double.parseDouble(principalStr);
        double annualInterestRate = Double.parseDouble(interestRateStr);
        int tenureYears = Integer.parseInt(tenureStr);
        // Continue inside calculateEMI method

// Calculate monthly interest rate
        double monthlyInterestRate = (annualInterestRate / 12) / 100;

// Calculate number of payments (months)
        int numberOfPayments = tenureYears * 12;

// Calculate EMI
        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
        // Format the result
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        String emiResult = ": " + currencyFormat.format(emi);
        // Create an Intent to start ResultActivity
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("EMI_RESULT", emiResult);
        startActivity(intent);
// Display the result
        resultTextView.setText(emiResult);


    }



}

