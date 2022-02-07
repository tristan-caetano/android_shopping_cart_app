package com.example.shopping_cart_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class cart_inventory extends AppCompatActivity {

    //  Declaring variables for shared prefs
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String[] CUSTOM_ITEMS = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth", "fifteenth"};
    public int[] customItems = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public String[] customItemsNames = {"","","","","","","","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_inventory);

        // Declaring index
        int i;

        // Declaring buttons
        Button addMoreButton = (Button) findViewById(R.id.addMoreButton);
        Button deleteAllButton = (Button) findViewById(R.id.deleteAllButton);
        Button deleteItemButton = (Button) findViewById(R.id.deleteItem);

        // Declaring edit text
        EditText delItemText = (EditText) findViewById(R.id.delText);

        // Declaring Shared Prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        // Saving shared prefs for custom items
        for(i = 0; i < 15; i ++){
            System.out.println(i);
            customItems[i] = sharedPrefs.getInt((CUSTOM_ITEMS[i] + "i"), 0);
            customItemsNames[i] = sharedPrefs.getString(CUSTOM_ITEMS[i], "");
        }

        // Initiation list view
        listView();

        // Button that brings you back to previous menu
        addMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(itemIntent);
            }
        });

        // Button that deletes all items in the cart
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(1, true);
            }
        });

        // Button that deletes a single item from the cart
        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(Integer.valueOf(delItemText.getText().toString()), false);
            }
        });

    }

    // Updates list view with appropriate cart info
    public void listView(){

        // Declaring Shared Prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        TextView listText = (TextView) findViewById(R.id.listText);

        // Declaring indicies
        int j, z = 1;

        // Declaring string containing cart list
        String list = "";

        // Saving names of all items in cart to list variable
        for(j = 0; j < 15; j ++){
            if(!(customItemsNames[j].equals(""))) {
                list += (z + ". " + customItemsNames[j] + "\n");
                z++;
            }
        }

        // Updating the list
        listText.setText(list);
    }

    // Removes all items or a single specified item from the cart
    public void removeItem(int index, boolean allOrOne){

        // Declaring Shared Prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        // Declaring index
        int i;

        // Editing index for array rules
        index = index - 1;

        // If statement that makes sure the value from the user is less than 16 and more than -1
        if(index < 15 && index > -1) {

            // If statement that chooses whether the whole list is deleted (true) or only a single item (false)
            if (allOrOne) {
                for (i = 0; i < 15; i++) {
                    customItems[i] = 0;
                    customItemsNames[i] = "";
                    editor.putInt((CUSTOM_ITEMS[i] + "i"), customItems[i]);
                    editor.putString(CUSTOM_ITEMS[i], customItemsNames[i]);
                    editor.commit();
                }
            } else {
                customItems[index] = 0;
                customItemsNames[index] = "";
                editor.putInt((CUSTOM_ITEMS[index] + "i"), customItems[index]);
                editor.putString(CUSTOM_ITEMS[index], customItemsNames[index]);
                editor.commit();
            }

        // Notifying the user that the value chosen is too high or low
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "That index is too high.", Toast.LENGTH_SHORT);
        }

        // Fixing the list so there are no gaps
        resetList();

        // Reseting the list
        listView();
    }

    // Fixes the list so there arent gaps between items
    public void resetList(){

        // Declaring Shared Prefs
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        // Declaring temp variables
        int[] customItemsNew = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        String[] customItemsNamesNew = {"","","","","","","","","","","","","","",""};

        // Declaring indicies
        int i, z = 0;

        // Saves only written values to temp variables
        for (i = 0; i < 15; i++) {
            if(customItems[i] == 1){
                customItemsNew[z] = 1;
                customItemsNamesNew[z] = customItemsNames[i];
                z++;
            }
        }

        // Making actual values equal to temps
        customItems = customItemsNew;
        customItemsNames = customItemsNamesNew;

        // Saving the values
        for (i = 0; i < 15; i++) {
            editor.putInt((CUSTOM_ITEMS[i] + "i"), customItems[i]);
            editor.putString(CUSTOM_ITEMS[i], customItemsNames[i]);
            editor.commit();
        }
    }
}