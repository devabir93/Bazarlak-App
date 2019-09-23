package uk.co.ribot.androidboilerplate.ui.bag.billing;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.PaymentDataBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface BillingAndShippingMvpView extends MvpView {


    void showEmptyAddress();

    void showPayment(PaymentDataBody paymentVisa);

    void showAddress(List<AddressBody> paymentAddress);

    void showEmptyPayemnt();

    void isSuccess(RestResponse restResponse);

    void showMessage(String msg);
}
