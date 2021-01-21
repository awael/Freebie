package com.aucegypt.freebie;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout mainGrid;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toolbar = findViewById(R.id.homepage_toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }



    private void setSingleEvent(GridLayout mainGrid) {

        for(int i = 0; i<mainGrid.getChildCount();i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener((view) -> {
                //Create new intent here
//                Toast.makeText(HomePage.this,"Clicked Index "+finalI,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this, ItemActivity.class);
                intent.putExtra("widgetIndex",finalI);
                startActivity(intent);
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        firebaseAuth.getInstance();

        switch (id){
            case R.id.profileSideMenu:
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
            case R.id.trackSideMenu:
                intent = new Intent(this, track.class);
               startActivity(intent);
                break;
            case R.id.aboutusSideMenu:
                intent = new Intent(this, aboutStatic.class);
                startActivity(intent);
                break;
            case R.id.signoutSideMenu:
                firebaseAuth.signOut();
                startActivity(new Intent(HomePage.this, MainActivity.class));
                finish();
//                intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}