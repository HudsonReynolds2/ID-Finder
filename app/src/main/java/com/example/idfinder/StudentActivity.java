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
//-----TODO: work on this
//package com.example.idfinder;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.TextView;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//
//public class StudentActivity extends AppCompatActivity {
//    private static final String SERVER_URL = "http://10.239.182.200:5000";
//    private static final String checkIDRoute = "/checkID";
//
//    Button checkForID;
//
//    static String isFound = "";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student);
//
//        checkForID = (Button) findViewById(R.id.checkForIDButton);
//        checkForID.setOnClickListener(this::checkForID);
//
//        EditText editText = findViewById(R.id.identificationInput);
//        String initialText = "U";
//        editText.setText(initialText); // sets a "U" as the first value in the text box on creation
//        isFound = "XD";
//    }
//
//    private void checkForID(View view) {
//        TextView value = findViewById(R.id.identificationInput);
//        String str = value.getText().toString();
//        String id = str.substring(1);
//        String jsonData = "{\"id\" : \"" + id + "\"}";
//
//        CheckID(jsonData, new MyCallBack() {
//            @Override
//            public void onResult(String result) {
//                Log.d("final-result-2", result);
//            }
//        });
////        Log.d("final-result-2", isFound);
//
////        if(isFound.equals("true")) {
////            Intent idFound = new Intent(StudentActivity.this, IDfound.class); // create intent if id is found in system
////            startActivity(idFound); // sends the user to the id found screen
////
////        } else {
////            Intent idNotFound = new Intent(StudentActivity.this, IDnotfound.class); // create intent if id is not found in system
////            startActivity(idNotFound); // sends the user to the id not found screen
////        }
//    }
//
//    public static void CheckID(final String jsonData, final MyCallBack callback) {
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... params) {
//                try {
//                    URL url = new URL(SERVER_URL + checkIDRoute); // Replace with your server details
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("POST");
//
//                    connection.setRequestProperty("Content-Type", "application/json");
//                    connection.setDoOutput(true);
//                    connection.getOutputStream().write(jsonData.getBytes(StandardCharsets.UTF_8));
//                    connection.getResponseCode(); // Trigger the request
//
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    return response.toString();
//                } catch (Exception e) {
//                    Log.e("HTTP", "Error sending data to server", e);
//                    return null;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                if (callback != null) {
//                    callback.onResult(result);
//                }
//            }
//        }.execute();
//    }
//
//}