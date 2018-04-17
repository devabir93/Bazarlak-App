
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BrandData {

    @SerializedName("brands")
    @Expose
    private List<Brand> brands = null;

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("brands", brands).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(brands).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BrandData)) {
            return false;
        }
        BrandData rhs = ((BrandData) other);
        return new EqualsBuilder().append(brands, rhs.brands).isEquals();
    }

}
