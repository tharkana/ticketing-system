package com.duosoft.duosoftticketingsystem.rest_api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tkodagoda on 7/3/17.
 */

public class UserDetails  implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("landnumber")
    @Expose
    private String landnumber;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter")
    @Expose
    private String twitter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLandnumber() {
        return landnumber;
    }

    public void setLandnumber(String landnumber) {
        this.landnumber = landnumber;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    protected UserDetails(Parcel in) {
        id = in.readString();
        name = in.readString();
        avatar = in.readString();
        phone = in.readString();
        email = in.readString();
        landnumber = in.readString();
        facebook = in.readString();
        twitter = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(avatar);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(landnumber);
        dest.writeString(facebook);
        dest.writeString(twitter);
    }



    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserDetails> CREATOR = new Parcelable.Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(Parcel in) {
            return new UserDetails(in);
        }

        @Override
        public UserDetails[] newArray(int size) {
            return new UserDetails[size];
        }
    };
}
