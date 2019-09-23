package uk.co.ribot.androidboilerplate.ui.bag.billing;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.AddToCartBody;
import uk.co.ribot.androidboilerplate.data.model.CartBody;
import uk.co.ribot.androidboilerplate.data.model.PaymentData;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class BillingAndShippingPresenter extends BasePresenter<BillingAndShippingMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public BillingAndShippingPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(BillingAndShippingMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getPaymentAddressData() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getPaymentAndAddressData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PaymentData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull PaymentData paymentData) {
                        Timber.d("getPaymentAddressData %s", paymentData);
                        if (paymentData.getPaymentAddress() != null && paymentData.getPaymentAddress().size() > 0) {
                            getMvpView().showAddress(paymentData.getPaymentAddress());
                        } else
                            getMvpView().showEmptyAddress();
                        if (paymentData.getPaymentVisa() != null) {
                            getMvpView().showPayment(paymentData.getPaymentVisa());
                        } else
                            getMvpView().showEmptyPayemnt();

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
                        Timber.e(e, "There was an error while getPaymentAddressData");
                        //getMvpView().showError();
                    }

                    @Override
                    public void onComplete() {
                        ;
                    }
                });
    }

    public void buyOrder(CartBody addToCartBody) {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.saveOrders(addToCartBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RestResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull RestResponse restResponse) {
                        Timber.d("buyOrder %s", restResponse);
                        getMvpView().isSuccess(restResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ResponseBody responseBody = ((HttpException) e).response().errorBody();
                        try {
                            JSONObject jObjError = new JSONObject(responseBody.string());
                            Timber.d("responseBody %s", jObjError.getString("message"));
                            Timber.d("responseBody2 %s", jObjError.getString("code"));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        if (e instanceof HttpException) {
                            // view.onUnknownError(getErrorMessage(responseBody));
                        } else if (e instanceof SocketTimeoutException) {
                            getMvpView().onTimeout();
                        } else if (e instanceof IOException) {
                            getMvpView().onNetworkError();
                        } else {
                            //  getMvpView().showError();
                            getMvpView().onUnknownError(e.getMessage());
                        }

                        Timber.e(e, "There was an error while buyOrder");
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
