package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface ProductsMvpView extends MvpView {


    void showProducts(List<Product> productList);

    void showEmpty();

    void addedToBag(boolean b);
}
