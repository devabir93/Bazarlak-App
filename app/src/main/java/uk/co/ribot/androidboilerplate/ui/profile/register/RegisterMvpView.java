package uk.co.ribot.androidboilerplate.ui.profile.register;

import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;
import uk.co.ribot.androidboilerplate.util.Message;

public interface RegisterMvpView extends MvpView {

    void isSuccess(RegisterResponse isSuccess);

    void showMessage(boolean showProgress, String Message, Message messageType);

    void showError();
}
