package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import static android.gesture.GestureOverlayView.ORIENTATION_HORIZONTAL;

public class Item_Activity extends AppCompatActivity {

    ImageButton ibAddItem, ibCloset, ibPresets, ibHome, ibHelp;
    RecyclerView rvItemView;
    ItemsAdapter itemsAdapter, itemsAdapter2;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ibAddItem = (ImageButton) findViewById(R.id.imgbtn_add);
        ibCloset = (ImageButton) findViewById(R.id.imgbtn_items);
        ibPresets = (ImageButton) findViewById(R.id.imgbtn_preset);
        ibHome = (ImageButton) findViewById(R.id.imgbtn_home);
        ibHelp = (ImageButton) findViewById(R.id.imgbtn_help);
        rvItemView = (RecyclerView) findViewById(R.id.rv_itemView);


        dbHelper = new DatabaseHelper(getBaseContext());
        itemsAdapter = new ItemsAdapter(getBaseContext(), dbHelper.getAllItemsCursor());

        rvItemView.setAdapter(itemsAdapter);
        rvItemView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        ibAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), AddItem.class);
                startActivity(i);
            }
        });

        ibCloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), Item_Activity.class);
                startActivity(i);
            }
        });

        ibPresets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), PresetView.class);
                startActivity(i);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        ibHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
