
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Mainvideo implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    public final static Creator<Mainvideo> CREATOR = new Creator<Mainvideo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Mainvideo createFromParcel(Parcel in) {
            return new Mainvideo(in);
        }

        public Mainvideo[] newArray(int size) {
            return (new Mainvideo[size]);
        }

    }
    ;

    protected Mainvideo(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.video = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Mainvideo() {
    }

    /**
     * 
     * @param id
     * @param title
     * @param status
     * @param createdAt
     * @param userId
     * @param video
     */
    public Mainvideo(Integer id, String userId, String title, String video, String status, String createdAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.video = video;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userId", userId).append("title", title).append("video", video).append("status", status).append("createdAt", createdAt).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(title).append(status).append(createdAt).append(userId).append(video).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Mainvideo) == false) {
            return false;
        }
        Mainvideo rhs = ((Mainvideo) other);
        return new EqualsBuilder().append(id, rhs.id).append(title, rhs.title).append(status, rhs.status).append(createdAt, rhs.createdAt).append(userId, rhs.userId).append(video, rhs.video).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userId);
        dest.writeValue(title);
        dest.writeValue(video);
        dest.writeValue(status);
        dest.writeValue(createdAt);
    }

    public int describeContents() {
        return  0;
    }

}
