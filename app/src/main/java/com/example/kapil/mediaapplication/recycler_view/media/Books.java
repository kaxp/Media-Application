package com.example.kapil.mediaapplication.recycler_view.media;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Books implements Parcelable {

    @SerializedName("title")
    public String Title;
    @SerializedName("author")
    public String Author;
    @SerializedName("reviews")
    public Integer Reviews;
    @SerializedName("description")
    public String Description;
    @SerializedName("rating")
    public Double Rating;
    @SerializedName("bannerImage")
    public String BannerImage;
    @SerializedName("aboutAuthor")
    public String AboutAuthor;

    public Books(String title, String  author, Integer reviews, String description, Double rating, String bannerImage, String aboutAuthor) {
        Title = title;
        Author = author;
        Reviews = reviews;
        Description = description;
        Rating = rating;
        BannerImage = bannerImage;
        AboutAuthor = aboutAuthor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeString(this.Author);
        dest.writeValue(this.Reviews);
        dest.writeString(this.Description);
        dest.writeValue(this.Rating);
        dest.writeString(this.BannerImage);
        dest.writeString(this.AboutAuthor);
    }

    protected Books(Parcel in) {
        this.Title = in.readString();
        this.Author = in.readString();
        this.Reviews = (Integer) in.readValue(Integer.class.getClassLoader());
        this.Description = in.readString();
        this.Rating = (Double) in.readValue(Double.class.getClassLoader());
        this.BannerImage = in.readString();
        this.AboutAuthor = in.readString();
    }

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel source) {
            return new Books(source);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };
}
