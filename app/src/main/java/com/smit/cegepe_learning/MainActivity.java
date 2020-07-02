package com.smit.cegepe_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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
    public static String userId;
    public static String uusertype;
    //public Menu adminmenu;


    UserHelperClass user;

    TextView headerName, headerUserType, menuTitle;
    LinearLayout llayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuTitle = findViewById(R.id.menu_title);
        menuTitle.setText("Courses");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        llayout = findViewById(R.id.ll_appovalbuttons);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //adminmenu = findViewById(R.id.adminMenu);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_courses()).commit();

        Intent intent = getIntent();
        final String userid = intent.getStringExtra("userID");

        //to access in upload video frag
        userId = intent.getStringExtra("userID");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = new UserHelperClass(dataSnapshot.child(userid).child("name").getValue().toString(),
                        dataSnapshot.child(userid).child("dob").getValue().toString(),
                        dataSnapshot.child(userid).child("city").getValue().toString(),
                        dataSnapshot.child(userid).child("email").getValue().toString(),
                        dataSnapshot.child(userid).child("password").getValue().toString(),
                        dataSnapshot.child(userid).child("usertype").getValue().toString());
                if (userid == null) {
                    System.out.println("User UUID not valid");
                } else {
                    nname = dataSnapshot.child(userid).child("name").getValue().toString();
                    ddob = dataSnapshot.child(userid).child("dob").getValue().toString();
                    ccity = dataSnapshot.child(userid).child("city").getValue().toString();
                    eemail = dataSnapshot.child(userid).child("email").getValue().toString();
                    ppassword = dataSnapshot.child(userid).child("password").getValue().toString();
                    uusertype = dataSnapshot.child(userid).child("usertype").getValue().toString();

                    headerName = findViewById(R.id.header_name);
                    headerUserType = findViewById(R.id.header_userType);

                    Menu navMenu = navigationView.getMenu();

                    if (uusertype.equals("admin")) {
                        navMenu.findItem(R.id.adminMenu).setVisible(true);
                    } else {
                        navMenu.findItem(R.id.adminMenu).setVisible(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void triggerNavBar(View v) {
        headerName.setText(user.getName());
        headerUserType.setText(user.getUsertype().toUpperCase());
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
            case R.id.MyVideos:
                menuTitle.setText("My Videos");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_myvideos()).commit();
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
            case R.id.Chat:
                menuTitle.setText("Chat");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_chat()).commit();
                break;
            case R.id.ApprovalRequests:
                menuTitle.setText("Approval Requests");
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_tutorialapprovals()).commit();
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
