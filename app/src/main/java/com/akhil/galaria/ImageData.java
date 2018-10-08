package com.akhil.galaria;

public class ImageData {
    private int mImageIcon;
    private String mImageDescription;

    public boolean getLikedStatus() {
        return mLikedStatus;
    }

    public void setLikedStatus(boolean mLikedStatus) {
        this.mLikedStatus = mLikedStatus;
    }

    private boolean mLikedStatus;

    public int getImageIcon() {
        return mImageIcon;
    }

    public void setImageIcon(int mImageIcon) {
        this.mImageIcon = mImageIcon;
    }

    public String getImageDescription() {
        return mImageDescription;
    }

    public void setImageDescription(String mImageDescription) {
        this.mImageDescription = mImageDescription;
    }


}
