package com.example.myfirst;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Create_Post extends AppCompatActivity {

    private EditText urge_level, select_ngo, post_type, animal_category;
    private Spinner spinner, spinner1, spinner2, spinner3;

    private EditText description, address;
    private Button create_post, select_image;

    private ProgressBar progressBar;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    Post post;

    private FirebaseAuth mAuth;

    private static final int PICK_IMAGE = 1;
    private ImageView imageView;
    private Uri selectedUri;

    // Video and image


    //    UploadTask uploadTask;
//    VideoView videoView;
    String url, name;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    private StorageReference storageReferance;
    private DatabaseReference databaseReference;

//    private MediaController mediaController;
//    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts").child(currentuid);
        storageReferance = FirebaseStorage.getInstance().getReference().child("Posts").child(currentuid);

        mAuth = FirebaseAuth.getInstance();


        description = (EditText) findViewById(R.id.Description);
        address = (EditText) findViewById(R.id.Address);

        create_post = findViewById(R.id.Create_Post);


        select_image = findViewById(R.id.Select_Image);
        imageView = findViewById(R.id.Post_Image);




        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openImagechooser();

            }
        });

        create_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post(); // UPLOADFILE
            }
        });





        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        ///////////////// URGENCY LEVEL //////////////////

        urge_level = findViewById(R.id.Urgency_level);
        spinner = findViewById(R.id.spinner);

        List<String> categories = new ArrayList<>();
        categories.add(0, "Choose Urgency Level");
        categories.add("Very urgent");
        categories.add("Within few hours");
        categories.add("Within a day");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adapterView.getItemAtPosition(i).equals("Choose Urgency Level")) {

                } else {
                    urge_level.setText(adapterView.getSelectedItem().toString()); // Selected item in the text view
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ///////////////// SELECT NEARBY NGO //////////////////

        select_ngo = findViewById(R.id.Select_NGO);
        spinner1 = findViewById(R.id.spinner1);

        List<String> categories1 = new ArrayList<>();
        categories1.add(0, "Choose Nearby NGO");
        categories1.add("Very urgent");
        categories1.add("Within few hours");
        categories1.add("Within a day");

        ArrayAdapter<String> dataAdapter1;
        dataAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adapterView.getItemAtPosition(i).equals("Choose Nearby NGO")) {

                } else {
                    select_ngo.setText(adapterView.getSelectedItem().toString()); // Selected item in the text view
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ///////////////// SELECT Post Type //////////////////

        post_type = findViewById(R.id.Post_Type);
        spinner2 = findViewById(R.id.spinner2);

        List<String> categories2 = new ArrayList<>();
        categories2.add(0, "Select Post Type");
        categories2.add("Stray Animals");
        categories2.add("Animal harassment");
        categories2.add("Stranded due to natural calamity");
        categories2.add("Any other");


        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(dataAdapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adapterView.getItemAtPosition(i).equals("Select Post Type")) {

                } else {
                    post_type.setText(adapterView.getSelectedItem().toString()); // Selected item in the text view
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ///////////////// SELECT Category of Animal //////////////////

        animal_category = findViewById(R.id.Animal_Category);
        spinner3 = findViewById(R.id.spinner3);

        List<String> categories3 = new ArrayList<>();
        categories3.add(0, "Category of Animal");
        categories3.add("Domestic");
        categories3.add("Wild");


        ArrayAdapter<String> dataAdapter3;
        dataAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(dataAdapter3);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (adapterView.getItemAtPosition(i).equals("Category of Animal")) {

                } else {
                    animal_category.setText(adapterView.getSelectedItem().toString()); // Selected item in the text view
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void openImagechooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        if(requestCode == PICK_IMAGE || resultCode == RESULT_OK ||
        data != null || data.getData() != null) {

            selectedUri = data.getData();
            Picasso.get().load(selectedUri).into(imageView);



        }


    }

    private String getFileExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }




//    private void chooseImage() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/* video/*");
//        startActivityForResult(intent, PICK_FILE);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == PICK_FILE || requestCode == RESULT_OK ||
//        data != null || data.getData() != null) {
//
//
//            selectedUri = data.getData();
//            if(selectedUri.toString().contains("image")) {
//                Picasso.get().load(selectedUri).into(imageView);
//                imageView.setVisibility(View.VISIBLE);
//                videoView.setVisibility(View.INVISIBLE);
//                type = "iv";
//
//            }else if(selectedUri.toString().contains("video")) {
//                videoView.setMediaController(mediaController);
//                videoView.setVisibility(View.VISIBLE);
//                imageView.setVisibility(View.INVISIBLE);
//                videoView.setVideoURI(selectedUri);
//                videoView.start();
//                type = "vv";
//
//            }else {
//                Toast.makeText(this, "NO File Selected", Toast.LENGTH_LONG).show();
//            }
//        }
//    }

//    private String getFileExt(Uri uri) {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String currentuid = user.getUid();
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference documentReference = db.collection("Users").document(currentuid);
//
//        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.getResult().exists()) {
//                    name = task.getResult().getString("mane");
//                    url = task.getResult().getString("url");
//                }else {
//                    Toast.makeText(Create_Post.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }


    private void Post () {

        final String Description = description.getText().toString().trim();
        final String Urgency_level = urge_level.getText().toString().trim();
        final String Select_NGO = select_ngo.getText().toString().trim();
        final String Post_Type = post_type.getText().toString().trim();
        final String Animal_Category = animal_category.getText().toString().trim();
        final String Address = address.getText().toString().trim();


        if (Description.isEmpty()) {
            description.setError("Description Required");
            description.requestFocus();
            return;
        }

        if (Urgency_level.isEmpty()) {
            urge_level.setError("Specify urgency level");
            urge_level.requestFocus();
            return;
        }

        if (Select_NGO.isEmpty()) {
            select_ngo.setError("Please select an NGO");
            select_ngo.requestFocus();
            return;
        }

        if (Post_Type.isEmpty()) {
            post_type.setError("Please select Post Type");
            post_type.requestFocus();
            return;
        }

        if (Animal_Category.isEmpty()) {
            animal_category.setError("Please select Category of animal");
            animal_category.requestFocus();
            return;
        }

        if (Address.isEmpty()) {
            address.setError("Please provide incident address");
            address.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        if(selectedUri != null) {
            StorageReference filereference = storageReferance.child(System.currentTimeMillis()
            + "." + getFileExt(selectedUri));

            filereference.putFile(selectedUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Create_Post.this, "Post Uploaded", Toast.LENGTH_SHORT).show();

                            Post post = new Post(Description, Urgency_level, Select_NGO, Post_Type, Animal_Category, Address,
                                    taskSnapshot.getUploadSessionUri().toString());

                            String uploadId = databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(post);

                            progressBar.setVisibility(View.GONE);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Create_Post.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });

        }else {
            Toast.makeText(Create_Post.this, "No File Selected", Toast.LENGTH_SHORT).show();
        }




        // Video and image

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String currentuid = user.getUid();
//
//        Calendar cdate = Calendar.getInstance();
//        SimpleDateFormat currentdate = new SimpleDateFormat("dd-mm-yyyy");
//        final String savedate = currentdate.format(cdate.getTime());
//
//        Calendar ctime = Calendar.getInstance();
//        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
//        final String savetime = currenttime.format(ctime.getTime());
//
//        String time = savedate + ":" + savetime;

        // File is empty or not


//        if(selectedUri != null) {
//
//            final StorageReference reference = storageReferance.child(System.currentTimeMillis() + "." + getFileExt(selectedUri));
//
//
//        }




//        progressBar.setVisibility(View.GONE);
//        Toast.makeText(Create_Post.this, "Post Created", Toast.LENGTH_LONG).show();


    }
}