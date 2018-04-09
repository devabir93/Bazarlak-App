package uk.co.ribot.androidboilerplate.ui.profile.register;

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
import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class RegisterPresenter extends BasePresenter<RegisterMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RegisterMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }
    public void register(final Context context, final UserData userData) {
        getMvpView().showMessage(true, context.getResources().getString(R.string.signup_message),Message.LOGGING);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.makeRegister(userData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull RegisterResponse registerResponse) {
                        getMvpView().isSuccess(registerResponse);
                        //getMvpView().showMessage(true, context.getResources().getString(R.string.login_success_message), Message.SUCCESS);
                        getMvpView().showMessage(false, context.getResources().getString(R.string.signup_message),Message.LOGGING);
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
                          //  getMvpView().showError();
                            getMvpView().onUnknownError(e.getMessage());
                        }
                        Timber.e(e, "There was an error while login");
                        //getMvpView().showError();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showMessage(false, context.getResources().getString(R.string.signup_message),Message.LOGGING);
                    }
                });
    }

}
