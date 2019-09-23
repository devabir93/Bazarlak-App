
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

public class MyOrders implements Parcelable
{

    @SerializedName("Current")
    @Expose
    private List<Current> current = null;
    @SerializedName("Pervious")
    @Expose
    private List<Current> pervious = null;
    public final static Creator<MyOrders> CREATOR = new Creator<MyOrders>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MyOrders createFromParcel(Parcel in) {
            return new MyOrders(in);
        }

        public MyOrders[] newArray(int size) {
            return (new MyOrders[size]);
        }

    }
    ;

    protected MyOrders(Parcel in) {
        in.readList(this.current, (Current.class.getClassLoader()));
        in.readList(this.pervious, (Current.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public MyOrders() {
    }

    /**
     * 
     * @param current
     * @param pervious
     */
    public MyOrders(List<Current> current, List<Current> pervious) {
        super();
        this.current = current;
        this.pervious = pervious;
    }

    public List<Current> getCurrent() {
        return current;
    }

    public void setCurrent(List<Current> current) {
        this.current = current;
    }

    public List<Current> getPervious() {
        return pervious;
    }

    public void setPervious(List<Current> pervious) {
        this.pervious = pervious;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("current", current).append("pervious", pervious).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(current).append(pervious).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MyOrders) == false) {
            return false;
        }
        MyOrders rhs = ((MyOrders) other);
        return new EqualsBuilder().append(current, rhs.current).append(pervious, rhs.pervious).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(current);
        dest.writeList(pervious);
    }

    public int describeContents() {
        return  0;
    }

}
