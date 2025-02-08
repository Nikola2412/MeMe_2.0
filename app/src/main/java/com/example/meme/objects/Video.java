package com.example.meme.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Video implements Parcelable{

    private final String videoID;
    private final String videoName;

    private final String canalID;
    private final String canalName;

    public Video(String videoID,String videoName,String canalID,String canalName){
        this.videoID = videoID;
        this.videoName = videoName;
        this.canalID = canalID;
        this.canalName = canalName;
    }


    protected Video(Parcel in) {
        videoID = in.readString();
        videoName = in.readString();
        canalID = in.readString();
        canalName = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getVideoID() {
        return videoID;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getCanalID() {
        return canalID;
    }

    public String getCanalName() {
        return canalName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(videoID);
        dest.writeString(videoName);
        dest.writeString(canalID);
        dest.writeString(canalName);
    }
}
