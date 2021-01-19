package com.aucegypt.freebie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btn1, noAccButton;
    private EditText email, password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.emailtxt);
        password = findViewById(R.id.passwordtxt);
        btn1=(Button)findViewById(R.id.signinbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this, HomePage.class);
//                startActivity(intent);
                loginUser();
            }
        });

        noAccButton=(Button)findViewById(R.id.noAccButton);
        noAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser() {
        String mEmail = email.getText().toString();
        String mPassword = password.getText().toString();

        if (!mEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            if (!mPassword.isEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(mEmail, mPassword)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(MainActivity.this, HomePage.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                password.setError("Password cannot be empty");
            }
        } else if (mEmail.isEmpty()) {
            email.setError("Email cannot be empty");
        } else {
            email.setError("Please enter a valid email");
        }
    }
}



