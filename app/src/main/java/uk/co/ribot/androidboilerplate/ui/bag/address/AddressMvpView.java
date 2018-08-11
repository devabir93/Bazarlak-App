package uk.co.ribot.androidboilerplate.ui.bag.address;

import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface AddressMvpView extends MvpView {


    void isSuccess(RestResponse restResponse);
}
