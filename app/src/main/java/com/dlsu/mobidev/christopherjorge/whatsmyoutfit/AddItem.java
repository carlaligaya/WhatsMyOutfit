package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddItem extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath, type;


    DatabaseHelper db;

    ImageButton ibBack;
    RelativeLayout v;
    ImageView   ivItem;
    TextView    tvLaman;
    EditText    etBrand, etColor, etSize, etDescription, etWorn;
    Button      bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ibBack = (ImageButton) findViewById(R.id.imgbtn_back);
        v = (RelativeLayout) findViewById(R.id.ReLayout);
        ivItem = (ImageView) findViewById(R.id.imageView);
        tvLaman = (TextView) findViewById(R.id.laman);
        etBrand = (EditText) findViewById(R.id.et_brand);
        etColor = (EditText) findViewById(R.id.et_color);
        etSize = (EditText) findViewById(R.id.et_size);
        etDescription = (EditText) findViewById(R.id.et_description);
        //etWorn = (EditText) findViewById(R.id.et_lastworn);
        bSave = (Button) findViewById(R.id.button);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentPhotoPath == null){
                    dispatchTakePictureIntent();
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(AddItem.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Retake Image of Clothing Item?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dispatchTakePictureIntent();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DatabaseHelper(getBaseContext());

                Items item = new Items();
                item.setType(type);
                item.setImageLocation(ivItem.getTag().toString());
                item.setBrand(etBrand.getText().toString());
                item.setSize(etSize.getText().toString());
                item.setColor(etColor.getText().toString());
                item.setDescription(etDescription.getText().toString());

               if(db.addItems(item) == true){
                   Intent i = new Intent();
                   i.setClass(getBaseContext(), Item_Activity.class);
                   startActivity(i);
               }else{
                   Intent i = new Intent();
                   i.setClass(getBaseContext(), AddItem.class);
                   startActivity(i);
               }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Bitmap bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(mCurrentPhotoPath), 150, 112); thumbnail shits
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);

            ExifInterface ei = null;
            try {
                ei = new ExifInterface(mCurrentPhotoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            Bitmap rotatedBitmap = null;
            switch(orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmap;
            }

            //mImageView.setImageURI(Uri.fromFile(new File(mCurrentPhotoPath)));
            ivItem.setImageBitmap(rotatedBitmap);
            ivItem.setTag(mCurrentPhotoPath);
            tvLaman.setText("");
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_tops:
                if (checked)
                    type = "Top";
                    break;
            case R.id.radio_bottoms:
                if (checked)
                    type = "Bottom";
                    break;
            case R.id.radio_shoes:
                if (checked)
                    type = "Shoes";
                    break;
        }
    }

}
