package com.smit.cegepe_learning;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostVideoFeedAdapter extends FirebaseRecyclerAdapter<PostVideoFeed, PostVideoFeedAdapter.PostVideoFeedViewHolder> {

    DatabaseReference databaseReference;
    String userName;
    UserHelperClass user1;

    public PostVideoFeedAdapter(@NonNull FirebaseRecyclerOptions<PostVideoFeed> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostVideoFeedViewHolder holder, int position, @NonNull PostVideoFeed postVideoFeed) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(postVideoFeed.getUser());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.child("name"));
                holder.user.setText(dataSnapshot.child("name").getValue().toString());
                // userName = dataSnapshot.child("name").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        holder.id.setText(postVideoFeed.getId());
        holder.title.setText(postVideoFeed.getTitle());
        holder.description.setText(postVideoFeed.getDescription());
        holder.category.setText(postVideoFeed.getCategory());
        holder.link.setText(postVideoFeed.getLink());
        //  holder.user.setText(user1.getName());

        final String couLink = postVideoFeed.getLink();
        final String couTitle = postVideoFeed.getTitle();
        final String couDescription = postVideoFeed.getDescription();
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), VideoPlayerActivity.class);
                myIntent.putExtra("linkValue", couLink);
                myIntent.putExtra("titleValue", couTitle);
                myIntent.putExtra("descriptionValue", couDescription);
                view.getContext().startActivity(myIntent);
            }
        });
    }

    @NonNull
    @Override
    public PostVideoFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coursefeed_cardview, parent, false);
        return new PostVideoFeedViewHolder(view);
    }

    class PostVideoFeedViewHolder extends RecyclerView.ViewHolder {

        TextView id, title, description, category, link, user;
        View v;

        public PostVideoFeedViewHolder(@NonNull View itemView) {

            super(itemView);
            id = itemView.findViewById(R.id.feed_id);
            title = itemView.findViewById(R.id.feed_title);
            description = itemView.findViewById(R.id.feed_description);
            category = itemView.findViewById(R.id.feed_category);
            link = itemView.findViewById(R.id.feed_link);
            user = itemView.findViewById(R.id.feed_user);
            v = itemView;
        }
    }
}
