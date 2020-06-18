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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    public void triggerNavBar(View v) {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Courses:
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_courses()).commit();
                break;
            case R.id.UploadCourse:
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_uploadcourse()).commit();
                break;
            case R.id.Profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_profile()).commit();
                break;
            case R.id.Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.back, new fragment_settings()).commit();
                break;
            case R.id.About:
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
