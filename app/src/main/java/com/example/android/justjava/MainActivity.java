package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean whippedcream = false, Chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText fieldname = (EditText) findViewById(R.id.name_edit_text);
        String name = fieldname.getText().toString();
        String s = getString(R.string.CofeeOrderFor);
        name = name.toUpperCase();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, s + name);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(quantity, name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    public void whippedCream(View view) {
        CheckBox haswhippedcream = (CheckBox) findViewById(R.id.whippedcreamcheckbox);
        whippedcream = haswhippedcream.isChecked();
    }

    public void chocolate(View view) {
        CheckBox haschocolate = (CheckBox) findViewById(R.id.chocolatecheckbox);
        Chocolate = haschocolate.isChecked();
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = quantity * 5;
        if (whippedcream) {
            price = price + quantity;
        }
        if (Chocolate) {
            price = price + 2 * quantity;
        }
        return price;
    }

    private String createOrderSummary(int p, String n) {

        return (getString(R.string.Name) + n + getString(R.string.addwhippedcream) + whippedcream + "\n" +
                getString(R.string.addchocolate) + Chocolate + getString(R.string.Quantity) + p + getString(R.string.Totalwithcurrency) + calculatePrice() + getString(R.string.thankyou));
    }

    public void increment(View view) {

        if (quantity == 20) {
            Toast.makeText(this, R.string.maximumlimit, Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);


    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, R.string.nocoffeeordered, Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


}