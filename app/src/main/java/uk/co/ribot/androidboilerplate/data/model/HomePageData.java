
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HomePageData implements Parcelable
{

    @SerializedName("mainvideo")
    @Expose
    private Mainvideo mainvideo;
    @SerializedName("offerproducts")
    @Expose
    private List<Offerproduct> offerproducts = null;
    public final static Creator<HomePageData> CREATOR = new Creator<HomePageData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public HomePageData createFromParcel(Parcel in) {
            return new HomePageData(in);
        }

        public HomePageData[] newArray(int size) {
            return (new HomePageData[size]);
        }

    }
    ;

    protected HomePageData(Parcel in) {
        this.mainvideo = ((Mainvideo) in.readValue((Mainvideo.class.getClassLoader())));
        in.readList(this.offerproducts, (Offerproduct.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public HomePageData() {
    }

    /**
     * 
     * @param offerproducts
     * @param mainvideo
     */
    public HomePageData(Mainvideo mainvideo, List<Offerproduct> offerproducts) {
        super();
        this.mainvideo = mainvideo;
        this.offerproducts = offerproducts;
    }

    public Mainvideo getMainvideo() {
        return mainvideo;
    }

    public void setMainvideo(Mainvideo mainvideo) {
        this.mainvideo = mainvideo;
    }

    public List<Offerproduct> getOfferproducts() {
        return offerproducts;
    }

    public void setOfferproducts(List<Offerproduct> offerproducts) {
        this.offerproducts = offerproducts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("mainvideo", mainvideo).append("offerproducts", offerproducts).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(offerproducts).append(mainvideo).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HomePageData) == false) {
            return false;
        }
        HomePageData rhs = ((HomePageData) other);
        return new EqualsBuilder().append(offerproducts, rhs.offerproducts).append(mainvideo, rhs.mainvideo).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mainvideo);
        dest.writeList(offerproducts);
    }

    public int describeContents() {
        return  0;
    }

}
