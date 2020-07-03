package com.smit.cegepe_learning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<UserIdClass> musers;
    String Lastmessage;
    Boolean isChat;

    public UserAdapter(Context mContext, List<UserIdClass> musers, Boolean isChat) {
        this.musers = musers;
        this.mContext = mContext;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_cardview, parent, false);

        return new UserAdapter.chatHoldeer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println(musers.toString());
        final UserIdClass user = musers.get(position);
        //System.out.println(user.getName());
        TextView tvUser = holder.itemView.findViewById(R.id.chat_tv_user);
        TextView tvUsertype = holder.itemView.findViewById(R.id.chat_tv_usertype);
        ImageView imgFp = holder.itemView.findViewById(R.id.chat_iv_userImage);
        tvUser.setText(user.getName());
        if (user.getUsertype().toLowerCase().equals("user")) {
            tvUsertype.setText("");
        } else {
            tvUsertype.setText("(" + user.getUsertype().toUpperCase() + ")");
        }
        if (user.getUsertype().toLowerCase().equals("admin")) {
            //holder.itemView.findViewById(R.id.chat_iv_userImage).setBackgroundResource(R.drawable.icon_admin);
            imgFp.setImageResource(R.drawable.icon_admin);
            imgFp.setBackgroundResource(R.drawable.rounded_backgroundadmin);
        } else if (user.getUsertype().toLowerCase().equals("teacher")) {
            //holder.itemView.findViewById(R.id.chat_iv_userImage).setBackgroundResource(R.drawable.icon_teacher);
            imgFp.setImageResource(R.drawable.icon_teacher);
            imgFp.setBackgroundResource(R.drawable.rounded_backgroundteacher);
        } else {
            //holder.itemView.findViewById(R.id.chat_iv_userImage).setBackgroundResource(R.drawable.icon_user);
            imgFp.setImageResource(R.drawable.icon_user);
            imgFp.setBackgroundResource(R.drawable.rounded_background);
        }
        TextView lastmessage1 = holder.itemView.findViewById(R.id.chat_tv_lastmessage);
        if (isChat) {
            lastMessage(user.getId(), lastmessage1);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, MessageActivity.class);
                i.putExtra("userChatId", user.getId());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musers.size();
    }

    class chatHoldeer extends RecyclerView.ViewHolder {
        public TextView username, usertype, lastmessage;
        public ImageView userImg;

        public chatHoldeer(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.chat_tv_user);
            usertype = itemView.findViewById(R.id.chat_tv_usertype);
            lastmessage = itemView.findViewById(R.id.chat_tv_lastmessage);
            userImg = itemView.findViewById(R.id.chat_iv_userImage);
        }
    }

    private void lastMessage(final String userid, final TextView lastmessage) {
        Lastmessage = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) || chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())) {
                        Lastmessage = chat.getMessage();
                    }
                }
                switch (Lastmessage) {
                    case "default":
                        lastmessage.setText("No Message");
                        break;
                    default:
                        lastmessage.setText(Lastmessage);
                        break;
                }
                Lastmessage = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
