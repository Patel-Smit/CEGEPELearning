package com.smit.cegepe_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class fragment_uploadcourse extends Fragment {

    FirebaseDatabase rootNode;
    DatabaseReference reference, referenceCategory;
    EditText videoTitle, videoDescription, videoCategory, videoLink;
    Button uploadTutorial;
    Spinner spinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_uploadcourse, container, false);

        videoTitle = (EditText) v.findViewById(R.id.upload_videoTitle);
        videoDescription = (EditText) v.findViewById(R.id.upload_videoDescription);
        //videoCategory = (EditText) v.findViewById(R.id.upload_videoCategory);
        videoLink = (EditText) v.findViewById(R.id.upload_videoLink);
        uploadTutorial = (Button) v.findViewById(R.id.upload_uploadTutorial);

        spinner = (Spinner) v.findViewById(R.id.upload_sp_videoCategory);

        referenceCategory = FirebaseDatabase.getInstance().getReference("videocategories");
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();


        uploadTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String categoryName = spinner.getSelectedItem().toString();
                System.out.println(categoryName);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("videos").child(categoryName);

                final String UserID = MainActivity.userId;

                final String vTitle = videoTitle.getText().toString();
                final String vDescription = videoDescription.getText().toString();
                final String vCategory = categoryName;
                final String vLink = videoLink.getText().toString();
                final String vUser = UserID;
                final String vApproved = null;
                final String vId = UUID.randomUUID().toString();

                spinnerDataList.clear();
                adapter.notifyDataSetChanged();
                UploadVideoHelperClass uploadVideoHelperClass = new UploadVideoHelperClass(vTitle, vDescription, vCategory, vLink, vUser, vApproved, vId);
                reference.child(vId).setValue(uploadVideoHelperClass);
                Toast.makeText(getActivity().getApplication(), "Upload Successful, Video is under Approval", Toast.LENGTH_SHORT).show();

                //clean edittext
                videoTitle.setText(null);
                videoDescription.setText(null);
                //videoCategory.setText(null);
                videoLink.setText(null);
            }
        });

        return v;
    }

    public void retrieveData() {
        listener = referenceCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerDataList.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    spinnerDataList.add(item.child("categoryName").getValue().toString());
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
