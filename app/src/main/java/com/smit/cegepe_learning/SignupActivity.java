package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    EditText signupEmail, signupPassword;
    Button btnSignup;
    TextView tvSignin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signup_et_email);
        signupPassword = findViewById(R.id.signup_et_password);
        btnSignup = findViewById(R.id.signup_btn_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = signupEmail.getText().toString();
                String passwd = signupPassword.getText().toString();

                if (emailId.isEmpty()) {
                    signupEmail.setError("Please enter email address");
                    signupEmail.requestFocus();
                } else if (passwd.isEmpty()) {
                    signupPassword.setError("Please enter your password");
                    signupPassword.requestFocus();
                } else if (emailId.isEmpty() && passwd.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(emailId.isEmpty() && passwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailId, passwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Signup Unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
                            } else {
                                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                                mFirebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignupActivity.this, "Registration successful, check your email to verify your account.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignupActivity.this, "Error Occurred!!!", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
