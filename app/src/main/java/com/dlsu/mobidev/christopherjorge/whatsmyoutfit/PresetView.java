package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PresetView extends AppCompatActivity {

    ImageButton ibAddPreset, ibCloset, ibPresets, ibHome, ibHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_view);

        ibAddPreset = (ImageButton) findViewById(R.id.imgbtn_add);
        ibCloset = (ImageButton) findViewById(R.id.imgbtn_items);
        ibPresets = (ImageButton) findViewById(R.id.imgbtn_preset);
        ibHome = (ImageButton) findViewById(R.id.imgbtn_home);
        ibHelp = (ImageButton) findViewById(R.id.imgbtn_help);

        ibAddPreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), AddPreset.class);
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
