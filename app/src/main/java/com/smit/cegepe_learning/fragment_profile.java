package com.smit.cegepe_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_profile extends Fragment {

    EditText naame, doob, ciity, emmail, paassword;
    TextInputLayout nameBox, dobBox, cityBox;
    Button editProfile, updateProfile;
    EditText updateName, updatedob, updatecity;
    TextInputLayout updatenameBox, updatedobBox, updatecityBox;
    private DatabaseReference mDatabaseReference;
    UserHelperClass user;
    FirebaseUser fuser;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        naame = (EditText) v.findViewById(R.id.profile_et_name);
        naame.setText(MainActivity.nname);
        doob = (EditText) v.findViewById(R.id.profile_et_DOB);
        doob.setText(MainActivity.ddob);
        ciity = (EditText) v.findViewById(R.id.profile_et_city);
        ciity.setText(MainActivity.ccity);
        emmail = (EditText) v.findViewById(R.id.profile_et_email);
        emmail.setText(MainActivity.eemail);
        paassword = (EditText) v.findViewById(R.id.profile_et_password);
        paassword.setText(MainActivity.ppassword);

        nameBox = (TextInputLayout) v.findViewById(R.id.profile_nameBox);
        dobBox = (TextInputLayout) v.findViewById(R.id.profile_dobBox);
        cityBox = (TextInputLayout) v.findViewById(R.id.profile_cityBox);

        //Profile Update
        updateName = (EditText) v.findViewById(R.id.profile_et_updatename);
        updateName.setText(MainActivity.nname);
        updatedob = (EditText) v.findViewById(R.id.profile_et_updateDOB);
        updatedob.setText(MainActivity.ddob);
        updatecity = (EditText) v.findViewById(R.id.profile_et_updatecity);
        updatecity.setText(MainActivity.ccity);

        updatenameBox = (TextInputLayout) v.findViewById(R.id.profile_updatenameBox);
        updatedobBox = (TextInputLayout) v.findViewById(R.id.profile_updateDOBBox);
        updatecityBox = (TextInputLayout) v.findViewById(R.id.profile_updatecitybox);

        editProfile = (Button) v.findViewById(R.id.profile_btn_edit);
        updateProfile = (Button) v.findViewById(R.id.profile_btn_update);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naame.setVisibility(View.GONE);
                nameBox.setVisibility(View.GONE);
                doob.setVisibility(View.GONE);
                dobBox.setVisibility(View.GONE);
                ciity.setVisibility(View.GONE);
                cityBox.setVisibility(View.GONE);

                updatenameBox.setVisibility(View.VISIBLE);
                updateName.setVisibility(View.VISIBLE);
                updatedobBox.setVisibility(View.VISIBLE);
                updatedob.setVisibility(View.VISIBLE);
                updatecityBox.setVisibility(View.VISIBLE);
                updatecity.setVisibility(View.VISIBLE);

                editProfile.setVisibility(View.GONE);
                updateProfile.setVisibility(View.VISIBLE);
            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updatenameBox.setVisibility(View.GONE);
                updateName.setVisibility(View.GONE);
                updatedobBox.setVisibility(View.GONE);
                updatedob.setVisibility(View.GONE);
                updatecityBox.setVisibility(View.GONE);
                updatecity.setVisibility(View.GONE);

                fuser = FirebaseAuth.getInstance().getCurrentUser();
                final String userid = fuser.getUid();

                mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        user = new UserHelperClass(dataSnapshot.child(userid).child("name").getValue().toString(),
                                dataSnapshot.child(userid).child("dob").getValue().toString(),
                                dataSnapshot.child(userid).child("city").getValue().toString(),
                                dataSnapshot.child(userid).child("email").getValue().toString(),
                                dataSnapshot.child(userid).child("password").getValue().toString(),
                                dataSnapshot.child(userid).child("usertype").getValue().toString());
                        if (userid == null) {
                            System.out.println("User UUID not valid");
                        } else {
                            if (naame.getText().toString() != updateName.getText().toString()) {
                                mDatabaseReference.child(userid).child("name").setValue(updateName.getText().toString());
                                naame.setText(MainActivity.nname);
                            }
                            if (doob.getText().toString() != updatedob.getText().toString()) {
                                mDatabaseReference.child(userid).child("dob").setValue(updatedob.getText().toString());
                                doob.setText(MainActivity.ddob);
                            }
                            if (ciity.getText().toString() != updatecity.getText().toString()) {
                                mDatabaseReference.child(userid).child("city").setValue(updatecity.getText().toString());
                                ciity.setText(MainActivity.ccity);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                naame.setVisibility(View.VISIBLE);
                nameBox.setVisibility(View.VISIBLE);
                doob.setVisibility(View.VISIBLE);
                dobBox.setVisibility(View.VISIBLE);
                ciity.setVisibility(View.VISIBLE);
                cityBox.setVisibility(View.VISIBLE);

                updateProfile.setVisibility(View.GONE);
                editProfile.setVisibility(View.VISIBLE);
            }
        });
        return v;
    }
}
