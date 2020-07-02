package com.smit.cegepe_learning;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class PostVideoAdapter extends FirebaseRecyclerAdapter<PostVideos, PostVideoAdapter.PostVideoViewHolder> {
    public PostVideoAdapter(@NonNull FirebaseRecyclerOptions<PostVideos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostVideoViewHolder holder, int position, @NonNull PostVideos postVideos) {
        final String couCatName = postVideos.getCategoryName();
        final String couCatLink = postVideos.getCategoryImageLink();
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(couCatName);
                Intent myIntent = new Intent(view.getContext(), CourseVideoFeedActivity.class);
                myIntent.putExtra("catregoryValue", couCatName);
                myIntent.putExtra("catregoryImageLink", couCatLink);
                view.getContext().startActivity(myIntent);
            }
        });
        holder.categoryName.setText(postVideos.getCategoryName());
        Picasso.get().load(postVideos.getCategoryImageLink()).into(holder.categoryImageLink);
    }

    @NonNull
    @Override
    public PostVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coursecategory_cardview, parent, false);
        return new PostVideoViewHolder(view);
    }

    class PostVideoViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImageLink;
        View v;

        public PostVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.courses_categotyName);
            categoryImageLink = itemView.findViewById(R.id.iv_categoryLogo);
            v = itemView;
        }
    }
}
