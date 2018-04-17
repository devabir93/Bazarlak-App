
package uk.co.ribot.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ColorFeature extends SugarRecord implements Parcelable {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("sizes")
    @Expose
    private List<Size> sizes = null;

    public ColorFeature() {
    }

    protected ColorFeature(Parcel in) {
        _0 = in.readString();
        _1 = in.readString();
        _2 = in.readString();
        color = in.readString();
        sizes = in.createTypedArrayList(Size.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_0);
        dest.writeString(_1);
        dest.writeString(_2);
        dest.writeString(color);
        dest.writeTypedList(sizes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ColorFeature> CREATOR = new Creator<ColorFeature>() {
        @Override
        public ColorFeature createFromParcel(Parcel in) {
            return new ColorFeature(in);
        }

        @Override
        public ColorFeature[] newArray(int size) {
            return new ColorFeature[size];
        }
    };

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public String get1() {
        return _1;
    }

    public void set1(String _1) {
        this._1 = _1;
    }

    public String get2() {
        return _2;
    }

    public void set2(String _2) {
        this._2 = _2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("_0", _0).append("_1", _1).append("_2", _2).append("color", color).append("sizes", sizes).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sizes).append(color).append(_0).append(_1).append(_2).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ColorFeature) == false) {
            return false;
        }
        ColorFeature rhs = ((ColorFeature) other);
        return new EqualsBuilder().append(sizes, rhs.sizes).append(color, rhs.color).append(_0, rhs._0).append(_1, rhs._1).append(_2, rhs._2).isEquals();
    }

}
