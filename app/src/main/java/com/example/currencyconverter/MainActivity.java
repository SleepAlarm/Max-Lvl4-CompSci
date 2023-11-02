package com.example.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText;
    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(
                this, R.array.currencies, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromCurrencySpinner.setAdapter(currencyAdapter);
        toCurrencySpinner.setAdapter(currencyAdapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = Double.parseDouble(amountEditText.getText().toString());
                String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
                String toCurrency = toCurrencySpinner.getSelectedItem().toString();
                double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

                resultTextView.setText("Result: " + convertedAmount);
            }
        });
    }
    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double cadToUsdRate = 0.75;
        double usdToEurRate = 0.85;
        double eurToCadRate = 1.4;

        if (fromCurrency.equals("CAD - Canadian Dollar") && toCurrency.equals("USD - United States Dollar")) {
            return amount * cadToUsdRate;
        } else if (fromCurrency.equals("USD - United States Dollar") && toCurrency.equals("CAD - Canadian Dollar")) {
            return amount / cadToUsdRate;
        } else if (fromCurrency.equals("USD - United States Dollar") && toCurrency.equals("EUR - Euro")) {
            return amount * usdToEurRate;
        } else if (fromCurrency.equals("EUR - Euro") && toCurrency.equals("USD - United States Dollar")) {
            return amount / usdToEurRate;
        } else if (fromCurrency.equals("EUR - Euro") && toCurrency.equals("CAD - Canadian Dollar")) {
            return amount * eurToCadRate;
        } else if (fromCurrency.equals("CAD - Canadian Dollar") && toCurrency.equals("EUR - Euro")) {
            return amount / eurToCadRate;
        }
        return amount; // Return the same amount if currencies are the same
    }
}
