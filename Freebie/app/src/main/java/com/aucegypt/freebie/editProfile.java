package com.aucegypt.freebie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class editProfile extends AppCompatActivity {
    private static final String TAG = "retrieve database edit";
    Button updateProfileButton;
    EditText editName;
    EditText editUserName;
    EditText editEmail;
    EditText editPhone;
    EditText editAddress;
    TextView tv_name;
    TextView tv_address;

    String EDIT_NAME = null;
    String EDIT_USERNAME = null;
    String EDIT_EMAIL = null;
    String EDIT_PHONE= null;
    String EDIT_ADDRESS = null;
    String EDIT_ADDRESS2 = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_address = (TextView)findViewById(R.id.tv_address);
        updateProfileButton=(Button)findViewById(R.id.updateprofilebutton);


        editName= findViewById(R.id.editName);
        editUserName=findViewById(R.id.editUserName);
        editEmail=findViewById(R.id.editEmail);
        editPhone=findViewById(R.id.editPhone);
        editAddress=findViewById(R.id.editAddress1);

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editNameNew = editName.getText().toString();
//                String editUserNameNew=editUserName.getText().toString();
//                String editEmailNew=editEmail.getText().toString();
                String editPhoneNew=editPhone.getText().toString();
                String editAddressNew=editAddress.getText().toString();

                DocumentReference DocRefUpdate = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

                DocRefUpdate
                        .update("address", editAddressNew)
                        .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully updated!"))
                        .addOnFailureListener(e -> Log.w(TAG, "Error updating document", e));
                DocRefUpdate
                        .update("fName", editNameNew)
                        .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully updated!"))
                        .addOnFailureListener(e -> Log.w(TAG, "Error updating document", e));
                DocRefUpdate
                        .update("phone", editPhoneNew)
                        .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully updated!"))
                        .addOnFailureListener(e -> Log.w(TAG, "Error updating document", e));




                Intent intent=new Intent(editProfile.this, HomePage.class);
                startActivity(intent);
            }
        });

        DocumentReference docRef = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());



                        tv_name.setText(document.getString("fName"));
                        tv_address.setText(document.getString("address"));

                        editName.setText(document.getString("fName"));
                        String[] tokens = document.getString("email").split("@");
                        editUserName.setText(tokens[0]);
                        editEmail.setText(document.getString("email"));
                        editPhone.setText(document.getString("phone"));
                        editAddress.setText(document.getString("address"));
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