package com.smit.cegepe_learning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PostVideoAdapter extends FirebaseRecyclerAdapter<PostVideos, PostVideoAdapter.PostVideoViewHolder> {


    public PostVideoAdapter(@NonNull FirebaseRecyclerOptions<PostVideos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostVideoViewHolder holder, int position, @NonNull PostVideos postVideos) {
        holder.categoryName.setText(postVideos.getCategoryName());
    }

    @NonNull
    @Override
    public PostVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coursecategory_cardview, parent, false);
        return new PostVideoViewHolder(view);
    }

    class PostVideoViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        public PostVideoViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.courses_categotyName);
        }
    }
}
