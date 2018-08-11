
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaymentDataBody implements Parcelable {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("card_number")
    @Expose
    private String cardNumber;
    @SerializedName("expiration_date")
    @Expose
    private String expiration;
    @SerializedName("isdefault")
    @Expose
    private String isdefault;
    @SerializedName("cvc")
    @Expose
    private String cvc;
    @SerializedName("id")
    @Expose
    private Integer id;
    public final static Creator<PaymentDataBody> CREATOR = new Creator<PaymentDataBody>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentDataBody createFromParcel(Parcel in) {
            return new PaymentDataBody(in);
        }

        public PaymentDataBody[] newArray(int size) {
            return (new PaymentDataBody[size]);
        }

    };

    protected PaymentDataBody(Parcel in) {
        this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.cardNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.expiration = ((String) in.readValue((String.class.getClassLoader())));
        this.isdefault = ((String) in.readValue((String.class.getClassLoader())));
        this.cvc = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public PaymentDataBody() {
    }

    /**
     * @param id
     * @param isdefault
     * @param expiration
     * @param name
     * @param userId
     * @param cvc
     * @param cardNumber
     */
    public PaymentDataBody(Integer userId, String name, String cardNumber, String expiration, String isdefault, String cvc, Integer id) {
        super();
        this.userId = userId;
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.isdefault = isdefault;
        this.cvc = cvc;
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("name", name).append("cardNumber", cardNumber).append("expiration", expiration).append("isdefault", isdefault).append("cvc", cvc).append("id", id).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(isdefault).append(expiration).append(name).append(userId).append(cvc).append(cardNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentDataBody) == false) {
            return false;
        }
        PaymentDataBody rhs = ((PaymentDataBody) other);
        return new EqualsBuilder().append(id, rhs.id).append(isdefault, rhs.isdefault).append(expiration, rhs.expiration).append(name, rhs.name).append(userId, rhs.userId).append(cvc, rhs.cvc).append(cardNumber, rhs.cardNumber).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(name);
        dest.writeValue(cardNumber);
        dest.writeValue(expiration);
        dest.writeValue(isdefault);
        dest.writeValue(cvc);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}
