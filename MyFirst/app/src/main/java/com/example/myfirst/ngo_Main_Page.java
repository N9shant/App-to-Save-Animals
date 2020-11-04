package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ngo_Main_Page extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo__main__page);

        BottomNavigationView bottomNavigationView =findViewById(R.id.ngo_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.ngo_bottom_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ngo_bottom_profile:
                        startActivity(new Intent(getApplicationContext(), ngoProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ngo_bottom_home:
                        return true;

                    case R.id.ngo_bottom_setting:
                        startActivity(new Intent(getApplicationContext(), ngo_setting.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }
}