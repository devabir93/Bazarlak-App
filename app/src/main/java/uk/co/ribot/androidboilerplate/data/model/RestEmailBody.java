package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestEmailBody {
    @SerializedName("old_email")
    @Expose
    String oldEmail;
    @SerializedName("email")
    @Expose
    String email;


    public RestEmailBody() {
    }

    public RestEmailBody(String oldEmail, String email) {
        this.oldEmail = oldEmail;
        this.email = email;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
