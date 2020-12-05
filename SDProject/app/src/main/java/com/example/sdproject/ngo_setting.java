package com.example.sdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ngo_setting extends AppCompatActivity {

    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_setting);

        Logout = (Button) findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); // Firebase function signout
                startActivity(new Intent(ngo_setting.this, ngo_signup.class));
                finish(); // To delete the final activity
            }
        });



        BottomNavigationView bottomNavigationView =findViewById(R.id.ngo_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.ngo_bottom_setting);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ngo_bottom_profile:
                        startActivity(new Intent(getApplicationContext(), ngoProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ngo_bottom_home:
                        startActivity(new Intent(getApplicationContext(), ngo_Main_Page.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ngo_bottom_setting:
                        return true;
                }
                return false;
            }
        });
    }
}