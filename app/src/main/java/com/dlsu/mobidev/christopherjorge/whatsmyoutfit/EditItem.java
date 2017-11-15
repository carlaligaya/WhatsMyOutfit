package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class EditItem extends AppCompatActivity {

    ImageButton ibBack;
    ImageView ivItem;
    EditText etBrand, etColor, etSize, etDescription;
    Button bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        ibBack = (ImageButton) findViewById(R.id.imgbtn_back);
        ivItem = (ImageView) findViewById(R.id.imageView);
        etBrand = (EditText) findViewById(R.id.et_brand);
        etColor = (EditText) findViewById(R.id.et_color);
        etSize = (EditText) findViewById(R.id.et_size);
        etDescription = (EditText) findViewById(R.id.et_description);
        bSave = (Button) findViewById(R.id.button);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
