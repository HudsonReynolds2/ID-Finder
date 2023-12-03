package com.example.idfinder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class EnterID extends AppCompatActivity{
    private static final String SERVER_URL = "http://10.239.182.200:5000";
    private static final String addingIDRoute = "/addID";
    private static final String removeIDRoute = "/deleteID";
    private static final String getDataRoute = "/getIDs";
    Button addButton;
    Button removeIDButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_id);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(this::addID);

        removeIDButton = (Button) findViewById(R.id.removeButton);
        removeIDButton.setOnClickListener(this::removeID);
    }

    private void addID(View view) {
        //this block of code modifies the text values into json string without the "U"
        TextView value = findViewById(R.id.inputNumber);
        String id = value.getText().toString();
        //make sure what they entered was a valid-8-digit ID
        if (id.length() == 8) {
            String jsonData = "{\"id\" : \"" + id + "\"}";
            //Send the data to the server and add it
            AddIDToServer(jsonData);
            //send a response to the user saying it was added
            Toast.makeText(EnterID.this, "ID Added", Toast.LENGTH_SHORT).show();
            Intent refresh = new Intent(EnterID.this, EnterID.class);
            startActivity(refresh); // recreates the page so that a "U" is placed back in the starting position
        } else {
            Toast.makeText(EnterID.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

    }
    private void removeID(View view) {
        //this block of code modifies the text values into json string without the "U"
        TextView value = findViewById(R.id.inputNumber);
        String id = value.getText().toString();
        //make sure what they entered was a valid-8-digit ID
        if (id.length() == 8) {
            String jsonData = "{\"id\" : \"" + id + "\"}";
            //Send the data to the server and the server will remove it from its database
            RemoveIDFromServer(jsonData);
            //send a response to the user saying it was removed
            Toast.makeText(EnterID.this, "ID Removed", Toast.LENGTH_SHORT).show();
            Intent refresh = new Intent(EnterID.this, EnterID.class);
            startActivity(refresh); // recreates the page so that a "U" is placed back in the starting position
        } else {
            Toast.makeText(EnterID.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private static void AddIDToServer(final String jsonData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //Secure a Connection to the server
                    URL url = new URL(SERVER_URL + addingIDRoute);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //Send the ID to the server to add it in the database
                    connection.setRequestMethod("POST");//keyword: POST; tells the server I want to "add" something
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(jsonData.getBytes(StandardCharsets.UTF_8));
                    connection.getResponseCode();
                    Log.d("data-sent", "Sent ID: " + jsonData);
                } catch (Exception e) {
                    Log.e("HTTP", "Error sending data to server", e);
                }
                return null;
            }
        }.execute();
    }

    private  static  void RemoveIDFromServer(final String jsonData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //Secure a Connection to the server
                    URL url = new URL(SERVER_URL + removeIDRoute);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //Send the info to the server to delete
                    connection.setRequestMethod("DELETE"); //keyword: DELETE; tells the server I want to delete info
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(jsonData.getBytes(StandardCharsets.UTF_8));
                    connection.getResponseCode(); // Trigger the request
                    Log.d("data-remove", "Removed ID: " + jsonData);
                } catch (Exception e) {
                    Log.e("HTTP", "Error sending data to server", e);
                }
                return null;
            }
        }.execute();
    }

    private static void GetAllData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    //Secure a Connection to the server
                    URL url = new URL(SERVER_URL + getDataRoute);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    //Gets the data and is in JSON format
                    connection.setRequestMethod("GET");//keyword GET; tells the server I want the data
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.d("data-get", response.toString());
                } catch (Exception e) {
                    Log.e("HTTP", "Error getting data from server", e);
                    return null;
                }
                return  null;
            }
        }.execute();
    }
}
