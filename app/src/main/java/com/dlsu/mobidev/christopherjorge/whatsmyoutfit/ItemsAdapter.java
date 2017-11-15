package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Christopher Jorge on 11/13/2017.
 */

public class ItemsAdapter extends CursorRecyclerViewAdapter<ItemsAdapter.ViewHolder> {


    private OnItemClickListener onItemClickListener;

    public ItemsAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(Items.COLUMN_ID));
        String imagePath = cursor.getString(cursor.getColumnIndex(Items.COLUMN_IMAGE_LOCATION));
        String type = cursor.getString(cursor.getColumnIndex(Items.COLUMN_TYPE));
        String brand = cursor.getString(cursor.getColumnIndex(Items.COLUMN_BRAND));
        String size = cursor.getString(cursor.getColumnIndex(Items.COLUMN_SIZE));
        String color = cursor.getString(cursor.getColumnIndex(Items.COLUMN_COLOR));
        String lastWorn = cursor.getString(cursor.getColumnIndex(Items.COLUMN_LAST_WORN));

        Bitmap bm = BitmapFactory.decodeFile(imagePath);

        ExifInterface ei = null;
        try {
            ei = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch(orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bm, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bm, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bm, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bm;
        }

        viewHolder.ivThumbnail.setImageBitmap(rotatedBitmap);
        viewHolder.tvType.setText(type);
        viewHolder.tvBrand.setText(brand);
        viewHolder.tvSize.setText(size);
        viewHolder.tvColor.setText(color);
        if(!(lastWorn.equalsIgnoreCase("null"))){
            viewHolder.tvLastWorn.setText(lastWorn);
        }

        /*viewHolder.itemView.setTag(id);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id to caller
                onItemClickListener.onItemClick((Long) v.getTag());
            }
        });*/
    }



    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(v);
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivThumbnail;
        TextView tvType, tvBrand, tvColor, tvSize, tvLastWorn;


        public ViewHolder (View itemView){
            super(itemView);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            tvBrand = (TextView) itemView.findViewById(R.id.tv_brand);
            tvColor = (TextView) itemView.findViewById(R.id.tv_color);
            tvSize = (TextView) itemView.findViewById(R.id.tv_size);
            tvLastWorn = (TextView) itemView.findViewById(R.id.tv_last_worn);
        }
    }


    // interface to be implemented to know if an item has been clicked or not
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(long id);
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

}
