package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    //private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Logout = (Button) findViewById(R.id.Logout);
//
//        Logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut(); // Firebase function signout
//                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
//                finish(); // To delete the final activity
//            }
//        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView firstNameTextView = (TextView) findViewById(R.id.firstName);
        final TextView LastNameTextView = (TextView) findViewById(R.id.lastName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);
        final TextView phoneTextView = (TextView) findViewById(R.id.Phone);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);

                if(userProfile != null) {
                    String firstName = userProfile.Fname; // Values from above TextViews
                    String lastName = userProfile.Lname;
                    String email = userProfile.Email;
                    String mobile = userProfile.Mobile;

                    greetingTextView.setText("Welcome, " + firstName + " " + lastName + "!");
                    firstNameTextView.setText(firstName); // Values from above step
                    LastNameTextView.setText(lastName);
                    emailTextView.setText(email);
                    phoneTextView.setText(mobile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "Something wrong happened!!", Toast.LENGTH_LONG).show();
            }
        });




        BottomNavigationView bottomNavigationView =findViewById(R.id.user_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.user_bottom_profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.user_bottom_profile:
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
                        startActivity(new Intent(getApplicationContext(), user_setting.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }
}