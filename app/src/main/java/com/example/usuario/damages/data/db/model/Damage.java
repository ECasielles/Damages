package com.example.usuario.damages.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Clase modelo para las averías.
 * @see Parcelable
 */
public class Damage implements Parcelable {

    public static final String TAG = "Damage";
    private int id;
    private String code;
    private int cityId;
    private String data;
    private String description;

    public Damage(int id, String code, int cityId, String data, String description) {
        this.id = id;
        this.code = code;
        this.cityId = cityId;
        this.data = data;
        this.description = description;
    }

    protected Damage(Parcel in) {
        id = in.readInt();
        code = in.readString();
        cityId = in.readInt();
        data = in.readString();
        description = in.readString();
    }

    public static final Creator<Damage> CREATOR = new Creator<Damage>() {
        @Override
        public Damage createFromParcel(Parcel in) {
            return new Damage(in);
        }

        @Override
        public Damage[] newArray(int size) {
            return new Damage[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(code);
        dest.writeInt(cityId);
        dest.writeString(data);
        dest.writeString(description);
    }
}
