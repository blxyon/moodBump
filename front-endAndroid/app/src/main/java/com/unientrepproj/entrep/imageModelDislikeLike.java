package com.unientrepproj.entrep;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class imageModelDislikeLike{
    int mThumbId;
    Integer likeOrDislike=0;
    ImageButton like;
    ImageButton disklike;
    String spotLink;
    ImageView img;

    public void setButtons(ImageButton like, ImageButton dislike){
        this.like=like;
        this.disklike=dislike;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public ImageButton getLike() {
        return like;
    }
    public void setLink(String spotLink){
        this.spotLink=spotLink;
    }

    public String getSpotLink() {
        return spotLink;
    }

    public ImageButton getDisklike() {
        return disklike;
    }

    public Integer getLikeOrDislike() {
        return likeOrDislike;
    }

    public void setLikeOrDislike(Integer likeOrDislike) {
        this.likeOrDislike = likeOrDislike;
    }

    public int getmThumbIds(){
        return mThumbId;
    }
    public void setmThumbIds(int mThumbId){
        this.mThumbId=mThumbId;
    }

}
