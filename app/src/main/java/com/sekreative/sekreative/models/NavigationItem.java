package com.sekreative.sekreative.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;



public class NavigationItem implements Parcelable {

    @StringRes
    private int title;
    @DrawableRes
    private int icon;
    private String tag;

    public NavigationItem(@DrawableRes int icon, @StringRes int title, String tag) {
        this.title = title;
        this.icon = icon;
        this.tag = tag;
    }

    public int getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.title);
        dest.writeInt(this.icon);
    }

    protected NavigationItem(Parcel in) {
        this.title = in.readInt();
        this.icon = in.readInt();
    }

    public static final Parcelable.Creator<NavigationItem> CREATOR = new Parcelable.Creator<NavigationItem>() {
        @Override
        public NavigationItem createFromParcel(Parcel source) {
            return new NavigationItem(source);
        }

        @Override
        public NavigationItem[] newArray(int size) {
            return new NavigationItem[size];
        }
    };
}
