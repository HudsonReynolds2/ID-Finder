package com.example.idfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    private final String ValidUser = "IDadmin"; // unique login for admin access
    private final String ValidPass = "bostonu123"; // unique password for admin access

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }
    public void enterEmail(View v){
        TextView text1 = findViewById(R.id.emailAddress); //saves email address from login screen to a textview
        String email = text1.getText().toString(); //saves email into a string

        TextView text2 = findViewById(R.id.password); //saves email address from login screen to a textview
        String pass = text2.getText().toString(); //saves password into a string

        if (email.equals(ValidUser) && pass.equals(ValidPass)) { // checks if the entered login information is valid
            Intent enterID = new Intent(AdminLoginActivity.this, EnterID.class); // if login is successful the user is brought to the next screen where they can enter ID
            startActivity(enterID);
        } else {
            Toast.makeText(AdminLoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show(); // User is notified if login is unsuccessful
        }
    }
}