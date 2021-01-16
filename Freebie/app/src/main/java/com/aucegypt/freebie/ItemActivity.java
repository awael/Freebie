package com.aucegypt.freebie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {
    Button donate;

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

        relativeLayout = findViewById(R.id.rel1);
        handler.postDelayed(runnable, 500);

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