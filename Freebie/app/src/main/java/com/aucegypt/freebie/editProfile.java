package com.aucegypt.freebie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class editProfile extends AppCompatActivity {
    Button updateProfileButton;
    EditText editName;
    EditText editUserName;
    EditText editEmail;
    EditText editPhone;
    EditText editAddress;
    EditText editAddress2;
    String EDIT_NAME = null;
    String EDIT_USERNAME = null;
    String EDIT_EMAIL = null;
    String EDIT_PHONE= null;
    String EDIT_ADDRESS = null;
    String EDIT_ADDRESS2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        updateProfileButton=(Button)findViewById(R.id.updateprofilebutton);
        editName=  findViewById(R.id.editName);
        editUserName=findViewById(R.id.editUserName);
         editEmail=findViewById(R.id.editEmail);
         editPhone=findViewById(R.id.editPhone);
         editAddress=findViewById(R.id.editAddress1);
         editAddress2=findViewById(R.id.editAddress2);

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(editProfile.this, Profile.class);
                startActivity(intent);
            }
        });


    }
}