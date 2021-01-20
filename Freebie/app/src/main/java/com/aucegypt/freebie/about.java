package com.aucegypt.freebie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class about extends AppCompatActivity {


    ListView listView;
    String[] infoTitle;
    String[] infoSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setTitle("App developer info");
        infoTitle = getResources().getStringArray(R.array.dev_info);
        infoSubtitle = getResources().getStringArray(R.array.dev_info_subtitle);

        listView = findViewById(R.id.infoList);
        InfoAdapter infoAdapter = new InfoAdapter(this, infoTitle, infoSubtitle);
        listView.setAdapter(infoAdapter);
    }
}


class InfoAdapter extends ArrayAdapter<String> {
    Context context;
    String[] infoTitle;
    String[] infoSubtitle;

    public InfoAdapter(Context context, String[] title, String[] subtitle) {
        super(context, R.layout.info_row, R.id.title1, title);
        this.context = context;
        this.infoSubtitle = subtitle;
        this.infoTitle = title;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        View rowItem = convertView;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowItem = layoutInflater.inflate(R.layout.info_row,parent, false);
        TextView nSubtitle = rowItem.findViewById(R.id.subtitle);
        TextView nTitle = rowItem.findViewById(R.id.title1);
        nTitle.setText(infoTitle[position]);
        nSubtitle.setText(infoSubtitle[position]);
        return rowItem;
    }
}

