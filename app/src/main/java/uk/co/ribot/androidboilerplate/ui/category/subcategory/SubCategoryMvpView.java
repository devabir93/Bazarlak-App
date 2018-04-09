package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.Extrasubcategory;
import uk.co.ribot.androidboilerplate.data.model.Subcategory;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface SubCategoryMvpView extends MvpView {


    void showEmpty();

    void showAllCategories(List<Subcategory> categories);

    void showExtraSubCategories(List<Extrasubcategory> extrasubcategories);
}
