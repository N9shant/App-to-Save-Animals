package com.example.sdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ngo_Main_Page extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;


    private DatabaseReference reference;

    private ArrayList<Post> postList;

    private RecyclerAdapter recyclerAdapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo__main__page);

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();

        // ArrayList
        postList = new ArrayList<>();

        // Clear List
        ClearALL();

        // Get Data method
        GetDataFromFirebase();


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();


        reference = firebaseDatabase.getReference("All Posts");

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

    private void GetDataFromFirebase() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String currentuid = user.getUid();

        Query query = reference.child("All Posts").child(currentuid);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearALL();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Post post = new Post();

                    post.setDescription(snapshot.child("Description").getValue().toString());
                    post.setUrgency_level(snapshot.child("Urgency_level").getValue().toString());
                    post.setSelect_NGO(snapshot.child("Select_NGO").getValue().toString());
                    post.setPost_Type(snapshot.child("Post_Type").getValue().toString());
                    post.setAnimal_Category(snapshot.child("Animal_Category").getValue().toString());
                    post.setAddress(snapshot.child("Address").getValue().toString());
//                    post.setSelectedUri(snapshot.child("selectedUri").getValue().toString());

                    postList.add(post);
                }

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), postList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void ClearALL(){
        if(postList != null) {
            postList.clear();

            if(recyclerAdapter != null) {
                recyclerAdapter.notifyDataSetChanged();
            }
        }

        postList = new ArrayList<>();
    }
}