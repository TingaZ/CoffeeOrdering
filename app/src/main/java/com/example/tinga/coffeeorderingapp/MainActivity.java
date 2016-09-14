package com.example.tinga.coffeeorderingapp;
import android.content.Context;

import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;

import android.util.Log;

import android.view.View;

import android.widget.CheckBox;

import android.widget.EditText;

import android.widget.TextView;

import android.widget.Toast;




import java.text.NumberFormat;




/**

 * This app displays an order form to order coffee.

 */

public class MainActivity extends ActionBarActivity {

    int quantity = 1;







    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }




    /**

     * This method is called when the order button is clicked.

     */

    public void submitOrder(View view) {

        EditText nameEditText =(EditText) findViewById(R.id.name_edit_text);

        String name = nameEditText.getText().toString();




        CheckBox whippedcreamCheckBox =(CheckBox) findViewById(R.id.whipped_cream_checkbox);

        boolean hasWhippedCream = whippedcreamCheckBox.isChecked();

        Log.v("Main Activity", "Has Whipped Cream: "+hasWhippedCream);

        CheckBox chocolateCheckBox =(CheckBox) findViewById(R.id.chocolate_checkbox);

        boolean hasChocolateTopping = chocolateCheckBox.isChecked();

        Log.v("Main Activity", "Has Chocolate Cream: "+ hasChocolateTopping);




        /*

        * getting calculatingPrice method for price calculations

        *

        * Gettting createOrderSummary method to display parameters for price, hasWhippedCream.

        * */

        int price = calculatePrice(hasWhippedCream, hasChocolateTopping);

        String display = createOrderSummary(price, hasWhippedCream, hasChocolateTopping);




        //Sending an Intent to an email app to handle our coffee orders

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "brndkt@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for: " +name);
        intent.putExtra(Intent.EXTRA_TEXT, display);

        if(intent.resolveActivity(getPackageManager()) != null){

            startActivity(intent);

        }







        displayMessage(display);







        /////////////////////////// Abolished ////////////////////////////




       /* int price = calculatePrice();

        String priceMessage= "Total =$" + price;

        priceMessage = priceMessage + "\nThank you!";

        displayMessage(priceMessage);*/




        ////////////////////////// Abolished /////////////////////////////

    }




    /**

     * This method is called when the Add button is clicked.

     */

    public void increment(View add){

        if(quantity == 100){

            Toast.makeText(this,"You cannot order more than 100 coffees.", Toast.LENGTH_SHORT);

            return;

        }




        quantity = quantity + 1;

        displayQuantity(quantity);




    }




    /**

     * This method is called when the Minus button is clicked.

     */

    public void decrement(View sub){

        if(quantity == 1) {

            Toast.makeText(this, "You cannot order less than 100 coffees.", Toast.LENGTH_SHORT);

            return;

        }

        quantity = quantity - 1;

        displayQuantity(quantity);




    }




    /**

     * This method displays the given quantity value on the screen.

     */

    private void displayQuantity(int number) {

        TextView quantityTextView = (TextView) findViewById(

                R.id.quantity_text_view);

        quantityTextView.setText("" + number);

    }







    /**

     * This method displays the String Value on the screen.

     */

    public void displayMessage(String message){

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);

        orderSummaryTextView.setText(message);




    }




    /**

     * calculates the price of the order.

     * return total price

     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolateTopping){

        int price = 5;




        if(addWhippedCream){

            price = price + 1;

        }




        if(addChocolateTopping){

            price = price + 2;

        }




        return quantity * price;

    }




    /*

    * This method displays the summary of the order

    *

    * */

    private String createOrderSummary(int price, boolean hasWhippedCream,boolean hasChocolateTopping){

        EditText nameEditText =(EditText) findViewById(R.id.name_edit_text);

        String name = nameEditText.getText().toString();




        return "Name: "+ name +"\nAdd Whipped Cream?: "+ hasWhippedCream+"\nAdd Chocolate?: "+hasChocolateTopping+" \nQuantity: "+ quantity +" \nTotal: $"+ price +"\nThank You!";

    }




}