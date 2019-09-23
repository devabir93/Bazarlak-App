
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaymentANDAddressResponse implements Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("PaymentData")
    @Expose
    private PaymentData paymentData;
    public final static Creator<PaymentANDAddressResponse> CREATOR = new Creator<PaymentANDAddressResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PaymentANDAddressResponse createFromParcel(Parcel in) {
            return new PaymentANDAddressResponse(in);
        }

        public PaymentANDAddressResponse[] newArray(int size) {
            return (new PaymentANDAddressResponse[size]);
        }

    }
    ;

    protected PaymentANDAddressResponse(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentData = ((PaymentData) in.readValue((PaymentData.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PaymentANDAddressResponse() {
    }

    /**
     * 
     * @param message
     * @param paymentData
     * @param status
     * @param code
     */
    public PaymentANDAddressResponse(Boolean status, Integer code, String message, PaymentData paymentData) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.paymentData = paymentData;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("code", code).append("message", message).append("paymentData", paymentData).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(paymentData).append(status).append(code).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentANDAddressResponse) == false) {
            return false;
        }
        PaymentANDAddressResponse rhs = ((PaymentANDAddressResponse) other);
        return new EqualsBuilder().append(message, rhs.message).append(paymentData, rhs.paymentData).append(status, rhs.status).append(code, rhs.code).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(code);
        dest.writeValue(message);
        dest.writeValue(paymentData);
    }

    public int describeContents() {
        return  0;
    }

}
