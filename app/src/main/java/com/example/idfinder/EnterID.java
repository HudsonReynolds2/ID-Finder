package com.example.idfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EnterID extends AppCompatActivity {
    public ArrayList<String> ids = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_id);

        EditText editText = findViewById(R.id.inputNumber);
        String initialText = "U";
        editText.setText(initialText);
    }
    public void addID (View v){
        TextView num = findViewById(R.id.inputNumber); // inputted ID is saved as a textView
        String ID = num.getText().toString(); // the input is converted to a string
        if (!ids.contains(ID)) {
            ids.add((ID)); // if the id is not in the list it is added
            num.setText(""); // the text box is cleared
            Toast.makeText(EnterID.this, "ID Added", Toast.LENGTH_SHORT).show(); // User is notified if ID is successfully added
        } else {
            Toast.makeText(EnterID.this, "ID Already Exists", Toast.LENGTH_SHORT).show(); // User is notified if ID is already in the list
        }
    }

    public void removeID (View v) {
        TextView num = findViewById(R.id.inputNumber); // inputted ID is saved as a textView
        String ID = num.getText().toString(); // the input is converted to a string
        if (ids.contains(ID)) {
            ids.remove((ID)); // if the id is in the list it is removed
            num.setText(""); // the text box is cleared
            Toast.makeText(EnterID.this, "ID Removed", Toast.LENGTH_SHORT).show(); // User is notified if ID is successfully removed
        } else {
            Toast.makeText(EnterID.this, "ID Doesn't Exist To Remove", Toast.LENGTH_SHORT).show(); // User is notified if ID is not in the list and therefore cannot be removed
        }
    }
}