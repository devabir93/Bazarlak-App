
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
    @SerializedName("expiration")
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
    @SerializedName("type")
    @Expose
    private String type;

    protected PaymentDataBody(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        name = in.readString();
        cardNumber = in.readString();
        expiration = in.readString();
        isdefault = in.readString();
        cvc = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(name);
        dest.writeString(cardNumber);
        dest.writeString(expiration);
        dest.writeString(isdefault);
        dest.writeString(cvc);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PaymentDataBody> CREATOR = new Creator<PaymentDataBody>() {
        @Override
        public PaymentDataBody createFromParcel(Parcel in) {
            return new PaymentDataBody(in);
        }

        @Override
        public PaymentDataBody[] newArray(int size) {
            return new PaymentDataBody[size];
        }
    };

    @Override
    public String toString() {
        return "PaymentDataBody{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiration='" + expiration + '\'' +
                ", isdefault='" + isdefault + '\'' +
                ", cvc='" + cvc + '\'' +
                ", id=" + id +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentDataBody that = (PaymentDataBody) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null)
            return false;
        if (expiration != null ? !expiration.equals(that.expiration) : that.expiration != null)
            return false;
        if (isdefault != null ? !isdefault.equals(that.isdefault) : that.isdefault != null)
            return false;
        if (cvc != null ? !cvc.equals(that.cvc) : that.cvc != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (expiration != null ? expiration.hashCode() : 0);
        result = 31 * result + (isdefault != null ? isdefault.hashCode() : 0);
        result = 31 * result + (cvc != null ? cvc.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
