package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class User_registration extends AppCompatActivity implements View.OnClickListener {

    private TextView AppName, Register;
    private EditText editTextFname, editTextLname, editTextEmail, editTextPassword, editTextMobile;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        mAuth = FirebaseAuth.getInstance();

        AppName = (TextView) findViewById(R.id.AppName);
        AppName.setOnClickListener(this);

        Register = (Button) findViewById(R.id.Register);
        Register.setOnClickListener(this);

        editTextFname = (EditText) findViewById(R.id.Fname);
        editTextLname = (EditText) findViewById(R.id.Lname);
        editTextEmail = (EditText) findViewById(R.id.Email);
        editTextPassword = (EditText) findViewById(R.id.Password);
        editTextMobile = (EditText) findViewById(R.id.Mobile);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.AppName:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.Register:
                Register();
                break;
        }
    }

    private void Register() {
        final String Fname = editTextFname.getText().toString().trim();
        final String Lname = editTextLname.getText().toString().trim();
        final String Email = editTextEmail.getText().toString().trim();
        final String Password = editTextPassword.getText().toString().trim();
        final String Mobile = editTextMobile.getText().toString().trim();

        if(Fname.isEmpty()) {
            editTextFname.setError("First Name Required");
            editTextFname.requestFocus();
            return;
        }
        if(Lname.isEmpty()) {
            editTextLname.setError("Last Name Required");
            editTextLname.requestFocus();
            return;
        }

        if(Email.isEmpty()) {
            editTextEmail.setError("Email Required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(Password.isEmpty()) {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if(Password.length()<6) {
            editTextPassword.setError("Minimum Password Length should be 6 character");
            editTextPassword.requestFocus();
            return;
        }

        if(Mobile.isEmpty()) {
            editTextMobile.setError("Mobile Number Required");
            editTextMobile.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(Mobile).matches()){
            editTextEmail.setError("Please provide valid Mobile Number");
            editTextEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(Fname, Lname, Email, Mobile, Password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(User_registration.this, "You have been registered Sussessfully!!, Please Login", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(User_registration.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(User_registration.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });
                        }else {
                            Toast.makeText(User_registration.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}