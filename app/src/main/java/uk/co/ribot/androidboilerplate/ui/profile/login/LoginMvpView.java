package uk.co.ribot.androidboilerplate.ui.profile.login;

import uk.co.ribot.androidboilerplate.data.model.LoginResponse;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;
import uk.co.ribot.androidboilerplate.util.Message;

public interface LoginMvpView extends MvpView {

    void isSuccess(LoginResponse isSuccess);

    void showMessage(boolean showProgress, String Message, Message messageType);

    void showError();

    void onTimeout();

    void onNetworkError();

    void onUnknownError(String message);
}
