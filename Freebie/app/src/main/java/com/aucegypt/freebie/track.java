package com.aucegypt.freebie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import java.util.ArrayList;

public class track extends AppCompatActivity {
private Button addItem;


 ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        ArrayList<String> arrayList=new ArrayList<String>();
        SwipeMenuListView listView=(SwipeMenuListView)findViewById(R.id.listView);
        adapter=new ArrayAdapter(track.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        addItem=(Button)findViewById(R.id.additem);
        arrayList.add("T shirt");
        arrayList.add("Data structure book");
        arrayList.add("Chair");
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent1);
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setIcon(R.drawable.ic_put);
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_done);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:


                        break;
                    case 1:
                        int itemnum=position;
                        new AlertDialog.Builder(track.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Confirm")
                                .setMessage("Are you sure you want to mark this item as donated?")
                                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        arrayList.remove(itemnum);
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