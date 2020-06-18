package com.smit.cegepe_learning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class fragment_uploadcourse extends Fragment {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText videoTitle, videoDescription, videoCategory, videoLink;
    Button uploadTutorial;
    FirebaseAuth mFirebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_uploadcourse, container, false);

        videoTitle = (EditText) v.findViewById(R.id.upload_videoTitle);
        videoDescription = (EditText) v.findViewById(R.id.upload_videoDescription);
        videoCategory = (EditText) v.findViewById(R.id.upload_videoCategory);
        videoLink = (EditText) v.findViewById(R.id.upload_videoLink);
        uploadTutorial = (Button) v.findViewById(R.id.upload_uploadTutorial);


        uploadTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("videos");

                final String UserID = MainActivity.userId;

                final String vTitle = videoTitle.getText().toString();
                final String vDescription = videoDescription.getText().toString();
                final String vCategory = videoCategory.getText().toString();
                final String vLink = videoLink.getText().toString();
                final String vUser = UserID;
                final String vApproved = null;
                final String vId = UUID.randomUUID().toString();

                UploadVideoHelperClass uploadVideoHelperClass = new UploadVideoHelperClass(vTitle, vDescription, vCategory, vLink, vUser, vApproved, vId);
                reference.child(vId).setValue(uploadVideoHelperClass);
                Toast.makeText(getActivity().getApplication(), "Upload Successful, Video is under Approval", Toast.LENGTH_SHORT).show();
                videoTitle.setText(null);
                videoDescription.setText(null);
                videoCategory.setText(null);
                videoLink.setText(null);
            }
        });

        return v;
    }
}
