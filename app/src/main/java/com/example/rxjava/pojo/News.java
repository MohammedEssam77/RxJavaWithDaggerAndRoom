package com.example.rxjava.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_table")
public class News extends NewsRseponse {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Name ;
    private String URL ;


    public News(String name, String URL) {
        Name = name;
        this.URL = URL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
