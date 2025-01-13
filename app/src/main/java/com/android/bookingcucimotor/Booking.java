package com.android.bookingcucimotor;

import android.os.Parcel;
import android.os.Parcelable;

public class Booking implements Parcelable {
    private final int img;
    private final String name;
    private final String price;
    private final String description;

    public Booking(int img, String name, String price, String description) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    protected Booking(Parcel in) {
        img = in.readInt();
        name = in.readString();
        price = in.readString();
        description = in.readString();
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static final Creator<Booking> CREATOR = new Creator<Booking>() {
        @Override
        public Booking createFromParcel(Parcel in) {
            return new Booking(in);
        }

        @Override
        public Booking[] newArray(int size) {
            return new Booking[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(img);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(description);
    }
}
