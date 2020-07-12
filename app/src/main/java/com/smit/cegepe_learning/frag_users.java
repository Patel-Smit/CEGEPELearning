package com.smit.cegepe_learning;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;


public class frag_users extends Fragment {
    List<UserIdClass> musers, susers;
    RecyclerView rvUser;
    SearchView searchView;
    private UserAdapter userAdapter, userAdapter1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_users, container, false);
        searchView = v.findViewById(R.id.chat_sv_userSearch);

        rvUser = v.findViewById(R.id.chat_rv_usersList);
        rvUser.setHasFixedSize(true);

        rvUser.setLayoutManager(new LinearLayoutManager(getContext()));

        musers = new ArrayList<>();
        readUsers();
        return v;
    }

    private void readUsers() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                musers.clear();
                for (DataSnapshot Snapshot : dataSnapshot.getChildren()) {

                    UserIdClass user = new UserIdClass(Snapshot.getKey().toString(),
                            Snapshot.child("name").getValue().toString(),
                            Snapshot.child("dob").getValue().toString(),
                            Snapshot.child("city").getValue().toString(),
                            Snapshot.child("email").getValue().toString(),
                            Snapshot.child("password").getValue().toString(),
                            Snapshot.child("usertype").getValue().toString());
                    if (!user.getId().equals(firebaseUser.getUid())) {
                        musers.add(user);
                    }
                }
                userAdapter = new UserAdapter(getContext(), musers, true);
                rvUser.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    searchUser(s);
                    return true;
                }
            });
        }
    }

    private void searchUser(String str) {
        susers = new ArrayList<>();
        for (UserIdClass object : musers) {
            if (object.getName().toLowerCase().contains(str.toLowerCase())) {
                susers.add(object);
            }
        }
        userAdapter1 = new UserAdapter(getContext(), susers, true);
        rvUser.setAdapter(userAdapter1);
    }
}
