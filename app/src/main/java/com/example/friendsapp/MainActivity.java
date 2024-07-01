package com.example.friendsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    AppCompatButton b1,b2;

    String apiUrl = "https://friendsapi-re5a.onrender.com/adddata";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.fname);
        ed3 = (EditText) findViewById(R.id.nickname);
        ed4 = (EditText) findViewById(R.id.description);
        b1 = (AppCompatButton) findViewById(R.id.add);
        b2=  (AppCompatButton) findViewById(R.id.view);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = ed1.getText().toString();
                String getFName = ed2.getText().toString();
                String getNickname = ed3.getText().toString();
                String getDes = ed4.getText().toString();

                JSONObject friend = new JSONObject();
                try {
                    //reading values in JSON format
                    friend.put("name", getName);
                    friend.put("friendName", getFName);
                    friend.put("friendNickName", getNickname);
                    friend.put("DescribeYourFriend", getDes);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //JSON object request creation
                JsonObjectRequest jasonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                ed1.setText("");
                                ed2.setText("");
                                ed3.setText("");
                                ed4.setText("");
                                Toast.makeText(getApplicationContext(), "ADDED SUCCESFULLY", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jasonObjectRequest);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),ViewFriends.class);
                startActivity(i);
            }
        });
    }
}