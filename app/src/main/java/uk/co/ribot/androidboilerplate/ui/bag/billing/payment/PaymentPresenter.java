package uk.co.ribot.androidboilerplate.ui.bag.billing.payment;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.PaymentDataBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.bag.billing.BillingAndShippingMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class PaymentPresenter extends BasePresenter<PaymentMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public PaymentPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(PaymentMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void savePaymentData(PaymentDataBody paymentDataBody) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.setPaymentData(paymentDataBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RestResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RestResponse restResponse) {
                        getMvpView().isSuccess(restResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showProgresBar(false);

                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgresBar(false);

                    }
                });
    }

}
