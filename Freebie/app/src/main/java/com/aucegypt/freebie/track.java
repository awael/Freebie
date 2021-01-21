package com.aucegypt.freebie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class track extends AppCompatActivity {
    private Button addItem;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Track activity";
    String userID;
    ArrayList<String> arrayList=new ArrayList<String>();
    ArrayList<String> ids =new ArrayList<String>();




    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        SwipeMenuListView listView=(SwipeMenuListView)findViewById(R.id.listView);



        CollectionReference donRef = db.collection("Donations");
        Query donQuery = donRef.whereEqualTo("userID", FirebaseAuth.getInstance().getCurrentUser().getUid());
        donQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    adapter=new ArrayAdapter(track.this, android.R.layout.simple_list_item_1,arrayList);
                    listView.setAdapter(adapter);
                    for(QueryDocumentSnapshot documentSnapshot: task.getResult()){

                        ids.add(documentSnapshot.getId());
                        arrayList.add(documentSnapshot.getString("category")+" donated to " + documentSnapshot.getString("ngo"));
                    }
                }
                else{
                    System.out.println("Failed");
                }
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_baseline_cancel_24);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
//                    case 0:
//
//
//                        break;
                    case 0:
                        int itemnum=position;
                        new AlertDialog.Builder(track.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Confirm")
                                .setMessage("Are you sure you want to delete this item?")
                                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        db.collection("Donations").document(ids.get(itemnum)).delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error deleting document", e);
                                                    }
                                                });
                                        arrayList.remove(itemnum);
                                        ids.remove(itemnum);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Cancel",null)
                                .show();

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }
}