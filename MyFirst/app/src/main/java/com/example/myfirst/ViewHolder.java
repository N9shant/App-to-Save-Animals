package com.example.myfirst;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String Tag = "RecyclerView";

    private Context context;
    private ArrayList<Post> postList;

    public ViewHolder(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView Description, Urgency_level, Select_NGO, Post_Type, Animal_Category, Address;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.rImageView);

            Description = itemView.findViewById(R.id.problem);
            Urgency_level = itemView.findViewById(R.id.UrgencyLevel);
            Select_NGO = itemView.findViewById(R.id.SelectNGO);
            Post_Type = itemView.findViewById(R.id.Post_Type);
            Animal_Category = itemView.findViewById(R.id.AnimalCategory);
            Address = itemView.findViewById(R.id.AddressofInc);

        }
    }

//    View view;


//    public void setdetails(Context context, String Description, String Urgency_level, String Select_NGO, String Post_Type, String Animal_Category, String Address, String Image) {
//
//
//
//        mDescription.setText(Description);
//        mUrgency_level.setText(Urgency_level);
//        mSelect_NGO.setText(Select_NGO);
//        mPost_Type.setText(Post_Type);
//        mAnimal_Category.setText(Animal_Category);
//        mAddress.setText(Address);
//
//
//
//        ImageView imageview = view.findViewById(R.id.rImageView);
//        Picasso.get().load(Image).into(imageview);
//
//        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//        itemView.startAnimation(animation);
//
//    }
}
