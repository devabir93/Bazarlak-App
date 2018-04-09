
package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class UserData {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("token")
    @Expose
    private String token;

    private String password;

    public UserData() {
    }

    public UserData(String name, String email, String mobile, String gender, String profileImage, String updatedAt, String createdAt, Integer id, String token, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.profileImage = profileImage;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
        this.token = token;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("email", email).append("mobile", mobile).append("gender", gender).append("profileImage", profileImage).append("updatedAt", updatedAt).append("createdAt", createdAt).append("id", id).append("token", token).append("password",password).toString();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
        if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
        if (mobile != null ? !mobile.equals(userData.mobile) : userData.mobile != null) return false;
        if (gender != null ? !gender.equals(userData.gender) : userData.gender != null) return false;
        if (profileImage != null ? !profileImage.equals(userData.profileImage) : userData.profileImage != null)
            return false;
        if (updatedAt != null ? !updatedAt.equals(userData.updatedAt) : userData.updatedAt != null)
            return false;
        if (createdAt != null ? !createdAt.equals(userData.createdAt) : userData.createdAt != null)
            return false;
        if (id != null ? !id.equals(userData.id) : userData.id != null) return false;
        if (token != null ? !token.equals(userData.token) : userData.token != null) return false;
        return password != null ? password.equals(userData.password) : userData.password == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (profileImage != null ? profileImage.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
