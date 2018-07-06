package uk.co.ribot.androidboilerplate.ui.profile;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;
import uk.co.ribot.androidboilerplate.util.Message;

public interface ProfileMvpView extends MvpView {

    void showMessage(String s);

    void showMessage(boolean b, String string, Message logging);

    void showProgresBar(boolean b);

    void finishActivity(boolean b);
}
