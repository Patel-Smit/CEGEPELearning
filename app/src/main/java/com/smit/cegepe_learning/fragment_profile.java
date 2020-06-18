package com.smit.cegepe_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class fragment_profile extends Fragment {

    EditText naame, doob, ciity, emmail, paassword;
    Button editProfile;

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

        editProfile = (Button) v.findViewById(R.id.profile_btn_edit);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }
}
