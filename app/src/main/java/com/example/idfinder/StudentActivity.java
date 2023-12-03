package com.example.idfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class StudentActivity extends AppCompatActivity {
    private static final String SERVER_URL = "http://10.239.182.200:5000";
    private static final String checkIDRoute = "/checkID";

    Button checkForID;

    String isFound = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        checkForID = (Button) findViewById(R.id.checkForIDButton);
        checkForID.setOnClickListener(this::checkForID);
        isFound = ""; //initialize
    }

    private void checkForID(View view) {
        //change string to json
        TextView value = findViewById(R.id.identificationInput);
        String id = value.getText().toString();
        if (id.length() == 8) {
            String jsonData = "{\"id\" : \"" + id + "\"}";
            new CheckID().execute(jsonData);
        } else {
            Toast.makeText(StudentActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        //send the data to the server to check if the ID exists in the database
    }

    private class CheckID extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            //this is where it connects to the server and checks if the ID exists if so
            //then return true or false
            try {
                String inputString = params[0];
                URL url = new URL(SERVER_URL + checkIDRoute); // Replace with your server details
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.getOutputStream().write(inputString.getBytes(StandardCharsets.UTF_8));
                connection.getResponseCode(); // Trigger the request

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                //return the result
                inputString = response.toString();
                return inputString;
            } catch (Exception e) {
                Log.e("HTTP", "Error sending data to server", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //after the doInBackground(), this function gets called, and it just opens a new
            //activity according to the value
            isFound = result;
            if(isFound.equals("true")) {
                Intent idFound = new Intent(StudentActivity.this, IDfound.class); // create intent if id is found in system
                startActivity(idFound); // sends the user to the id found screen

            } else {
                Intent idNotFound = new Intent(StudentActivity.this, IDnotfound.class); // create intent if id is not found in system
                startActivity(idNotFound); // sends the user to the id not found screen
            }
        }

    }

}