package com.aucegypt.freebie;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Homepage";

    ProgressBar progressBar1,progressBar2,progressBar3,progressBar4,progressBar5,progressBar6,progressBar7,
            progressBar8,progressBar9,progressBar10,progressBar11,progressBar12,progressBar13,progressBar14;

    GridLayout mainGrid;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();

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


        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2= (ProgressBar) findViewById(R.id.progressBar2);
        progressBar3= (ProgressBar) findViewById(R.id.progressBar3);
        progressBar4= (ProgressBar) findViewById(R.id.progressBar4);
        progressBar5= (ProgressBar) findViewById(R.id.progressBar5);
        progressBar6= (ProgressBar) findViewById(R.id.progressBar6);
        progressBar7= (ProgressBar) findViewById(R.id.progressBar7);
        progressBar8= (ProgressBar) findViewById(R.id.progressBar8);
        progressBar9 = (ProgressBar) findViewById(R.id.progressBar9);
        progressBar10 = (ProgressBar) findViewById(R.id.progressBar10);
        progressBar11 = (ProgressBar) findViewById(R.id.progressBar11);
        progressBar12 = (ProgressBar) findViewById(R.id.progressBar12);
        progressBar13 = (ProgressBar) findViewById(R.id.progressBar13);
        progressBar14 = (ProgressBar) findViewById(R.id.progressBar14);


        CollectionReference donRef = db.collection("Donations");
        Query donQuery = donRef.whereEqualTo("category", "Clothes");
        donQuery.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count = documentSnapshot.size();
                System.out.println(count + "Clothes");
                progressBar1.setProgress(Math.round(((float)count/10)*100));

            }
            else{
                System.out.println("Failed");
            }
        });



        Query donQuery2 = donRef.whereEqualTo("category", "Food");
        donQuery2.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count2 = documentSnapshot.size();
                System.out.println(count2 + "Food");
                progressBar2.setProgress(Math.round(((float)count2/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery3 = donRef.whereEqualTo("category", "Blankets");
        donQuery3.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count3 = documentSnapshot.size();
                System.out.println(count3 + "Blankets");
                progressBar3.setProgress(Math.round(((float)count3/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery4 = donRef.whereEqualTo("category", "First-Aid");
        donQuery4.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count4 = documentSnapshot.size();
                System.out.println(count4 + "First-Aid");
                progressBar4.setProgress(Math.round(((float)count4/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery5 = donRef.whereEqualTo("category", "Furniture");
        donQuery5.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count5 = documentSnapshot.size();
                System.out.println(count5 + "Furniture");
                progressBar5.setProgress(Math.round(((float)count5/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery6 = donRef.whereEqualTo("category", "Gadgets");
        donQuery6.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count6 = documentSnapshot.size();
                System.out.println(count6 + "Gadgets");
                progressBar6.setProgress(Math.round(((float)count6/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery7 = donRef.whereEqualTo("category", "Medicine");
        donQuery7.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count7 = documentSnapshot.size();
                System.out.println(count7 + "Medicine");
                progressBar7.setProgress(Math.round(((float)count7/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery8 = donRef.whereEqualTo("category", "Bags");
        donQuery8.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count8 = documentSnapshot.size();
                System.out.println(count8 + "Bags");
                progressBar8.setProgress(Math.round(((float)count8/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery9 = donRef.whereEqualTo("category", "Sanitary");
        donQuery9.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count9 = documentSnapshot.size();
                System.out.println(count9 + "Sanitary");
                progressBar9.setProgress(Math.round(((float)count9/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery10 = donRef.whereEqualTo("category", "Supplies");
        donQuery10.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count10 = documentSnapshot.size();
                System.out.println(count10 + "Supplies" );
                progressBar10.setProgress(Math.round(((float)count10/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery11 = donRef.whereEqualTo("category", "Toys");
        donQuery11.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count11 = documentSnapshot.size();
                System.out.println(count11 + "Toys");
                progressBar11.setProgress(Math.round(((float)count11/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery12 = donRef.whereEqualTo("category", "Utensils");
        donQuery12.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count12 = documentSnapshot.size();
                System.out.println(count12 + "Utensils");
                progressBar12.setProgress(Math.round(((float)count12/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery13 = donRef.whereEqualTo("category", "Sports");
        donQuery13.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count13 = documentSnapshot.size();
                System.out.println(count13 + "Sports");
                progressBar13.setProgress(Math.round(((float)count13/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

        Query donQuery14 = donRef.whereEqualTo("category", "Others");
        donQuery14.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot documentSnapshot =  task.getResult();
                int count14 = documentSnapshot.size();
                System.out.println(count14 + "Others");
                progressBar14.setProgress(Math.round(((float)count14/10)*100));


            }
            else{
                System.out.println("Failed");
            }
        });

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
//        firebaseAuth.getInstance();

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
                if (firebaseAuth != null){
                    firebaseAuth.signOut();
                    startActivity(new Intent(HomePage.this, MainActivity.class));
                    finish();
                }
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