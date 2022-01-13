package com.example.examenej1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

public class AirportDTO implements Parcelable {
    private int id;
    private String name;
    private long[] position;
    private File shield;

    public AirportDTO(String name, long[] position, File shield) {
        this.id = 0;
        this.name = name;
        this.position = position;
        this.shield = shield;
    }

    public AirportDTO(int id, String name, long[] position, File shield) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shield = shield;
    }


    protected AirportDTO(Parcel in) {
        id = in.readInt();
        name = in.readString();
        position = in.createLongArray();
        shield = new File(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeLongArray(position);
        dest.writeString(shield.getAbsolutePath());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportDTO> CREATOR = new Creator<AirportDTO>() {
        @Override
        public AirportDTO createFromParcel(Parcel in) {
            return new AirportDTO(in);
        }

        @Override
        public AirportDTO[] newArray(int size) {
            return new AirportDTO[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long[] getPosition() {
        return position;
    }

    public void setPosition(long[] position) {
        this.position = position;
    }

    public File getShield() {
        return shield;
    }

    public void setShield(File shield) {
        this.shield = shield;
    }
}
