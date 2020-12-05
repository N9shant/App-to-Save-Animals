package com.example.sdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class user_request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request);

        BottomNavigationView bottomNavigationView =findViewById(R.id.user_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.user_bottom_queue);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.user_bottom_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.user_bottom_home:
                        startActivity(new Intent(getApplicationContext(), user_Main_Page.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.user_bottom_queue:
                        return true;

                    case R.id.user_bottom_setting:
                        startActivity(new Intent(getApplicationContext(), user_setting.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}