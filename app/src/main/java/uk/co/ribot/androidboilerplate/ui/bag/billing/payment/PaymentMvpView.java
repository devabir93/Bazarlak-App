package uk.co.ribot.androidboilerplate.ui.bag.billing.payment;

import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface PaymentMvpView extends MvpView {


    void isSuccess(RestResponse status);
}
