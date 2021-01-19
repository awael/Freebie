package com.aucegypt.freebie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {
    Button donate;
    Button editAddress;


    RelativeLayout relativeLayout;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        int widgetNum = intent.getExtras().getInt("widgetIndex");
        String uri;

        Spinner mySpinner = (Spinner)findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ItemActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ngos));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        switch (widgetNum){
            case 0:
                uri = "@drawable/clothes";
                break;
            case 1:
                uri = "@drawable/foodicon";
                ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(ItemActivity.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.foodngos));
                myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter(myAdapter2);
                break;
            case 2:
                uri = "@drawable/blankets";
                break;
            case 3:
                uri = "@drawable/firstaid";
                break;
            case 4:
                uri = "@drawable/furniture";
                break;
            case 5:
                uri = "@drawable/gadgets";
                break;
            case 6:
                uri = "@drawable/medicine";
                break;
            case 7:
                uri = "@drawable/bags";
                break;
            case 8:
                uri = "@drawable/sanitary";
                break;
            case 9:
                uri = "@drawable/supplies";
                break;
            case 10:
                uri = "@drawable/toys";
                break;
            case 11:
                uri = "@drawable/utensils";
                break;
            case 12:
                uri = "@drawable/sports";
                break;
            case 13:
                uri = "@drawable/others";
                break;
            default:
                uri = "@drawable/others";
        }
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        ImageView imageview = (ImageView) findViewById(R.id.img_logo);
        Drawable res = getResources().getDrawable(imageResource);
        imageview.setImageDrawable(res);



        relativeLayout = findViewById(R.id.rel1);
        handler.postDelayed(runnable, 500);

        editAddress=(Button)findViewById(R.id.addressButton);
        editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ItemActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        donate=(Button)findViewById(R.id.donateButton);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ItemActivity.this,"Donation successful, Thank You!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(ItemActivity.this, HomePage.class);
                startActivity(intent);
            }
        });




    }
}