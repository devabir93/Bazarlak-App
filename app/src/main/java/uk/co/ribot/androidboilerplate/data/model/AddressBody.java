
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddressBody implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer addressId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    public final static Creator<AddressBody> CREATOR = new Creator<AddressBody>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AddressBody createFromParcel(Parcel in) {
            return new AddressBody(in);
        }

        public AddressBody[] newArray(int size) {
            return (new AddressBody[size]);
        }

    };

    protected AddressBody(Parcel in) {
        this.addressId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.fullname = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.address1 = ((String) in.readValue((String.class.getClassLoader())));
        this.address2 = ((String) in.readValue((String.class.getClassLoader())));
        this.postcode = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public AddressBody() {
    }

    /**
     * @param id
     * @param userId
     * @param state
     * @param address1
     * @param address2
     * @param postcode
     * @param fullname
     * @param city
     * @param country
     * @param mobile
     */
    public AddressBody(Integer id, String userId, String fullname, String mobile, String country, String city, String state, String address1, String address2, String postcode) {
        super();
        this.addressId = id;
        this.userId = userId;
        this.fullname = fullname;
        this.mobile = mobile;
        this.country = country;
        this.city = city;
        this.state = state;
        this.address1 = address1;
        this.address2 = address2;
        this.postcode = postcode;
    }

    public AddressBody(String name, String mobile, String country, String city, String state, String address1, String address2, String postCode) {
        this.fullname = name;
        this.mobile = mobile;
        this.country = country;
        this.city = city;
        this.state = state;
        this.address1 = address1;
        this.address2 = address2;
        this.postcode = postCode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer id) {
        this.addressId = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return
                fullname + "\n" +
                        mobile + "\n" +
                        country + "\n" +
                        city + "\n" +
                        state + "\n" +
                        address1 + "\n" +
                        address2 + "\n" +
                        postcode + "\n"
                ;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(addressId).append(userId).append(state).append(address1).append(address2).append(postcode).append(fullname).append(city).append(country).append(mobile).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AddressBody) == false) {
            return false;
        }
        AddressBody rhs = ((AddressBody) other);
        return new EqualsBuilder().append(addressId, rhs.addressId).append(userId, rhs.userId).append(state, rhs.state).append(address1, rhs.address1).append(address2, rhs.address2).append(postcode, rhs.postcode).append(fullname, rhs.fullname).append(city, rhs.city).append(country, rhs.country).append(mobile, rhs.mobile).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(addressId);
        dest.writeValue(userId);
        dest.writeValue(fullname);
        dest.writeValue(mobile);
        dest.writeValue(country);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(address1);
        dest.writeValue(address2);
        dest.writeValue(postcode);
    }

    public int describeContents() {
        return 0;
    }

}
