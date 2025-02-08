package com.example.meme.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Meme implements Parcelable {
    private final String memeID;

    private final String canalID;
    private final String canalName;

    public String getMemeID() {
        return memeID;
    }

    public String getCanalName() {
        return canalName;
    }
    
    public Meme(String memeID, String canalID, String canalName) {
        this.memeID = memeID;
        this.canalID = canalID;
        this.canalName = canalName;
    }

    protected Meme(Parcel in) {
        memeID = in.readString();
        canalID = in.readString();
        canalName = in.readString();
    }

    public static final Creator<Meme> CREATOR = new Creator<Meme>() {
        @Override
        public Meme createFromParcel(Parcel in) {
            return new Meme(in);
        }

        @Override
        public Meme[] newArray(int size) {
            return new Meme[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(memeID);
        dest.writeString(canalID);
        dest.writeString(canalName);
    }
}
