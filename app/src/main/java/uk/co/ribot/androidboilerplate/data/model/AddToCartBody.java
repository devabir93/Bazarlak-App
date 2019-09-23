
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

public class AddToCartBody {

    @SerializedName("data")
    @Expose
    private List<CartData> cartData = null;

    /**
     * No args constructor for use in serialization
     */
    public AddToCartBody() {
    }

    public List<CartData> getCartData() {
        return cartData;
    }

    public void setCartData(List<CartData> cartData) {
        this.cartData = cartData;
    }


}
