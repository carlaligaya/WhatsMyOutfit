package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewSpecificPreset extends AppCompatActivity {

    ImageButton ibBack;
    TextView tvTitle,tvDate;
    ImageView ivTop, ivBottom, ivShoes;
    Button bEdit,bDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_preset);

        ibBack = (ImageButton) findViewById(R.id.imgbtn_back);
        tvTitle = (TextView) findViewById(R.id.tv_presetTitle);
        tvDate = (TextView) findViewById(R.id.tv_presetDate);
        ivTop = (ImageView) findViewById(R.id.imageView_top);
        ivBottom = (ImageView) findViewById(R.id.imageView_bottom);
        ivShoes = (ImageView) findViewById(R.id.imageView_shoes);
        bEdit = (Button) findViewById(R.id.button_edit);
        bDelete = (Button) findViewById(R.id.button_delete);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
