package com.example.shopping_cart_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //  Declaring variables for shared prefs
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String[] CUSTOM_ITEMS = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth", "fifteenth"};
    public int i;
    public int[] customItems = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public String[] customItemsNames = {"","","","","","","","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declaring Shared Prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        // Saving shared prefs for custom items
        for(i = 0; i < 15; i ++){
            System.out.println(i);
            customItems[i] = sharedPrefs.getInt((CUSTOM_ITEMS[i] + "i"), 0);
            customItemsNames[i] = sharedPrefs.getString(CUSTOM_ITEMS[i], "");
        }

        // Preset item add buttons
        Button milkButton = (Button) findViewById(R.id.milkButton);
        Button cerealButton = (Button) findViewById(R.id.cerealButton);
        Button cheeseButton = (Button) findViewById(R.id.cheeseButton);
        Button breadButton = (Button) findViewById(R.id.breadButton);
        Button bananaButton = (Button) findViewById(R.id.bananaButton);

        // Custom item add button
        Button customButton = (Button) findViewById(R.id.customItemButton);

        // Go to cart button
        Button checkInvButton = (Button) findViewById(R.id.checkInv);

        // Text field so that the user can add a custom item
        EditText addItem = (EditText) findViewById(R.id.customItem);

        // Adds a milk to the cart
        milkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart("Milk");
            }
        });

        // Adds a cereal to the cart
        cerealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart("Cereal");
            }
        });

        // Adds a cheese to the cart
        cheeseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart("Cheese");
            }
        });

        // Adds a bread to the cart
        breadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart("Bread");
            }
        });

        // Adds a banana to the cart
        bananaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart("Banana");
            }
        });

        // Adds the custom item to the cart
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(addItem.getText().toString());
            }
        });

        // Brings user to the menu to show and edit the list
        checkInvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent invIntent = new Intent(getApplicationContext(), cart_inventory.class);
                startActivity(invIntent);
            }
        });
    }

    // Getting the sum of all of the items in the cart
    public int getSumOfCart(){

        // Declaring sum variable
        int sum = 0;

        // Adding up all of the items in the cart
        for(i = 0; i < 15; i ++){
            sum += customItems[i];
        }

        // Returning the sum
        return sum;
    }

    // Adding items to cart
    public void addToCart(String item){

        // Index
        int j;

        // Initializing Toast
        Toast toast = null;

        // Shared prefs declaration
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        // If statement that checks if the cart is full before adding item
        if(getSumOfCart() < 16){

            // For loop that checks every item in the cart
            for(j = 0; j < 15; j ++){

                // If statement thats triggered if a space is found in the cart, and adds the item
                if(customItemsNames[j].equals("")){
                    customItemsNames[j] = item;
                    customItems[j] = 1;
                    editor.putInt((CUSTOM_ITEMS[j] + "i"), customItems[j]);
                    editor.putString(CUSTOM_ITEMS[j], customItemsNames[j]);
                    editor.commit();
                    toast = Toast.makeText(getApplicationContext(), "You added " + item + " to your cart.", Toast.LENGTH_SHORT);
                    toast.show();
                    j = 16;
                }
            }

        // Telling the user that their cart is full
        }else{
            toast = Toast.makeText(getApplicationContext(), "Your cart is full! Please remove some items before adding more.", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}