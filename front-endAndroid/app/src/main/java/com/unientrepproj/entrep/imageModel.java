package com.unientrepproj.entrep;

public class imageModel {
    int mThumbId;
    Integer pressed=0;

    public Integer isPressed() {
        return pressed;
    }
    public int getmThumbIds(){
        return mThumbId;
    }
    public void setmThumbIds(int mThumbId){
        this.mThumbId=mThumbId;
    }
    public void setPressed() {
        if(pressed==0){
            pressed=1;
        }else{
            pressed=0;
        }
    }
}
