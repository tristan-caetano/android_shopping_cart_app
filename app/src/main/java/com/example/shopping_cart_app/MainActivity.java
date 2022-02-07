package com.example.shopping_cart_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs", MILK = "milk", CEREAL = "cereal", CHEESE = "cheese", BREAD = "bread", BANANA = "banana";
    public static final String[] CUSTOM_ITEMS = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth", "fifteenth"};
    SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPrefs.edit();
    public int milk, cereal, cheese, bread, banana, i;
    public int[] customItems = {};
    public String[] customItemsNames = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Saving shared prefs for common items
        milk = sharedPrefs.getInt(MILK, 0);
        cereal = sharedPrefs.getInt(CEREAL, 0);
        cheese = sharedPrefs.getInt(CHEESE, 0);
        bread = sharedPrefs.getInt(BREAD, 0);
        banana = sharedPrefs.getInt(BANANA, 0);

        // Saving shared prefs for custom items
        for(i = 0; i < 15; i ++){
            customItems[i] = sharedPrefs.getInt(CUSTOM_ITEMS[i], 0);
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

        milkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSumOfCart() < 16){
                    milk ++;
                    editor.putInt(MILK, milk);
                }
            }
        });

        cerealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSumOfCart() < 16){
                    cereal ++;
                    editor.putInt(CEREAL, cereal);
                }
            }
        });

        cheeseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSumOfCart() < 16){
                    cheese ++;
                    editor.putInt(CHEESE, cheese);
                }
            }
        });

        breadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSumOfCart() < 16){
                    bread ++;
                    editor.putInt(BREAD, bread);
                }
            }
        });

        bananaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getSumOfCart() < 16){
                    banana ++;
                    editor.putInt(BANANA, banana);
                }
            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
                if(getSumOfCart() < 16) {
                    for (i = 0; i < 15; i++) {
                        customItems[i] = sharedPrefs.getInt(CUSTOM_ITEMS[i], 0);
                        customItemsNames[i] = sharedPrefs.getString(CUSTOM_ITEMS[i], "");
                    }
                }
            }
        });

        checkInvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    // Getting the sum of all of the items in the cart
    public int getSumOfCart(){
        int sum = milk + cereal + cheese + bread + banana;

        for(i = 0; i < 15; i ++){
            sum += customItems[i];
        }

        return sum;
    }
}