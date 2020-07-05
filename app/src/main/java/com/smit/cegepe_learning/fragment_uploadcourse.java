package com.smit.cegepe_learning;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class fragment_uploadcourse extends Fragment {

    FirebaseDatabase rootNode;
    DatabaseReference reference, referenceCategory;
    EditText videoTitle, videoDescription, videoCategory, videoLink;
    Button uploadTutorial, chooseThumbnail;
    Spinner spinner;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    public String downloadLink;

    private static final int PikImageRequest = 1;
    private ProgressBar mprogressBar;
    private Uri mImageURI;
    private ImageView thumbnailView;

    private StorageReference mStoreageRef;
    private DatabaseReference mDatabaseRef;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_uploadcourse, container, false);

        videoTitle = (EditText) v.findViewById(R.id.upload_videoTitle);
        videoDescription = (EditText) v.findViewById(R.id.upload_videoDescription);
        //videoCategory = (EditText) v.findViewById(R.id.upload_videoCategory);
        videoLink = (EditText) v.findViewById(R.id.upload_videoLink);
        uploadTutorial = (Button) v.findViewById(R.id.upload_uploadTutorial);

        SharedPreferences prefsTheme = getActivity().getSharedPreferences("saveTheme", getContext().MODE_PRIVATE);
        Boolean restoredTheme = prefsTheme.getBoolean("valueTheme", true);
        if (restoredTheme) {
            uploadTutorial.setBackgroundResource(R.drawable.gradient_orange);
        }

        spinner = (Spinner) v.findViewById(R.id.upload_sp_videoCategory);
        mStoreageRef = FirebaseStorage.getInstance().getReference("uploads");


        referenceCategory = FirebaseDatabase.getInstance().getReference("videocategories");
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerDataList);
        spinner.setAdapter(adapter);
        retrieveData();

        chooseThumbnail = v.findViewById(R.id.upload_uploadThumbnail);
        mprogressBar = v.findViewById(R.id.upload_progressbar);
        thumbnailView = v.findViewById(R.id.upload_iv_thumbnail);

        chooseThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        uploadTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadfile();
                String categoryName = spinner.getSelectedItem().toString();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("videos").child(categoryName);

                final String UserID = MainActivity.userId;

                final String vTitle = videoTitle.getText().toString();
                final String vDescription = videoDescription.getText().toString();
                final String vCategory = categoryName;
                final String vLink = videoLink.getText().toString();
                final String vUser = UserID;
                final String vApproved = "0";
                final String vId = UUID.randomUUID().toString();
                if (vTitle.isEmpty()) {
                    videoTitle.setError("Please enter Title");
                    videoTitle.requestFocus();
                } else if (vDescription.isEmpty()) {
                    videoDescription.setError("Please enter Description");
                    videoDescription.requestFocus();
                } else if (vLink.isEmpty()) {
                    videoLink.setError("Please enter Link");
                    videoLink.requestFocus();
                } else {
                    spinnerDataList.clear();
                    adapter.notifyDataSetChanged();
                    UploadVideoHelperClass uploadVideoHelperClass = new UploadVideoHelperClass(vTitle, vDescription, vCategory, vLink, vUser, vApproved, vId, "https://firebasestorage.googleapis.com/v0/b/cegep-e-learning.appspot.com/o/CourseLogos%2FPresentationWallpaper.jpg?alt=media&token=ff12c899-2867-4b39-9403-d27e9dd9457d");
                    reference.child(vId).setValue(uploadVideoHelperClass);
                    Toast.makeText(getActivity().getApplication(), "Upload Successful, Video is under Approval", Toast.LENGTH_SHORT).show();
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("videos").child(categoryName).child(vId);
                    uploadfile();

                    videoTitle.setText(null);
                    videoDescription.setText(null);
                    videoLink.setText(null);
                    thumbnailView.setImageDrawable(null);
                }
            }

            private String getFileExtension(Uri uri) {
                ContentResolver cR = getContext().getContentResolver();
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                return mime.getExtensionFromMimeType(cR.getType(uri));
            }

            private String[] uploadfile() {
                final String[] dwString = new String[1];
                if (mImageURI != null) {
                    StorageReference fileReference = mStoreageRef.child(System.currentTimeMillis()
                            + "." + getFileExtension(mImageURI));
                    fileReference.putFile(mImageURI)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            mprogressBar.setProgress(0);
                                        }
                                    }, 500);
                                    if (taskSnapshot.getMetadata() != null) {
                                        if (taskSnapshot.getMetadata().getReference() != null) {
                                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String imageUrl = uri.toString();
                                                    mDatabaseRef.child("thumbnailLink").setValue(imageUrl);
                                                }
                                            });
                                        }
                                    }
                                    Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_LONG).show();
                                    //   UploadUri upload = new UploadUri(taskSnapshot.getDownloadUrl().toString());
                                    //   String uploadId = mDatabaseRef.push().getKey();

                                    //   mDatabaseRef.child("thumbnailLink").setValue(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                                    //   dwString[0] =  taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                    mprogressBar.setProgress((int) progress);
                                }
                            });

                } else {
                    Toast.makeText(getContext(), "No file selected", Toast.LENGTH_SHORT).show();
                }

                return dwString;
            }
        });
        return v;
    }


    private void openFileChooser() {
        Intent inte = new Intent();
        inte.setType("image/*");
        inte.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(inte, PikImageRequest);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PikImageRequest && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageURI = data.getData();
            thumbnailView.setImageURI(mImageURI);
        }
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
