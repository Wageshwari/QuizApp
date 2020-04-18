package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class option_menu extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.about:
                Toast.makeText(getApplicationContext(), "About Us Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.contact:
                Toast.makeText(getApplicationContext(), "Contact Us Selected", Toast.LENGTH_LONG).show();
                Intent in = new Intent(this, contact.class);
                startActivity(in);
                break;
            case R.id.gallery:
                Toast.makeText(getApplicationContext(), "Gallery Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.logout:
                Toast.makeText(getApplicationContext(), "Log OutSelected", Toast.LENGTH_LONG).show();
                fAuth.signOut();
                return true;
        }
                return super.onOptionsItemSelected(item);

        }
    }

