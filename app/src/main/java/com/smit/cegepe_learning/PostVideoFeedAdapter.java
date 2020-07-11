package com.smit.cegepe_learning;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PostVideoFeedAdapter extends FirebaseRecyclerAdapter<PostVideoFeed, PostVideoFeedAdapter.PostVideoFeedViewHolder> {

    DatabaseReference databaseReference, databaseReference1;
    String userName;

    public PostVideoFeedAdapter(@NonNull FirebaseRecyclerOptions<PostVideoFeed> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostVideoFeedViewHolder holder, int position, @NonNull final PostVideoFeed postVideoFeed) {
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
        holder.approvalStatus.setText(postVideoFeed.getApprovalStatus());
        Picasso.get().load(postVideoFeed.getThumbnailLink()).into(holder.img);
        //  holder.user.setText(user1.getName());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.title.setMaxLines(2);
            }
        });

        holder.title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.title.setMaxLines(5);
                return false;
            }
        });

        final String couLink = postVideoFeed.getLink();
        final String couTitle = postVideoFeed.getTitle();
        final String couDescription = postVideoFeed.getDescription();
        final String couCreator = postVideoFeed.getUser();
        final String couVideoId = postVideoFeed.getId();
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent myIntent = new Intent(view.getContext(), VideoPlayerActivity.class);
                myIntent.putExtra("linkValue", couLink);
                myIntent.putExtra("titleValue", couTitle);
                myIntent.putExtra("descriptionValue", couDescription);
                myIntent.putExtra("userIdValue", couCreator);
                myIntent.putExtra("videoIdValue", couVideoId);

                databaseReference1 = FirebaseDatabase.getInstance().getReference().child("users").child(postVideoFeed.getUser());

                databaseReference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userName = dataSnapshot.child("name").getValue().toString();
                        Intent myIntent = new Intent(view.getContext(), VideoPlayerActivity.class);
                        myIntent.putExtra("linkValue", couLink);
                        myIntent.putExtra("titleValue", couTitle);
                        myIntent.putExtra("descriptionValue", couDescription);
                        System.out.println(userName + " ehehehhehehe");
                        myIntent.putExtra("creatorValue", userName);
                        myIntent.putExtra("userIdValue", couCreator);
                        myIntent.putExtra("videoIdValue", couVideoId);
                        view.getContext().startActivity(myIntent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference parentref = FirebaseDatabase.getInstance().getReference().child("videos").child(postVideoFeed.getCategory()).child(postVideoFeed.getId());
                parentref.child("approvalStatus").setValue("1");
            }
        });
        holder.notapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference parentref = FirebaseDatabase.getInstance().getReference().child("videos").child(postVideoFeed.getCategory()).child(postVideoFeed.getId());
                parentref.child("approvalStatus").setValue("-1");
            }
        });
    }

    @NonNull
    @Override
    public PostVideoFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (MainActivity.uusertype.equals("admin")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coursefeed_admin_cardview, parent, false);
            return new PostVideoFeedViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coursefeed_cardview, parent, false);
            return new PostVideoFeedViewHolder(view);
        }
    }

    class PostVideoFeedViewHolder extends RecyclerView.ViewHolder {

        TextView id, title, description, category, link, user, approvalStatus;
        Button approve, notapprove;
        ImageView img;
        View v;

        public PostVideoFeedViewHolder(@NonNull View itemView) {

            super(itemView);
            id = itemView.findViewById(R.id.feed_id);
            title = itemView.findViewById(R.id.feed_title);
            description = itemView.findViewById(R.id.feed_description);
            category = itemView.findViewById(R.id.feed_category);
            link = itemView.findViewById(R.id.feed_link);
            user = itemView.findViewById(R.id.feed_user);
            approvalStatus = itemView.findViewById(R.id.feed_status);
            approve = itemView.findViewById(R.id.btn_approved);
            notapprove = itemView.findViewById(R.id.btn_notApproved);
            img = itemView.findViewById(R.id.iv_cardThumbnail);
            v = itemView;
        }
    }
}
