package com.dlsu.mobidev.christopherjorge.whatsmyoutfit;

import java.util.Date;

/**
 * Created by Christopher Jorge on 11/13/2017.
 */

public class Items {

    public static final String TABLE_NAME = "items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_IMAGE_LOCATION = "image";
    public static final String COLUMN_BRAND = "brand";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LAST_WORN = "last_worn";

    private long id;
    private String type;
    private String imageLocation;
    private String brand;
    private String size;
    private String color;
    private String description;
    private Date lastWorn;

    public Items(){}

    public Items(long id, String type, String imageLocation, String brand,
                 String size, String color, String description, Date lastWorn) {
        this.id = id;
        this.type = type;
        this.imageLocation = imageLocation;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.description = description;
        this.lastWorn = lastWorn;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastWorn() {
        return lastWorn;
    }

    public void setLastWorn(Date lastWorn) {
        this.lastWorn = lastWorn;
    }
}
