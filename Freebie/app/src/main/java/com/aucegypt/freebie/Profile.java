package com.aucegypt.freebie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {
    private static final String TAG = "retrieve database prof";
    Button editProfileButton;
    TextView username, noItemsDonated, username2, username3, useremail, userphone, useraddress1,addressSmall;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editProfileButton=(Button)findViewById(R.id.editprofilebutton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this, editProfile.class);
                startActivity(intent);
            }
        });

        username = (TextView)findViewById(R.id.username);
        addressSmall = (TextView)findViewById(R.id.addressSmall);

        noItemsDonated = (TextView)findViewById(R.id.noItemsDonated);
        username2 = (TextView)findViewById(R.id.username2);
        username3 = (TextView)findViewById(R.id.username3);
        useremail = (TextView)findViewById(R.id.useremail);
        userphone = (TextView)findViewById(R.id.userphone);
        useraddress1 = (TextView)findViewById(R.id.useraddress1);

        DocumentReference docRef = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        username.setText(document.getString("fName"));
                        noItemsDonated.setText("10");
                        username2.setText(document.getString("fName"));
                        String[] tokens = document.getString("email").split("@");
                        username3.setText(tokens[0]);
                        useremail.setText(document.getString("email"));
                        userphone.setText(document.getString("phone"));
                        useraddress1.setText(document.getString("address"));
                        addressSmall.setText(document.getString("address"));
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });




    }
}