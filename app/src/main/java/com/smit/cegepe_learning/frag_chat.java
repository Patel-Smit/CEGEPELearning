package com.smit.cegepe_learning;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;


public class frag_chat extends Fragment {
    RecyclerView rvchat;
    UserAdapter userAdapter;
    private List<UserIdClass> muser;
    private List<ChatList> userlist;
    FirebaseUser fuser;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_cat, container, false);

        rvchat = v.findViewById(R.id.fragchat_rv_chats);
        rvchat.setHasFixedSize(true);
        rvchat.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        userlist = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("chatlist").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatList chatList = snapshot.getValue(ChatList.class);
                    userlist.add(chatList);
                }
                chatList();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        updateToken(FirebaseInstanceId.getInstance().getToken());
        return v;
    }

    private void updateToken(String token) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("tokens");
        Token token1 = new Token(token);
        reference.child(fuser.getUid()).setValue(token1);
    }

    private void chatList() {
        muser = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                muser.clear();
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {
                    UserIdClass user = new UserIdClass(Snapshot.getKey(),
                            Snapshot.child("name").getValue().toString(),
                            Snapshot.child("dob").getValue().toString(),
                            Snapshot.child("city").getValue().toString(),
                            Snapshot.child("email").getValue().toString(),
                            Snapshot.child("password").getValue().toString(),
                            Snapshot.child("usertype").getValue().toString());
                    for (ChatList chatList : userlist) {
                        if (user.getId().equals(chatList.getId())) {
                            muser.add(user);
                        }
                    }
                }
                userAdapter = new UserAdapter(getContext(), muser, true);
                rvchat.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
