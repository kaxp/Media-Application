package com.example.kapil.mediaapplication.recycler_view.media;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Media implements Parcelable{

    @SerializedName("title")
    public String Title;
    @SerializedName("year")
    public Integer Year;
    @SerializedName("genre")
    public String Genre;
    @SerializedName("director")
    public String Director;
    @SerializedName("rating")
    public Double Rating;
    @SerializedName("summary")
    public String Summary;
    @SerializedName("duration")
    public String Duration;
    @SerializedName("thumbnailImage")
    public String ThumbnailImage;
    @SerializedName("bannerImage")
    public String BannerImage;
    @SerializedName("videoLink")
    public String VideoLink;




    public Media(String bannerImage, String thumbnailImage, String title, Integer year, String genre, String director, Double rating, String duration, String summary, String videoLink) {
        Title = title;
        Year = year;
        Genre = genre;
        Director = director;
        Rating = rating;
        Duration = duration;
        Summary = summary;

        ThumbnailImage = thumbnailImage;
        BannerImage = bannerImage;
        VideoLink = videoLink;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeValue(this.Year);
        dest.writeString(this.Genre);
        dest.writeString(this.Director);
        dest.writeValue(this.Rating);
        dest.writeString(this.Summary);
        dest.writeString(this.Duration);
        dest.writeString(this.ThumbnailImage);
        dest.writeString(this.BannerImage);
        dest.writeString(this.VideoLink);
    }

    protected Media(Parcel in) {
        this.Title = in.readString();
        this.Year = (Integer) in.readValue(Integer.class.getClassLoader());
        this.Genre = in.readString();
        this.Director = in.readString();
        this.Rating = (Double) in.readValue(Double.class.getClassLoader());
        this.Summary = in.readString();
        this.Duration = in.readString();
        this.ThumbnailImage = in.readString();
        this.BannerImage = in.readString();
        this.VideoLink = in.readString();
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel source) {
            return new Media(source);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
}
