package uk.co.ribot.androidboilerplate.ui.profile.login;

import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;
import uk.co.ribot.androidboilerplate.util.Message;

public interface LoginMvpView extends MvpView {

    void isSuccess(RegisterResponse isSuccess);

    void showMessage(boolean showProgress, String Message, Message messageType);

    void showError();

    void onTimeout();

    void onNetworkError();

    void onUnknownError(String message);

    void showProgresBar(boolean b);

    void finishActivity(boolean b);

    void showMessage(String message);
}
