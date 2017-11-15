package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by Christopher Jorge on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String SCHEMA = "mobidev";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Items.TABLE_NAME + " ("
                + Items.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Items.COLUMN_TYPE + " TEXT,"
                + Items.COLUMN_IMAGE_LOCATION + " TEXT,"
                + Items.COLUMN_BRAND + " TEXT,"
                + Items.COLUMN_SIZE + " TEXT,"
                + Items.COLUMN_COLOR + " TEXT,"
                + Items.COLUMN_DESCRIPTION + " TEXT,"
                + Items.COLUMN_LAST_WORN + " NUMERIC);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + Items.TABLE_NAME + ";";

        db.execSQL(sql);

        onCreate(db);
    }


    //add items
    public boolean addItems (Items items){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Items.COLUMN_TYPE, items.getType());
        cv.put(Items.COLUMN_IMAGE_LOCATION, items.getImageLocation());
        cv.put(Items.COLUMN_BRAND, items.getBrand());
        cv.put(Items.COLUMN_SIZE, items.getSize());
        cv.put(Items.COLUMN_COLOR, items.getColor());
        cv.put(Items.COLUMN_DESCRIPTION, items.getDescription());
        cv.put(Items.COLUMN_LAST_WORN, String.valueOf(items.getLastWorn()));

        long id = db.insert(Items.TABLE_NAME,
                "columnhack",
                cv);

        db.close();

        return (id!=-1);
    }

    //edit items
    public boolean editItems (Items newItemsDetails, int currentID){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Items.COLUMN_TYPE, newItemsDetails.getType());
        cv.put(Items.COLUMN_IMAGE_LOCATION, newItemsDetails.getImageLocation());
        cv.put(Items.COLUMN_BRAND, newItemsDetails.getBrand());
        cv.put(Items.COLUMN_SIZE, newItemsDetails.getSize());
        cv.put(Items.COLUMN_DESCRIPTION, newItemsDetails.getDescription());
        cv.put(Items.COLUMN_LAST_WORN, String.valueOf(newItemsDetails.getLastWorn()));

        int rowsAffected = db.update(Items.TABLE_NAME,
                cv,
                Items.COLUMN_ID + "=?",
                new String[]{newItemsDetails.getId()+""});

        db.close();

        return rowsAffected>0;
    }


    //delete items
    public boolean deleteItems (long id){
        SQLiteDatabase db = getWritableDatabase();

        int rowsAffected = db.delete(Items.TABLE_NAME,
                Items.COLUMN_ID + " =?",
                new String[]{id + ""});

        db.close();

        return rowsAffected > 0;
    }

    //get one item
    public Items getItem (long id){
        SQLiteDatabase db = getReadableDatabase();

        String whereClause = Items.COLUMN_ID + "=?";
        String[] whereArgs = new String[]{id + " "};

        Cursor c = db.query(Items.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,null,null);

        Items items = null;

        if(c.moveToFirst()){
            items = new Items();
            items.setType(c.getString(c.getColumnIndex(Items.COLUMN_TYPE)));
            items.setImageLocation(c.getString(c.getColumnIndex(Items.COLUMN_IMAGE_LOCATION)));
            items.setBrand(c.getString(c.getColumnIndex(Items.COLUMN_BRAND)));
            items.setSize(c.getString(c.getColumnIndex(Items.COLUMN_SIZE)));
            items.setColor(c.getString(c.getColumnIndex(Items.COLUMN_COLOR)));
            items.setDescription(c.getString(c.getColumnIndex(Items.COLUMN_DESCRIPTION)));
            items.setLastWorn(new Date (c.getString(c.getColumnIndex(Items.COLUMN_LAST_WORN))));
        }
        c.close();
        db.close();

        return items;
    }

    /*
    //get Tops
    public Items getTops (long id){
        SQLiteDatabase db = getReadableDatabase();

        String whereClause = "COLUMN_TYPE = ?";
        String[] whereArgs = new String[]{"tops"};

        Cursor c = db.query(Items.TABLE_NAME,
                null,whereClause,whereArgs,null,null,null);

        Items items = null;

        if(c.moveToFirst()){
            items = new Items();
            items.setType(c.getString(c.getColumnIndex(Items.COLUMN_TYPE)));
            items.setImageLocation(c.getString(c.getColumnIndex(Items.COLUMN_IMAGE_LOCATION)));
            items.setBrand(c.getString(c.getColumnIndex(Items.COLUMN_BRAND)));
            items.setSize(c.getString(c.getColumnIndex(Items.COLUMN_SIZE)));
            items.setColor(c.getString(c.getColumnIndex(Items.COLUMN_COLOR)));
            items.setDescription(c.getString(c.getColumnIndex(Items.COLUMN_DESCRIPTION)));
            items.setLastWorn(new Date (c.getString(c.getColumnIndex(Items.COLUMN_LAST_WORN))));
        }
        c.close();
        db.close();

        return items;
    }

    //get Bottoms
    public Items getBottoms (long id){
        SQLiteDatabase db = getReadableDatabase();

        String whereClause = "COLUMN_TYPE = ?";
        String[] whereArgs = new String[]{"bottoms"};

        Cursor c = db.query(Items.TABLE_NAME,
                null,whereClause,whereArgs,null,null,null);

        Items items = null;

        if(c.moveToFirst()){
            items = new Items();
            items.setType(c.getString(c.getColumnIndex(Items.COLUMN_TYPE)));
            items.setImageLocation(c.getString(c.getColumnIndex(Items.COLUMN_IMAGE_LOCATION)));
            items.setBrand(c.getString(c.getColumnIndex(Items.COLUMN_BRAND)));
            items.setSize(c.getString(c.getColumnIndex(Items.COLUMN_SIZE)));
            items.setColor(c.getString(c.getColumnIndex(Items.COLUMN_COLOR)));
            items.setDescription(c.getString(c.getColumnIndex(Items.COLUMN_DESCRIPTION)));
            items.setLastWorn(new Date (c.getString(c.getColumnIndex(Items.COLUMN_LAST_WORN))));
        }
        c.close();
        db.close();

        return items;
    }


    //get Shoes
    public Items getShoes (long id){
        SQLiteDatabase db = getReadableDatabase();

        String whereClause = "COLUMN_TYPE = ?";
        String[] whereArgs = new String[]{"shoes"};

        Cursor c = db.query(Items.TABLE_NAME,
                null,whereClause,whereArgs,null,null,null);

        Items items = null;

        if(c.moveToFirst()){
            items = new Items();
            items.setType(c.getString(c.getColumnIndex(Items.COLUMN_TYPE)));
            items.setImageLocation(c.getString(c.getColumnIndex(Items.COLUMN_IMAGE_LOCATION)));
            items.setBrand(c.getString(c.getColumnIndex(Items.COLUMN_BRAND)));
            items.setSize(c.getString(c.getColumnIndex(Items.COLUMN_SIZE)));
            items.setColor(c.getString(c.getColumnIndex(Items.COLUMN_COLOR)));
            items.setDescription(c.getString(c.getColumnIndex(Items.COLUMN_DESCRIPTION)));
            items.setLastWorn(new Date (c.getString(c.getColumnIndex(Items.COLUMN_LAST_WORN))));
        }
        c.close();
        db.close();

        return items;
    }**/


    //get all items
    public Cursor getAllItemsCursor (){
        return getReadableDatabase().query(Items.TABLE_NAME, null, null, null, null, null, null);
    }


    //get all tops/bottoms/shoes
    public Cursor getAllTypesCursor(String type){
        String whereClause = "COLUMN_TYPE=?";
        String[] whereArgs = new String[] {type + ""};

        return getReadableDatabase().query(Items.TABLE_NAME,null,whereClause,whereArgs,null,null,null);
    }




}
