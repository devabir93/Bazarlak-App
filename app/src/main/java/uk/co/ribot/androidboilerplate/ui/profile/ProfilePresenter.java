package uk.co.ribot.androidboilerplate.ui.profile;

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
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.data.model.RestEmailBody;
import uk.co.ribot.androidboilerplate.data.model.RestPasswordBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.data.model.UpdateProfileResponse;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class ProfilePresenter extends BasePresenter<ProfileMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;
    Context context;
    @Inject
    PreferencesHelper preferencesHelper;

    @Inject
    public ProfilePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ProfileMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void updateProfileInfo(final UserData userData) {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.updateProfile(userData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UpdateProfileResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull UpdateProfileResponse updateProfileResponse) {
                        if (!updateProfileResponse.getStatus()) {
                            getMvpView().showMessage(updateProfileResponse.getMessage());
                        } else {
                            getMvpView().showMessage(updateProfileResponse.getMessage());
                            getMvpView().finishActivity(true);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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
                        Timber.e(e, "There was an error while updateProfileInfo");
                        //getMvpView().showError();
                        getMvpView().showProgresBar(false);
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgresBar(false);
                    }
                });
    }

    public void resetEmail(final RestEmailBody restEmailBody) {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.resetEmail(restEmailBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RestResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull RestResponse updateProfileResponse) {
                        if (!updateProfileResponse.getStatus()) {
                            getMvpView().showMessage(updateProfileResponse.getMessage());
                        } else {
                            getMvpView().showSnackBar(updateProfileResponse.getMessage());
                            getMvpView().finishActivity(true);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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
                        Timber.e(e, "There was an error while resetEmail");
                        //getMvpView().showError();
                        getMvpView().showProgresBar(false);
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgresBar(false);
                    }
                });
    }

    public void resetPassword(final RestPasswordBody restPasswordBody) {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.resetPassword(restPasswordBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RestResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull RestResponse updateProfileResponse) {
                        if (!updateProfileResponse.getStatus()) {
                            getMvpView().showMessage(updateProfileResponse.getMessage());
                        } else {
                            getMvpView().showMessage(updateProfileResponse.getMessage());
                            getMvpView().finishActivity(true);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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
                        Timber.e(e, "There was an error while resetPassword");
                        //getMvpView().showError();
                        getMvpView().showProgresBar(false);
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgresBar(false);
                    }
                });
    }

    public void signout() {
        preferencesHelper.removeUserSession();
    }

    public void updateAddress(AddressBody addressBody) {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.updateAddress(addressBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RestResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull RestResponse updateProfileResponse) {
                        if (!updateProfileResponse.getStatus()) {
                            getMvpView().showMessage(updateProfileResponse.getMessage());
                        } else {
                            getMvpView().showMessage(updateProfileResponse.getMessage());
                            getMvpView().finishActivity(true);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
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
                        Timber.e(e, "There was an error while updateAddress");
                        //getMvpView().showError();
                        getMvpView().showProgresBar(false);
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgresBar(false);
                    }
                });
    }
}
