package com.example.myfirst;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_Main_Page extends AppCompatActivity implements View.OnClickListener {

    Button add_post;

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__main__page);


        // To retrive post data

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();


        reference = firebaseDatabase.getReference("Posts").child(currentuid);




        BottomNavigationView bottomNavigationView =findViewById(R.id.user_bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.user_bottom_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.user_bottom_profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.user_bottom_home:
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

        add_post = (Button) findViewById(R.id.Add_post);
        add_post.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Post, ViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Post, ViewHolder>(

                        Post.class,
                        R.layout.post_retrive,
                        ViewHolder.class,
                        reference

                ) {


                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Post post, int i) {

                        viewHolder.setdetails(getApplicationContext(), Post.getDescription(), Post.getUrgency_level(), Post.getSelect_NGO(), Post.getPost_Type(), Post.getAnimal_Category(), Post.getAddress(), Post.getSelectedUri());

                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Add_post:
                startActivity(new Intent(this, Create_Post.class));
                break;
        }
    }
}