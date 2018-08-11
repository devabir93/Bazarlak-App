package uk.co.ribot.androidboilerplate.ui.profile.your_order;

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
import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.data.model.RestEmailBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.ui.profile.login.LoginMvpView;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class YourOrdersPresenter extends BasePresenter<YourOrdersMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public YourOrdersPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(YourOrdersMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getOrders(final Context context) {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        getMvpView().showProgresBar(false);

//        mDataManager.getOrders()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<RegisterResponse>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        mDisposable = d;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull RegisterResponse loginResponse) {
//                        getMvpView().showProgresBar(false);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        getMvpView().showProgresBar(false);
//                        if (e instanceof HttpException) {
//                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
//                           // view.onUnknownError(getErrorMessage(responseBody));
//                        } else if (e instanceof SocketTimeoutException) {
//                            getMvpView().onTimeout();
//                        } else if (e instanceof IOException) {
//                            getMvpView().onNetworkError();
//                        } else {
//                            //getMvpView().showError();
//                            getMvpView().onUnknownError(e.getMessage());
//                        }
//                        Timber.e(e, "There was an error while login");
//                       // getMvpView().showError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        getMvpView().showProgresBar(false);
//                    }
//                });
    }

}
