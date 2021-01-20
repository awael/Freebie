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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    Button btn1;
    TextView textView;
    private EditText email, password, confirm_password, name, phonenumber, address;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn1 = (Button) findViewById(R.id.signupbtn);
        email = findViewById(R.id.signupemailtxt);
        password = findViewById(R.id.passwordtxt);
        name = findViewById(R.id.nametxt);
        phonenumber = findViewById(R.id.phonetxt);
        address = findViewById(R.id.addresstxt);

        confirm_password = findViewById(R.id.passwordtxt1);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        textView = findViewById(R.id.signhvaccountid);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(SignUp.this,HomePage.class);
//                startActivity(intent);
                createUser();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createUser() {
        String mEmail = email.getText().toString();
        String mPassword = password.getText().toString();
        String mPassword1 = confirm_password.getText().toString();
        String mName = name.getText().toString();
        String mPhone = phonenumber.getText().toString();
        String mAddress = address.getText().toString();


        if (mEmail.isEmpty()) {
            email.setError("Email cannot be empty");
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            email.setError("Please enter a valid email");
            return;
        }

        if (mName.isEmpty()) {
            email.setError("Name cannot be empty");
            return;
        }

        if (mPhone.isEmpty()) {
            email.setError("Phone number cannot be empty");
            return;
        }
        if (mAddress.isEmpty()) {
            email.setError("Address cannot be empty");
            return;
        }

        if (mPassword.isEmpty()) {
            password.setError("Password cannot be empty");
            return;
        }

        if (mPassword.length() < 8) {
            Toast.makeText(SignUp.this, "password must have at least 8 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mPassword1.isEmpty()) {
            password.setError("Password cannot be empty");
        }

        if (!mPassword.equals(mPassword1)) {
            Toast.makeText(SignUp.this, "Password must match!", Toast.LENGTH_SHORT).show();
        }


        if (!mPassword.isEmpty() && !mPassword1.isEmpty() && mPassword.equals(mPassword1) && mPassword.length() >= 8) {
            firebaseAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                userID = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("Users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fName", mName);
                                user.put("email", mEmail);
                                user.put("phone", mPhone);
                                user.put("address", mAddress);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(SignUp.this, "Profile created Successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                });




                                startActivity(new Intent(SignUp.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignUp.this, "Your email is already registered.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUp.this, "Failed. Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}

//
//
//        if (!mEmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
//                if (!mPassword.isEmpty() && !mPassword1.isEmpty() && mPassword.equals(mPassword1) &&mPassword.length()>= 8) {
//                firebaseAuth.createUserWithEmailAndPassword(mEmail, mPassword)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//@Override
//public void onComplete(@NonNull Task<AuthResult> task) {
//        if(task.isSuccessful()) {
//        Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(SignUp.this, MainActivity.class));
//        finish();
//        }
//        else{
//        Toast.makeText(SignUp.this, "Your email is already registered.", Toast.LENGTH_SHORT).show();
//        }
//        }
//        }).addOnFailureListener(new OnFailureListener() {
//@Override
//public void onFailure(@NonNull Exception e) {
//        Toast.makeText(SignUp.this, "Failed. Please try again!", Toast.LENGTH_SHORT).show();
//        }
//        });
//        } else if(mPassword.length() < 8){
//        Toast.makeText(SignUp.this, "password must have at least 8 characters", Toast.LENGTH_SHORT).show();
//
//        }
//        else if(!mPassword.equals(mPassword1)) {
//        Toast.makeText(SignUp.this, "Password must match!", Toast.LENGTH_SHORT).show();
//        }
//        else if(mPassword.isEmpty()){
//        password.setError("Password cannot be empty");
//        }
//        else{
//        confirm_password.setError("Password cannot be empty");
//        }
//        } else if (mEmail.isEmpty()) {
//        email.setError("Email cannot be empty");
//        } else {
//        email.setError("Please enter a valid email");
//        }