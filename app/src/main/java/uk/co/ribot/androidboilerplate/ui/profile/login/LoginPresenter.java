package uk.co.ribot.androidboilerplate.ui.profile.login;

import android.content.Context;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.data.model.LoginResponse;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void login(final Context context, final UserData userData) {
        getMvpView().showMessage(true, context.getResources().getString(R.string.logging_message), Message.LOGGING);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.makeLogin("", userData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull LoginResponse loginResponse) {
                        getMvpView().isSuccess(loginResponse);
                        //getMvpView().showMessage(true, context.getResources().getString(R.string.login_success_message), Message.SUCCESS);
                        getMvpView().showMessage(false, context.getResources().getString(R.string.logging_message), Message.LOGGING);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().showMessage(false, context.getResources().getString(R.string.signup_message),Message.LOGGING);
                        if (e instanceof HttpException) {
                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
                           // view.onUnknownError(getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            getMvpView().onTimeout();
                        } else if (e instanceof IOException) {
                            getMvpView().onNetworkError();
                        } else {
                            //getMvpView().showError();
                            getMvpView().onUnknownError(e.getMessage());
                        }
                        Timber.e(e, "There was an error while login");
                       // getMvpView().showError();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showMessage(false, context.getResources().getString(R.string.logging_message), Message.LOGGING);
                    }
                });
    }

}
