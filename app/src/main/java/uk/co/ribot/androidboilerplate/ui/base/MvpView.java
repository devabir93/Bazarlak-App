package uk.co.ribot.androidboilerplate.ui.base;


import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.ProductOrder; /**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

    void hasActiveInternetConnection(boolean b);

    void onTimeout();

    void onNetworkError();

    void onUnknownError(String message);

    void showProgresBar(boolean b);

    void showSnackBar(String message);

}
