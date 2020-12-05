package com.example.sdproject;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;

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

public class user_Main_Page extends AppCompatActivity implements View.OnClickListener {

    Button add_post;

    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;


    private DatabaseReference reference;

    private ArrayList<Post> postList;

    private RecyclerAdapter recyclerAdapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__main__page);


        // To retrive post data

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


        reference = firebaseDatabase.getReference("All Posts").child(currentuid);


        // BOTTOM NAVIGATION VIEW

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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Add_post:
                startActivity(new Intent(this, Create_Post.class));
                break;
        }

    }
}





//package com.example.sdproject;
//
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.MenuItem;
////import android.view.View;
////import android.widget.Button;
////
////import com.google.android.material.bottomnavigation.BottomNavigationView;
//
////import com.firebase.ui.database.FirebaseRecyclerAdapter;
//
//        import androidx.annotation.NonNull;
//        import androidx.appcompat.app.ActionBarDrawerToggle;
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.appcompat.widget.Toolbar;
//        import androidx.core.view.GravityCompat;
//        import androidx.drawerlayout.widget.DrawerLayout;
//        import androidx.fragment.app.Fragment;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import android.content.Context;
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.widget.Button;
//
//        import com.google.android.material.bottomnavigation.BottomNavigationView;
//        import com.google.firebase.auth.FirebaseAuth;
//        import com.google.firebase.auth.FirebaseUser;
//        import com.google.firebase.database.DataSnapshot;
//        import com.google.firebase.database.DatabaseError;
//        import com.google.firebase.database.DatabaseReference;
//        import com.google.firebase.database.FirebaseDatabase;
//        import com.google.firebase.database.Query;
//        import com.google.firebase.database.ValueEventListener;
//
//        import java.util.ArrayList;
//
//public class user_Main_Page extends AppCompatActivity implements View.OnClickListener {
//
//    Button add_post;
//
//    private RecyclerView recyclerView;
//
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    final String currentuid = user.getUid();
//
//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("All Posts");
//
//    private RecyclerAdapter recyclerAdapter;
//    private ArrayList<Post> postList;
//
//
////    FirebaseDatabase firebaseDatabase;
//
//
////    private DatabaseReference reference;
//
//
//
////    private Context context;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user__main__page);
//
//
//        // To retrive post data
//
//        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // ArrayList
//        postList = new ArrayList<>();
//        recyclerAdapter = new RecyclerAdapter(this, postList);
//
//        recyclerView.setAdapter(recyclerAdapter);
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { // Child event listener
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                    Post post = dataSnapshot1.getValue(Post.class);
//                    postList.add(post);
//
//                }
//                recyclerAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//
//
////        reference = FirebaseDatabase.getInstance().getReference();
////
////        // ArrayList
////        postList = new ArrayList<>();
////
////        // Clear List
////        ClearALL();
////
////        // Get Data method
////        GetDataFromFirebase();
////
////
////        recyclerView.setHasFixedSize(true);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        firebaseDatabase = FirebaseDatabase.getInstance();
////
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        String currentuid = user.getUid();
////
////
////        reference = firebaseDatabase.getReference("All Posts").child(currentuid);
//
//
//        // BOTTOM NAVIGATION VIEW
//
//        BottomNavigationView bottomNavigationView =findViewById(R.id.user_bottom_nav);
//        bottomNavigationView.setSelectedItemId(R.id.user_bottom_home);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.user_bottom_profile:
//                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.user_bottom_home:
//                        return true;
//
//                    case R.id.user_bottom_queue:
//                        startActivity(new Intent(getApplicationContext(), user_request.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.user_bottom_setting:
//                        startActivity(new Intent(getApplicationContext(), user_setting.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                }
//                return false;
//            }
//        });
//
//        add_post = (Button) findViewById(R.id.Add_post);
//        add_post.setOnClickListener(this);
//    }
//
//
////    private void GetDataFromFirebase() {
////
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        final String currentuid = user.getUid();
////
////        Query query = reference.child("All Posts").child(currentuid);
////
////        query.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                ClearALL();
////                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                    Post post = new Post();
////
////                    post.setDescription(snapshot.child("Description").getValue().toString());
////                    post.setUrgency_level(snapshot.child("Urgency_level").getValue().toString());
////                    post.setSelect_NGO(snapshot.child("Select_NGO").getValue().toString());
////                    post.setPost_Type(snapshot.child("Post_Type").getValue().toString());
////                    post.setAnimal_Category(snapshot.child("Animal_Category").getValue().toString());
////                    post.setAddress(snapshot.child("Address").getValue().toString());
//////                    post.setSelectedUri(snapshot.child("selectedUri").getValue().toString());
////
////                    postList.add(post);
////                }
////
////                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), postList);
////                recyclerView.setAdapter(recyclerAdapter);
////                recyclerAdapter.notifyDataSetChanged();
////
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////
////            }
////        });
////    }
////
////    private void ClearALL(){
////        if(postList != null) {
////            postList.clear();
////
////            if(recyclerAdapter != null) {
////                recyclerAdapter.notifyDataSetChanged();
////            }
////        }
////
////        postList = new ArrayList<>();
////    }
//
//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()) {
//            case R.id.Add_post:
//                startActivity(new Intent(this, Create_Post.class));
//                break;
//        }
//
//    }
//}