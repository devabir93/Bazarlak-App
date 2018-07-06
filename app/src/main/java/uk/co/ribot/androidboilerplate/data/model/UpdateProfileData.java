
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateProfileData implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
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
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    public final static Creator<UpdateProfileData> CREATOR = new Creator<UpdateProfileData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UpdateProfileData createFromParcel(Parcel in) {
            return new UpdateProfileData(in);
        }

        public UpdateProfileData[] newArray(int size) {
            return (new UpdateProfileData[size]);
        }

    }
    ;

    protected UpdateProfileData(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.profileImage = ((String) in.readValue((String.class.getClassLoader())));
        this.details = ((Object) in.readValue((Object.class.getClassLoader())));
        this.userType = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.deletedAt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public UpdateProfileData() {
    }

    /**
     * 
     * @param accessToken
     * @param profileImage
     * @param userType
     * @param id
     * @param updatedAt
     * @param details
     * @param email
     * @param createdAt
     * @param name
     * @param deletedAt
     * @param active
     * @param gender
     * @param mobile
     */
    public UpdateProfileData(Integer id, String email, String name, String mobile, String profileImage, Object details, String userType, String active, String gender, String createdAt, String updatedAt, Object deletedAt, String accessToken) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.mobile = mobile;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        return new ToStringBuilder(this).append("id", id).append("email", email).append("name", name).append("mobile", mobile).append("profileImage", profileImage).append("details", details).append("userType", userType).append("active", active).append("gender", gender).append("createdAt", createdAt).append("updatedAt", updatedAt).append("deletedAt", deletedAt).append("accessToken", accessToken).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(accessToken).append(profileImage).append(userType).append(updatedAt).append(id).append(details).append(email).append(createdAt).append(name).append(deletedAt).append(gender).append(active).append(mobile).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UpdateProfileData) == false) {
            return false;
        }
        UpdateProfileData rhs = ((UpdateProfileData) other);
        return new EqualsBuilder().append(accessToken, rhs.accessToken).append(profileImage, rhs.profileImage).append(userType, rhs.userType).append(updatedAt, rhs.updatedAt).append(id, rhs.id).append(details, rhs.details).append(email, rhs.email).append(createdAt, rhs.createdAt).append(name, rhs.name).append(deletedAt, rhs.deletedAt).append(gender, rhs.gender).append(active, rhs.active).append(mobile, rhs.mobile).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(email);
        dest.writeValue(name);
        dest.writeValue(mobile);
        dest.writeValue(profileImage);
        dest.writeValue(details);
        dest.writeValue(userType);
        dest.writeValue(active);
        dest.writeValue(gender);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(deletedAt);
        dest.writeValue(accessToken);
    }

    public int describeContents() {
        return  0;
    }

}
