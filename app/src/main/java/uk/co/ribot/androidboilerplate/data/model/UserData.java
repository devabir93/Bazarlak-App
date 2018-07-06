
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class UserData implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("details")
    @Expose
    private Object details;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("token")
    @Expose
    private String accessToken;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     * No args constructor for use in serialization
     */
    public UserData() {
    }

    /**
     * @param lname
     * @param accessToken
     * @param profileImage
     * @param country
     * @param userType
     * @param updatedAt
     * @param id
     * @param details
     * @param email
     * @param createdAt
     * @param deletedAt
     * @param active
     * @param gender
     * @param fname
     * @param mobile
     */
    public UserData(Integer id, String email, String fname, String lname, String mobile, String country, String profileImage, Object details, String userType, String active, String gender, String createdAt, String updatedAt, Object deletedAt, String accessToken) {
        super();
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.country = country;
        this.profileImage = profileImage;
        this.details = details;
        this.userType = userType;
        this.active = active;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.accessToken = accessToken;
    }

    protected UserData(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        email = in.readString();
        fname = in.readString();
        lname = in.readString();
        mobile = in.readString();
        country = in.readString();
        profileImage = in.readString();
        userType = in.readString();
        active = in.readString();
        gender = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        accessToken = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(email);
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(mobile);
        dest.writeString(country);
        dest.writeString(profileImage);
        dest.writeString(userType);
        dest.writeString(active);
        dest.writeString(gender);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(accessToken);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("email", email).append("fname", fname).append("lname", lname).append("mobile", mobile).append("country", country).append("profileImage", profileImage).append("details", details).append("userType", userType).append("active", active).append("gender", gender).append("createdAt", createdAt).append("updatedAt", updatedAt).append("deletedAt", deletedAt).append("accessToken", accessToken).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lname).append(accessToken).append(profileImage).append(userType).append(country).append(updatedAt).append(id).append(details).append(email).append(createdAt).append(deletedAt).append(gender).append(active).append(fname).append(mobile).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserData) == false) {
            return false;
        }
        UserData rhs = ((UserData) other);
        return new EqualsBuilder().append(lname, rhs.lname).append(accessToken, rhs.accessToken).append(profileImage, rhs.profileImage).append(userType, rhs.userType).append(country, rhs.country).append(updatedAt, rhs.updatedAt).append(id, rhs.id).append(details, rhs.details).append(email, rhs.email).append(createdAt, rhs.createdAt).append(deletedAt, rhs.deletedAt).append(gender, rhs.gender).append(active, rhs.active).append(fname, rhs.fname).append(mobile, rhs.mobile).isEquals();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
