package com.aucegypt.freebie;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;
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
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ngoView_public#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ngoView_public extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ngoView_public() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ngoView_public.
     */
    // TODO: Rename and change types and number of parameters
    public static ngoView_public newInstance(String param1, String param2) {
        ngoView_public fragment = new ngoView_public();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //Query capitalCities = firebaseFirestore.collection("Donation").whereEqualTo("Resala", true);

                     /*
                     ArrayAdapter<String> adapter= new ArrayAdapter<>(getActivity().getApplicationContext(),
                      android.R.layout.simple_expandable_list_item_1,
                        android.R.id.text1,itemsarrayList);

                    //ListView listView=(ListView)ngoView.f
                     listView.setAdapter(adapter);*/
        //   SwipeMenuListView listView=(SwipeMenuListView)getActivity().findViewById(R.id.listView2);
        //       ArrayAdapter adapter=new ArrayAdapter(getActivity()
        //             .getApplicationContext(),android.R.layout.simple_list_item_1,itemsarrayList);
//        listView.setAdapter(adapter);






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_ngo_view_public, container, false);
        ListView listView=(ListView) view.findViewById(R.id.list_view2);
        FirebaseAuth firebaseAuth;
        FirebaseFirestore firebaseFirestore;
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        CollectionReference collectionReference=firebaseFirestore.collection("Donations");
        Query query= collectionReference
                .whereEqualTo("ngo","Resala".toString());

        ArrayList<String> ids=new ArrayList<String>();
        ArrayList<String> names=new ArrayList<String>();
        ArrayList<String> adresses=new ArrayList<String>();
        ArrayList<String> phones=new ArrayList<String>();

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                ArrayList<String> itemsarrayList=new ArrayList<String>();

                ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,itemsarrayList);
                listView.setAdapter(arrayAdapter);
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document:task.getResult()){
                        System.out.println("works");
                       // itemsarrayList.add(document.getString("notes")) ;
                        itemsarrayList.add(document.getString("category")+":  "+document.getString("notes")) ;
                        ids.add( document.getString("userID"));
                        names.add( document.getString("fName"));
                        adresses.add( document.getString("address"));
                        phones.add( document.getString("phone"));
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    System.out.println("Fail");
                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String id= ids.get(position);
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle("Donation Info");
                        alertDialog.setMessage("Name: "+names.get(position)+"\n Phone:"+phones.get(position)+"\n Address: "+adresses.get(position));
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();


                    }
                });
            }

             ///////////////////

        });
        return view;
    }
}