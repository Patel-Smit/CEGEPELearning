package com.smit.cegepe_learning;

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

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextView signUp, forgotPassword;
    TextInputLayout emailHolder, passwordHolder;
    EditText loginEmail, loginPassword;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailHolder = findViewById(R.id.login_et_eemail);
        passwordHolder = findViewById(R.id.login_et_ppassword);

        loginEmail = findViewById(R.id.login_et_email);
        loginPassword = findViewById(R.id.login_et_password);
        loginBtn = findViewById(R.id.login_btn_login);

        signUp = findViewById(R.id.login_tv_signUP);
        forgotPassword = findViewById(R.id.login_tv_forgotPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake_animation);
                String emailId = loginEmail.getText().toString();
                String passwd = loginPassword.getText().toString();
                if (emailId.equals("admin") && passwd.equals("password")) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    
                    //Set outline red
                    emailHolder.setBoxStrokeColor(getResources().getColor(R.color.colorRed));
                    passwordHolder.setBoxStrokeColor(getResources().getColor(R.color.colorRed));

                    //Animate textholders
                    loginEmail.startAnimation(shake);
                    emailHolder.startAnimation(shake);
                    loginPassword.startAnimation(shake);
                    passwordHolder.startAnimation(shake);
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
                Toast.makeText(LoginActivity.this, "Activity under construction!", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(LoginActivity.this, MainActivity.class);
                //startActivity(i);
            }
        });
    }
}
