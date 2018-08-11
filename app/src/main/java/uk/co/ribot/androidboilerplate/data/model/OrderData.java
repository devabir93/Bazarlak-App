package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderData {
    @SerializedName("data")
    @Expose
    private ProductOrder productOrder;

    public OrderData(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    public OrderData() {
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }
}
