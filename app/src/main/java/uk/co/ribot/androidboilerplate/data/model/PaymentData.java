
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PaymentData implements Parcelable {

    @SerializedName("PaymentAddress")
    @Expose
    private List<AddressBody> paymentAddress = null;
    @SerializedName("PaymentVisa")
    @Expose
    private PaymentDataBody paymentVisa;
    public final static Creator<PaymentData> CREATOR = new Creator<PaymentData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaymentData createFromParcel(Parcel in) {
            return new PaymentData(in);
        }

        public PaymentData[] newArray(int size) {
            return (new PaymentData[size]);
        }

    };

    protected PaymentData(Parcel in) {
        in.readList(this.paymentAddress, (uk.co.ribot.androidboilerplate.data.model.AddressBody.class.getClassLoader()));
        this.paymentVisa = ((PaymentDataBody) in.readValue((PaymentDataBody.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public PaymentData() {
    }

    public PaymentData(List<AddressBody> paymentAddress, PaymentDataBody paymentVisa) {
        this.paymentAddress = paymentAddress;
        this.paymentVisa = paymentVisa;
    }

    public List<AddressBody> getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(List<AddressBody> paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public PaymentDataBody getPaymentVisa() {
        return paymentVisa;
    }

    public void setPaymentVisa(PaymentDataBody paymentVisa) {
        this.paymentVisa = paymentVisa;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("paymentAddress", paymentAddress).append("paymentVisa", paymentVisa).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(paymentAddress).append(paymentVisa).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentData) == false) {
            return false;
        }
        PaymentData rhs = ((PaymentData) other);
        return new EqualsBuilder().append(paymentAddress, rhs.paymentAddress).append(paymentVisa, rhs.paymentVisa).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(paymentAddress);
        dest.writeValue(paymentVisa);
    }

    public int describeContents() {
        return 0;
    }

}
