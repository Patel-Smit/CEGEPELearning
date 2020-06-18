package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;

    private DatabaseReference mDatabaseReference;
    public static String nname;
    public static String ddob;
    public static String ccity;
    public static String eemail;
    public static String ppassword;

    TextView headerName, menuTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuTitle = findViewById(R.id.menu_title);
        menuTitle.setText("Courses");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_courses()).commit();

        Intent intent = getIntent();
        final String userid = intent.getStringExtra("userID");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (userid == null) {
                    System.out.println("User UUID not valid");
                } else {
                    nname = dataSnapshot.child(userid).child("name").getValue().toString();
                    ddob = dataSnapshot.child(userid).child("dob").getValue().toString();
                    ccity = dataSnapshot.child(userid).child("city").getValue().toString();
                    eemail = dataSnapshot.child(userid).child("email").getValue().toString();
                    ppassword = dataSnapshot.child(userid).child("password").getValue().toString();

                    headerName = findViewById(R.id.header_name);
                    headerName.setText(nname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void triggerNavBar(View v) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuTitle = findViewById(R.id.menu_title);
        switch (menuItem.getItemId()) {
            case R.id.Courses:
                menuTitle.setText("Courses");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_courses()).commit();
                break;
            case R.id.UploadCourse:
                menuTitle.setText("Upload Course");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_uploadcourse()).commit();
                break;
            case R.id.Profile:
                menuTitle.setText("Profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_profile()).commit();
                break;
            case R.id.Settings:
                menuTitle.setText("Settings");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_settings()).commit();
                break;
            case R.id.About:
                menuTitle.setText("About");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_about()).commit();
                break;
            case R.id.SignOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
