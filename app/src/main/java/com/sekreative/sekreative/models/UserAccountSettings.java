package com.sekreative.sekreative.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserAccountSettings implements Parcelable{

    private String description;
    private String display_name;
    private long followers;
    private String cover_photo;
    private long following;
    private long posts;
    private String profile_photo;
    private String username;
    private String website;
    private String user_id;
    private int creations;
    private int coins;
    private int sno;

    public UserAccountSettings(String description, String display_name, long followers,
                               long following, long posts, String profile_photo, String username,
                               String website, String user_id, int creations, int coins,int sno,String cover_photo) {
        this.description = description;
        this.display_name = display_name;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.profile_photo = profile_photo;
        this.username = username;
        this.website = website;
        this.user_id = user_id;
        this.creations = creations;
        this.coins = coins;
        this.sno = sno;
        this.cover_photo = cover_photo;
    }

    public UserAccountSettings() {

    }



    protected UserAccountSettings(Parcel in) {
        description = in.readString();
        display_name = in.readString();
        followers = in.readLong();
        following = in.readLong();
        posts = in.readLong();
        profile_photo = in.readString();
        username = in.readString();
        website = in.readString();
        user_id = in.readString();
        creations = in.readInt();
        coins = in.readInt();
        sno = in.readInt();
        cover_photo = in.readString();

    }


    public static final Parcelable.Creator<UserAccountSettings> CREATOR = new Creator<UserAccountSettings>() {
        @Override
        public UserAccountSettings createFromParcel(Parcel in) {
            return new UserAccountSettings(in);
        }

        @Override
        public UserAccountSettings[] newArray(int size) {
            return new UserAccountSettings[size];
        }
    };

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getCreations() {
        return creations;
    }

    public void setCreations(int creations) {
        this.creations = creations;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "description='" + description + '\'' +
                ", display_name='" + display_name + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", posts=" + posts +
                ", profile_photo='" + profile_photo + '\'' +
                ", username='" + username + '\'' +
                ", website='" + website + '\'' +
                ", creations='" + creations + '\'' +
                ", coins='" + coins + '\'' +
                ", sno='" + sno + '\'' +
                ", cover_photo=' " + cover_photo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(display_name);
        dest.writeLong(followers);
        dest.writeLong(following);
        dest.writeLong(posts);
        dest.writeString(profile_photo);
        dest.writeString(username);
        dest.writeString(website);
        dest.writeString(user_id);
        dest.writeInt(creations);
        dest.writeInt(coins);
        dest.writeInt(sno);
        dest.writeString(cover_photo);
    }
}
