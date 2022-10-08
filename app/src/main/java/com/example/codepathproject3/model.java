package com.example.codepathproject3;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class model{
    String title;
    String body;
    String picture;

    public model(String title, String body, String picture) {
        this.title = title;
        this.body = body;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getPicture() {
        return picture;
    }
}
