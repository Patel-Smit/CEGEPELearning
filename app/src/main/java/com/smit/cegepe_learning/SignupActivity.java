package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {
    EditText signupName, signupBirthday, signupCity, signupEmail, signupPassword;
    Button btnSignup;
    TextView tvSignin;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextInputLayout dateBox;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        signupName = findViewById(R.id.signup_et_name);
        signupBirthday = findViewById(R.id.signup_et_DOB);
        signupCity = findViewById(R.id.signup_et_city);
        signupEmail = findViewById(R.id.signup_et_email);
        signupPassword = findViewById(R.id.signup_et_password);
        btnSignup = findViewById(R.id.signup_btn_signup);
        tvSignin = findViewById(R.id.signup_tv_signin);

        dateBox = findViewById(R.id.signup_box_dob);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        signupBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "/" + month + "/" + dayOfMonth;
                signupBirthday.setText(date);
            }
        };


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                final String namee = signupName.getText().toString();
                final String birthdayy = signupBirthday.getText().toString();
                final String cityy = signupCity.getText().toString();
                final String emailId = signupEmail.getText().toString();
                final String passwd = signupPassword.getText().toString();
                final String userType = "user";

                if (namee.isEmpty()) {
                    signupName.setError("Please enter your name");
                    signupName.requestFocus();
                } else if (birthdayy.isEmpty()) {
                    signupBirthday.setError("Please enter your Date of Birth");
                    signupBirthday.requestFocus();
                } else if (cityy.isEmpty()) {
                    signupCity.setError("Please enter your city");
                    signupCity.requestFocus();
                } else if (emailId.isEmpty()) {
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
                                System.out.println("!!!" + task.getException().toString() + "!!!!!!!!!!!!!!!!!!!");
                                if (task.getException().getMessage().equals("The given password is invalid. [ Password should be at least 6 characters ]")) {
                                    Toast.makeText(SignupActivity.this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                                    signupPassword.requestFocus();
                                } else if (task.getException().getMessage().equals("The email address is badly formatted.")) {
                                    Toast.makeText(SignupActivity.this, "Email Badly Formatted", Toast.LENGTH_SHORT).show();
                                    signupEmail.requestFocus();
                                } else if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                    Toast.makeText(SignupActivity.this, "Email already registered with another account", Toast.LENGTH_SHORT).show();
                                    signupEmail.requestFocus();
                                } else {
                                    System.out.println("----------" + task.getException().getMessage() + "--------");
                                    Toast.makeText(SignupActivity.this, "Signup Unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
                                }
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

                                String userid = mFirebaseUser.getUid();
                                UserHelperClass helperClass = new UserHelperClass(namee, birthdayy, cityy, emailId, passwd, userType);
                                reference.child(userid).setValue(helperClass);
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

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttoLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intenttoLogin);
            }
        });
    }
}
