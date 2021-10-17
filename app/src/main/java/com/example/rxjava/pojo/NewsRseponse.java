package com.example.rxjava.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class NewsRseponse implements Parcelable {
    private ArrayList<News> results;

    public NewsRseponse() {
    }

    public ArrayList<News> getResults() {
        return results;
    }
    public static final Creator<NewsRseponse> CREATOR = new Creator<NewsRseponse>() {
        @Override
        public NewsRseponse createFromParcel(Parcel in) {
            return new NewsRseponse(in);
        }

        @Override
        public NewsRseponse[] newArray(int size) {
            return new NewsRseponse[size];
        }
    };

    private int ID;
    private String Title;
    private String Img;
    private String SectionTitle;
    private int SourceID;
    private String SourceTitle;
    private int TimeStamp;
    private boolean IsSelectedFavorite;
    private boolean IsSelectedReadLater;
    private String FormatedDate;
    private String Ratio;
    private int Type;

    public NewsRseponse(Parcel in) {
        ID = in.readInt();
        Title = in.readString();
        Img = in.readString();
        SectionTitle = in.readString();
        SourceID = in.readInt();
        SourceTitle = in.readString();
        TimeStamp = in.readInt();
        IsSelectedFavorite = in.readByte() != 0;
        IsSelectedReadLater = in.readByte() != 0;
        FormatedDate = in.readString();
        Ratio = in.readString();
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getSectionTitle() {
        return SectionTitle;
    }

    public void setSectionTitle(String SectionTitle) {
        this.SectionTitle = SectionTitle;
    }

    public int getSourceID() {
        return SourceID;
    }

    public void setSourceID(int SourceID) {
        this.SourceID = SourceID;
    }

    public String getSourceTitle() {
        return SourceTitle;
    }

    public void setSourceTitle(String SourceTitle) {
        this.SourceTitle = SourceTitle;
    }

    public int getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(int TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    public boolean isIsSelectedFavorite() {
        return IsSelectedFavorite;
    }

    public void setIsSelectedFavorite(boolean IsSelectedFavorite) {
        this.IsSelectedFavorite = IsSelectedFavorite;
    }

    public boolean isIsSelectedReadLater() {
        return IsSelectedReadLater;
    }

    public void setIsSelectedReadLater(boolean IsSelectedReadLater) {
        this.IsSelectedReadLater = IsSelectedReadLater;
    }

    public String getFormatedDate() {
        return FormatedDate;
    }

    public void setFormatedDate(String FormatedDate) {
        this.FormatedDate = FormatedDate;
    }

    public String getRatio() {
        return Ratio;
    }

    public void setRatio(String Ratio) {
        this.Ratio = Ratio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(Title);
        parcel.writeString(Img);
        parcel.writeString(SectionTitle);
        parcel.writeInt(SourceID);
        parcel.writeString(SourceTitle);
        parcel.writeInt(TimeStamp);
        parcel.writeByte((byte) (IsSelectedFavorite ? 1 : 0));
        parcel.writeByte((byte) (IsSelectedReadLater ? 1 : 0));
        parcel.writeString(FormatedDate);
        parcel.writeString(Ratio);
    }
}