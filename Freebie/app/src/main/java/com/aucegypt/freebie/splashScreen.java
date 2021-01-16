package com.aucegypt.freebie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class splashScreen extends AppCompatActivity {

    ImageView logo;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo1);
        lottieAnimationView = findViewById(R.id.lottie1);

        logo.animate().translationY(1200).setDuration(1000).setStartDelay(4000);
        logo.animate().rotationBy(360).setDuration(2000).setStartDelay(1000);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                splashScreen.this.finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }
        }, 3000);



    }
}