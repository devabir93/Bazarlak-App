
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CategoriesData {

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categories", categories).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(categories).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CategoriesData)) {
            return false;
        }
        CategoriesData rhs = ((CategoriesData) other);
        return new EqualsBuilder().append(categories, rhs.categories).isEquals();
    }

}
