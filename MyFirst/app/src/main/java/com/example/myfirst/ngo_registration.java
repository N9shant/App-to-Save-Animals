package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ngo_registration extends AppCompatActivity implements View.OnClickListener{

    private TextView banner, signup;
    private EditText editTextngoname, editTextDateField, editTextEmail, editTextPassword, editTextConfirmPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    DatePickerDialog.OnDateSetListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_registration);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.Banner);
        banner.setOnClickListener(this);

        signup = (Button) findViewById(R.id.SignUp);
        signup.setOnClickListener(this);

        editTextngoname = (EditText) findViewById(R.id.NGOName);
        editTextEmail = (EditText) findViewById(R.id.Reg_Email);
        editTextPassword = (EditText) findViewById(R.id.Reg_Password);
        editTextConfirmPassword = (EditText) findViewById(R.id.Confirm_Password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        editTextDateField = findViewById(R.id.DateField);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ngo_registration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        editTextDateField.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.SignUp:
                SignUp();
                break;
        }
    }

    private void SignUp() {
        final String NGOName = editTextngoname.getText().toString().trim();
        final String DateField = editTextDateField.getText().toString().trim();
        final String Reg_Email = editTextEmail.getText().toString().trim();
        final String Reg_Password = editTextPassword.getText().toString().trim();
        final String Confirm_Password = editTextConfirmPassword.getText().toString().trim();

        if(NGOName.isEmpty()) {
            editTextngoname.setError("Name of NGO Required");
            editTextngoname.requestFocus();
            return;
        }

        if(Reg_Email.isEmpty()) {
            editTextEmail.setError("Email Required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Reg_Email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(Reg_Password.isEmpty()) {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if(Reg_Password.length()<6) {
            editTextPassword.setError("Minimum Password Length should be 6 character");
            editTextPassword.requestFocus();
            return;
        }
        if(Confirm_Password.isEmpty()) {
            editTextConfirmPassword.setError("Confirm Password Required");
            editTextConfirmPassword.requestFocus();
            return;
        }
        if(Confirm_Password.length()<6) {
            editTextConfirmPassword.setError("Minimum Confirm Password Length should be 6 character");
            editTextConfirmPassword.requestFocus();
            return;
        }
        if(!(Confirm_Password.equals(Reg_Password))) {
            editTextConfirmPassword.setError("Your Password and Confirm Password does not match");
            editTextConfirmPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(Reg_Email, Reg_Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            NGO ngo = new NGO(NGOName, DateField, Reg_Email, Reg_Password, Confirm_Password);

                            FirebaseDatabase.getInstance().getReference("NGO")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(ngo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ngo_registration.this, "You have been registered Sussessfully!!, Please Login", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(ngo_registration.this, ngo_signup.class));
                                        finish();
                                    }else{
                                        Toast.makeText(ngo_registration.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(ngo_registration.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}