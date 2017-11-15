package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddPreset extends AppCompatActivity {

    ImageButton ibBack;
    EditText    etTitle;
    ImageView   ivTop, ivBottom, ivShoes;
    Button      bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_preset);

        ibBack = (ImageButton) findViewById(R.id.imgbtn_back);
        etTitle = (EditText) findViewById(R.id.et_presetTitle);
        ivTop = (ImageView) findViewById(R.id.imageView_top);
        ivBottom = (ImageView) findViewById(R.id.imageView_bottom);
        ivShoes = (ImageView) findViewById(R.id.imageView_shoes);
        bSave = (Button) findViewById(R.id.button);


        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ivBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ivShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
