package uk.co.ribot.androidboilerplate.ui.bag;

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
import uk.co.ribot.androidboilerplate.data.model.ProductOrder;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class BagPresenter extends BasePresenter<BagMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public BagPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(BagMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getSavedOrders() {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getSavedOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ProductOrder>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<ProductOrder> productOrderList) {
                        Timber.d("productOrderList %s", productOrderList);
                        if (productOrderList != null && productOrderList.size() > 0) {
                            getMvpView().showSavedOrders(productOrderList);
                        } else {
                            getMvpView().showEmpty();
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
                        Timber.e(e, "There was an error while getSearchResult");
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
