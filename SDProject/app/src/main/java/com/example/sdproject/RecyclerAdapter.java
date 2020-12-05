package com.example.sdproject;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

        import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";

    private Context context;
    private ArrayList<Post> postList;

    public RecyclerAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_retrive, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Description.setText(postList.get(position).getDescription());
        holder.Urgency_level.setText(postList.get(position).getUrgency_level());
        holder.Select_NGO.setText(postList.get(position).getSelect_NGO());
        holder.Post_Type.setText(postList.get(position).getPost_Type());
        holder.Animal_Category.setText(postList.get(position).getAnimal_Category());
        holder.Address.setText(postList.get(position).getAddress());

        // Image View - Glide Library

//        Glide.with(context)
//                .load(postList.get(position).getSelectedUri())
//                .into(holder.imageview);





    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    // VIEWHOLDER CLASS
    public class ViewHolder extends RecyclerView.ViewHolder {

        //        ImageView imageview;
        TextView Description, Urgency_level, Select_NGO, Post_Type, Animal_Category, Address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            imageview = itemView.findViewById(R.id.rImageView);

            Description = itemView.findViewById(R.id.problem);
            Urgency_level = itemView.findViewById(R.id.UrgencyLevel);
            Select_NGO = itemView.findViewById(R.id.SelectNGO);
            Post_Type = itemView.findViewById(R.id.Post_Type);
            Animal_Category = itemView.findViewById(R.id.AnimalCategory);
            Address = itemView.findViewById(R.id.AddressofInc);

        }
    }


}










//package com.example.sdproject;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
////import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
//
////    private static final String Tag = "RecyclerView";
//
//    private Context context;
//    private ArrayList<Post> postList;
//
//    public RecyclerAdapter(Context context, ArrayList<Post> postList) {
//        this.context = context;
//        this.postList = postList;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(context)
//                .inflate(R.layout.post_retrive, parent, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        Post post = postList.get(position);
//
//        holder.Description.setText(post.getDescription());
//        holder.Urgency_level.setText(post.getUrgency_level());
//        holder.Select_NGO.setText(post.getSelect_NGO());
//        holder.Post_Type.setText(post.getPost_Type());
//        holder.Animal_Category.setText(post.getAnimal_Category());
//        holder.Address.setText(post.getAddress());
//
//        // Image View - Glide Library
//
////        Glide.with(context)
////                .load(postList.get(position).getSelectedUri())
////                .into(holder.imageview);
//
//
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return postList.size();
//    }
//
//
//    // VIEWHOLDER CLASS
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        //        ImageView imageview;
//        TextView Description, Urgency_level, Select_NGO, Post_Type, Animal_Category, Address;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
////            imageview = itemView.findViewById(R.id.rImageView);
//
//            Description = itemView.findViewById(R.id.problem);
//            Urgency_level = itemView.findViewById(R.id.UrgencyLevel);
//            Select_NGO = itemView.findViewById(R.id.SelectNGO);
//            Post_Type = itemView.findViewById(R.id.Post_Type);
//            Animal_Category = itemView.findViewById(R.id.AnimalCategory);
//            Address = itemView.findViewById(R.id.AddressofInc);
//
//        }
//    }
//
//
//}

