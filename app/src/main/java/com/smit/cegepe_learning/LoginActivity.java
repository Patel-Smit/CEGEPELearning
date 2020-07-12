package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView signUp, forgotPassword;
    TextInputLayout emailHolder, passwordHolder;
    EditText loginEmail, loginPassword;
    Button loginBtn;
    FirebaseAuth mFirebaseAuth;
    Animation shake;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailHolder = findViewById(R.id.login_et_eemail);
        passwordHolder = findViewById(R.id.login_et_ppassword);

        loginEmail = findViewById(R.id.login_et_email);
        loginPassword = findViewById(R.id.login_et_password);
        loginBtn = findViewById(R.id.login_btn_login);
        mFirebaseAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.login_tv_signUP);
        forgotPassword = findViewById(R.id.login_tv_forgotPassword);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    if (!mFirebaseAuth.getCurrentUser().isEmailVerified()) {
                        Toast.makeText(LoginActivity.this, "Please verify your email before signing in", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("userID", mFirebaseAuth.getCurrentUser().getUid());
                        startActivity(i);
                    }
                } else {

                }
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shake = AnimationUtils.loadAnimation(getApplication(), R.anim.shake_animation);
                final String emailId = loginEmail.getText().toString();
                String passwd = loginPassword.getText().toString();
                if (emailId.isEmpty()) {
                    loginEmail.setError("Please enter email address");
                    loginEmail.requestFocus();
                    loginEmail.startAnimation(shake);
                    emailHolder.startAnimation(shake);
                } else if (passwd.isEmpty()) {
                    loginPassword.setError("Please enter your password");
                    loginPassword.requestFocus();
                    loginPassword.startAnimation(shake);
                    passwordHolder.startAnimation(shake);
                } else if (emailId.isEmpty() && passwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    loginEmail.startAnimation(shake);
                    emailHolder.startAnimation(shake);
                    loginPassword.startAnimation(shake);
                    passwordHolder.startAnimation(shake);
                } else if (!(emailId.isEmpty() && passwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(emailId, passwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                if (task.getException().getMessage().equals("The password is invalid or the user does not have a password.")) {
                                    passwordHolder.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
                                    loginPassword.startAnimation(shake);
                                    passwordHolder.startAnimation(shake);
                                    loginPassword.requestFocus();
                                    Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                } else if (task.getException().getMessage().equals("There is no user record corresponding to this identifier. The user may have been deleted.")) {
                                    emailHolder.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
                                    loginEmail.startAnimation(shake);
                                    emailHolder.startAnimation(shake);
                                    loginEmail.requestFocus();
                                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                                } else if (task.getException().getMessage().equals("The email address is badly formatted.")) {
                                    emailHolder.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
                                    loginEmail.startAnimation(shake);
                                    emailHolder.startAnimation(shake);
                                    loginEmail.requestFocus();
                                    Toast.makeText(LoginActivity.this, "Email Formatted Badly", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                if (!mFirebaseAuth.getCurrentUser().isEmailVerified()) {
                                    Toast.makeText(LoginActivity.this, "Please verify your email before signing in", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent intentToHome = new Intent(LoginActivity.this, MainActivity.class);
                                    intentToHome.putExtra("userID", mFirebaseAuth.getCurrentUser().getUid());
                                    startActivity(intentToHome);
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
    }

    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
