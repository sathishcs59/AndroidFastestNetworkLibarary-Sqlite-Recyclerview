package com.example.manikkam.myapplication.Activities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by manikkam on 19/3/18.
 */

public class Product implements Parcelable {

    String id,name,uom,price;

    public Product() {
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        uom = in.readString();
        price = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(uom);
        dest.writeString(price);
    }
}

