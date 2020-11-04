package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class user_setting extends AppCompatActivity {

    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);


        Logout = (Button) findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); // Firebase function signout
                startActivity(new Intent(user_setting.this, MainActivity.class));
                finish(); // To delete the final activity

            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.user_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.user_bottom_setting);

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
                        startActivity(new Intent(getApplicationContext(), user_request.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.user_bottom_setting:
                        return true;
                }
                return false;
            }
        });

    }
}

//    private void logout() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(user_setting.this);
//        builder.setTitle("Logout")
//                .setMessage("Do you want to Logout")
//                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseAuth.getInstance().signOut(); // Firebase function signout
//                        startActivity(new Intent(user_setting.this, MainActivity.class));
//                        finish(); // To delete the final activity
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//    }
