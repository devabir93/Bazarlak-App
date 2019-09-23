package uk.co.ribot.androidboilerplate.ui.profile.your_order;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Current;
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface YourOrdersMvpView extends MvpView {

    void showEmpty();

    void showOrders(List<ProductOrder> productOrderList);

    void showCurrentOrders(List<Current> current);

    void showPreviousOrders(List<Current> pervious);
}
