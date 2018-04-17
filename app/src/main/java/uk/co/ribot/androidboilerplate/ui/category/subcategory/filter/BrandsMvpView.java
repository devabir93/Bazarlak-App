package uk.co.ribot.androidboilerplate.ui.category.subcategory.filter;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Brand;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface BrandsMvpView extends MvpView {

    void showFilteredProducts(List<Product> productList);

    void showEmpty();

    void showBrands(List<Brand> brandList);

    void showCategories(List<Category> categoryList);

    void showColors();

    void showSizes();
}
