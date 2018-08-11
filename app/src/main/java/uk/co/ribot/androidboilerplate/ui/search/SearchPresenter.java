package uk.co.ribot.androidboilerplate.ui.search;

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
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Products;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class SearchPresenter extends BasePresenter<SearchMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public SearchPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SearchMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getSearchResult(final String key, String page) {
        getMvpView().showProgresBar(true);
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getSearchResult(key, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Products>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Products products) {
                        if (products.getData() != null && products.getData().size() > 0) {
                            getMvpView().showSearchResult(products.getData());
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
