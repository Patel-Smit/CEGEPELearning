package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btnVerify;
    EditText fpemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();
        btnVerify = findViewById(R.id.fp_btn_verify);
        fpemail = findViewById(R.id.fp_et_email);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = fpemail.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                startActivity(i);
                            } else {
                                if (task.getException().getMessage().equals("The email address is badly formatted.")) {
                                    Toast.makeText(ForgotPasswordActivity.this, "Email Badly Formatted!", Toast.LENGTH_SHORT).show();
                                    fpemail.requestFocus();
                                } else if (task.getException().getMessage().equals("There is no user record corresponding to this identifier. The user may have been deleted.")) {
                                    Toast.makeText(ForgotPasswordActivity.this, "User not exists!", Toast.LENGTH_SHORT).show();
                                    fpemail.requestFocus();
                                } else {
                                    String errMessage = task.getException().getMessage();
                                    Toast.makeText(ForgotPasswordActivity.this, "Error: " + errMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}

