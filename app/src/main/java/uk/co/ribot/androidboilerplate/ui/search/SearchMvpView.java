package uk.co.ribot.androidboilerplate.ui.search;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface SearchMvpView extends MvpView {


    void showEmpty();

    void showSearchResult(List<Product> data);
}
