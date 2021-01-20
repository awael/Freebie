package com.aucegypt.freebie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
public class ngoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_view);
     /*   View bottomNavigation = findViewById(R.id.fragment);
       // BottomNavigationView.OnNavigationItemSelectedListener
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.anav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ngoView_public:
                        //Toast.makeText(this, "Recents", Toast.LENGTH_SHORT).show();
                        openFragment(ngoView_public.newInstance("", ""));
                        break;
                    case R.id.ngoView_private:
                        //Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });*/

    }
}