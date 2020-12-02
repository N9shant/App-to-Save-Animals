package com.example.myfirst;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setdetails(Context context, String Description, String Urgency_level, String Select_NGO, String Post_Type, String Animal_Category, String Address, String Image) {

        TextView mDescription = view.findViewById(R.id.problem);
        TextView mUrgency_level = view.findViewById(R.id.UrgencyLevel);
        TextView mSelect_NGO = view.findViewById(R.id.SelectNGO);
        TextView mPost_Type = view.findViewById(R.id.Post_Type);
        TextView mAnimal_Category = view.findViewById(R.id.AnimalCategory);
        TextView mAddress = view.findViewById(R.id.AddressofInc);

        mDescription.setText(Description);
        mUrgency_level.setText(Urgency_level);
        mSelect_NGO.setText(Select_NGO);
        mPost_Type.setText(Post_Type);
        mAnimal_Category.setText(Animal_Category);
        mAddress.setText(Address);



        ImageView imageview = view.findViewById(R.id.rImageView);
        Picasso.get().load(Image).into(imageview);

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        itemView.startAnimation(animation);

    }
}
