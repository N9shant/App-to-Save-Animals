package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ngoProfileActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;

    private String ngoID;

    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_profile);

        Logout = (Button) findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); // Firebase function signout
                startActivity(new Intent(ngoProfileActivity.this, ngo_signup.class));
                finish(); // To delete the final activity
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("NGO");
        ngoID = user.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView Estb_dateTextView = (TextView) findViewById(R.id.Estb_date);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAddress);

        reference.child(ngoID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NGO ngoProfile = dataSnapshot.getValue(NGO.class);

                if (ngoProfile != null) {
                    String fullName = ngoProfile.NGO_Name; // Values from above TextViews
                    String Estb_date = ngoProfile.DateField;
                    String email = ngoProfile.Reg_Email;

                    greetingTextView.setText("Welcome, " + fullName + "!");
                    fullNameTextView.setText(fullName); // Values from above step
                    Estb_dateTextView.setText(Estb_date);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ngoProfileActivity.this, "Something wrong happened!!", Toast.LENGTH_LONG).show();
                }
        });
    }
}