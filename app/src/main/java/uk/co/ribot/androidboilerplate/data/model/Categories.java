
package uk.co.ribot.androidboilerplate.data.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Categories implements Parcelable
{

    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("data")
    @Expose
    private List<Category> data = null;
    @SerializedName("first_page_url")
    @Expose
    private String firstPageUrl;
    @SerializedName("from")
    @Expose
    private Integer from;
    @SerializedName("last_page")
    @Expose
    private Integer lastPage;
    @SerializedName("last_page_url")
    @Expose
    private String lastPageUrl;
    @SerializedName("next_page_url")
    @Expose
    private Object nextPageUrl;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("prev_page_url")
    @Expose
    private Object prevPageUrl;
    @SerializedName("to")
    @Expose
    private Integer to;
    @SerializedName("total")
    @Expose
    private Integer total;
    public final static Creator<Categories> CREATOR = new Creator<Categories>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        public Categories[] newArray(int size) {
            return (new Categories[size]);
        }

    }
    ;

    protected Categories(Parcel in) {
        this.currentPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.data, (Category.class.getClassLoader()));
        this.firstPageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.from = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lastPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lastPageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.nextPageUrl = ((Object) in.readValue((Object.class.getClassLoader())));
        this.path = ((String) in.readValue((String.class.getClassLoader())));
        this.perPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.prevPageUrl = ((Object) in.readValue((Object.class.getClassLoader())));
        this.to = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Categories() {
    }

    /**
     * 
     * @param total
     * @param to
     * @param lastPage
     * @param nextPageUrl
     * @param prevPageUrl
     * @param firstPageUrl
     * @param path
     * @param data
     * @param perPage
     * @param from
     * @param currentPage
     * @param lastPageUrl
     */
    public Categories(Integer currentPage, List<Category> data, String firstPageUrl, Integer from, Integer lastPage, String lastPageUrl, Object nextPageUrl, String path, Integer perPage, Object prevPageUrl, Integer to, Integer total) {
        super();
        this.currentPage = currentPage;
        this.data = data;
        this.firstPageUrl = firstPageUrl;
        this.from = from;
        this.lastPage = lastPage;
        this.lastPageUrl = lastPageUrl;
        this.nextPageUrl = nextPageUrl;
        this.path = path;
        this.perPage = perPage;
        this.prevPageUrl = prevPageUrl;
        this.to = to;
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(Object prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("currentPage", currentPage).append("data", data).append("firstPageUrl", firstPageUrl).append("from", from).append("lastPage", lastPage).append("lastPageUrl", lastPageUrl).append("nextPageUrl", nextPageUrl).append("path", path).append("perPage", perPage).append("prevPageUrl", prevPageUrl).append("to", to).append("total", total).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(total).append(to).append(lastPage).append(nextPageUrl).append(prevPageUrl).append(firstPageUrl).append(data).append(perPage).append(from).append(path).append(currentPage).append(lastPageUrl).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Categories) == false) {
            return false;
        }
        Categories rhs = ((Categories) other);
        return new EqualsBuilder().append(total, rhs.total).append(to, rhs.to).append(lastPage, rhs.lastPage).append(nextPageUrl, rhs.nextPageUrl).append(prevPageUrl, rhs.prevPageUrl).append(firstPageUrl, rhs.firstPageUrl).append(data, rhs.data).append(perPage, rhs.perPage).append(from, rhs.from).append(path, rhs.path).append(currentPage, rhs.currentPage).append(lastPageUrl, rhs.lastPageUrl).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(currentPage);
        dest.writeList(data);
        dest.writeValue(firstPageUrl);
        dest.writeValue(from);
        dest.writeValue(lastPage);
        dest.writeValue(lastPageUrl);
        dest.writeValue(nextPageUrl);
        dest.writeValue(path);
        dest.writeValue(perPage);
        dest.writeValue(prevPageUrl);
        dest.writeValue(to);
        dest.writeValue(total);
    }

    public int describeContents() {
        return  0;
    }

}
