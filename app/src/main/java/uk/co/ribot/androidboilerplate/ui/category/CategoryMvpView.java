package uk.co.ribot.androidboilerplate.ui.category;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface CategoryMvpView extends MvpView {


    void showEmpty();

    void showAllCategories(List<Category> categories);
}
