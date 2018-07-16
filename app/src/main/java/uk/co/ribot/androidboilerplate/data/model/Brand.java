
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Brand extends SugarRecord implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer brandId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("trader_id")
    @Expose
    private String traderId;

    public Brand() {
    }

    protected Brand(Parcel in) {
        if (in.readByte() == 0) {
            brandId = null;
        } else {
            brandId = in.readInt();
        }
        name = in.readString();
        traderId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (brandId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(brandId);
        }
        dest.writeString(name);
        dest.writeString(traderId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Brand> CREATOR = new Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", brandId).append("traderId", traderId).append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(brandId).append(traderId).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Brand) == false) {
            return false;
        }
        Brand rhs = ((Brand) other);
        return new EqualsBuilder().append(brandId, rhs.brandId).append(traderId,rhs.traderId).append(name, rhs.name).isEquals();
    }

    public Brand(Integer brandId, String name, String traderId) {
        this.brandId = brandId;
        this.name = name;
        this.traderId = traderId;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }
}
