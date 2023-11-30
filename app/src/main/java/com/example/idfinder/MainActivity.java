package com.example.idfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button adminLoginButton; //declared AdminLongButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminLoginButton = (Button) findViewById(R.id.adminButton); //intializing adminButton
        adminLoginButton.setOnClickListener(this::AdminLoginPage); //setting the click to adminLoginPage
    }

    public void AdminLoginPage(View v) {
        //creates an 'Intent' to open the login page
        Intent adminLogin = new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(adminLogin);
    }

}
