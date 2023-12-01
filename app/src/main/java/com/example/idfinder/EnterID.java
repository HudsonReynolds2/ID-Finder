package com.example.idfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EnterID extends AppCompatActivity {
    public ArrayList<String> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_id);

        EditText editText = findViewById(R.id.inputNumber);
        String initialText = "U";
        editText.setText(initialText); // sets a "U" as the first value in the text box on creation
    }
    public void addID (View v){
        TextView num = findViewById(R.id.inputNumber); // inputted ID is saved as a textView
        String ID = num.getText().toString(); // the input is converted to a string

        // remove U from line
        //cast to int
        // add number to database with same logic below
        //just change formatting for the server database

        if (!ids.contains(ID)) {
            ids.add((ID)); // if the id is not in the list it is added
            //keep this
            Toast.makeText(EnterID.this, "ID Added", Toast.LENGTH_SHORT).show(); // User is notified if ID is successfully added
        } else {
            //keep this
            Toast.makeText(EnterID.this, "ID Already Exists", Toast.LENGTH_SHORT).show(); // User is notified if ID is already in the list
        }
        //keep this
        Intent refresh = new Intent(EnterID.this, EnterID.class);
        startActivity(refresh); // recreates the page so that a "U" is placed back in the starting position
    }

    public void removeID (View v) {
        TextView num = findViewById(R.id.inputNumber); // inputted ID is saved as a textView
        String ID = num.getText().toString(); // the input is converted to a string

        // remove U from line
        //cast to int
        // add number to database with same logic below
        //just change formatting for the server database


        if (ids.contains(ID)) {
            ids.remove((ID)); // if the id is in the list it is removed
            //keep this
            Toast.makeText(EnterID.this, "ID Removed", Toast.LENGTH_SHORT).show(); // User is notified if ID is successfully removed
        } else {
            //keep this
            Toast.makeText(EnterID.this, "ID Doesn't Exist To Remove", Toast.LENGTH_SHORT).show(); // User is notified if ID is not in the list and therefore cannot be removed
        }
        //keep this
        Intent refresh = new Intent(EnterID.this, EnterID.class);
        startActivity(refresh); // recreates the page so that a "U" is placed back in the starting position
    }
}