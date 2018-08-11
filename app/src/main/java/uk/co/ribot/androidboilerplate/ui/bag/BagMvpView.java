package uk.co.ribot.androidboilerplate.ui.bag;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface BagMvpView extends MvpView {

    void showSavedOrders(List<ProductOrder> productOrderList);

    void showEmpty();
}
