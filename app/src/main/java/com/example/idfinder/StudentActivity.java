package com.example.idfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        EditText editText = findViewById(R.id.identificationInput);
        String initialText = "U";
        editText.setText(initialText); // sets a "U" as the first value in the text box on creation
    }

    public void submitInfo(View v) {
        // if id is found in system
        Intent idFound = new Intent(StudentActivity.this, IDfound.class); // create intent if id is found in system
        startActivity(idFound); // sends the user to the id found screen

        // if id is not found

        Intent idNotFound = new Intent(StudentActivity.this, IDnotfound.class); // create intent if id is not found in system

        startActivity(idNotFound); // sends the user to the id not found screen
    }

}