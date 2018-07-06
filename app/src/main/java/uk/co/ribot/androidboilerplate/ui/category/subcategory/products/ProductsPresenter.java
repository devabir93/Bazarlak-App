package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.ProductResponse;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

@ConfigPersistent
public class ProductsPresenter extends BasePresenter<ProductsMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public ProductsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ProductsMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    void getProducts(Context context, String categoryId, String subCategoryId, String extracategoryId, String page) {
        mDataManager.getProducts(categoryId, subCategoryId, extracategoryId, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ProductResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductResponse productResponses) {
                        Timber.d("productList %s", productResponses);
                        if (productResponses.getStatus()) {
                            getMvpView().showProducts(productResponses.getData().getProducts().getData());
                        } else
                            getMvpView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        getMvpView().showMessage(false, context.getResources().getString(R.string.signup_message), Message.LOGGING);
//                        if (e instanceof HttpException) {
//                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
//                            // view.onUnknownError(getErrorMessage(responseBody));
//                        } else if (e instanceof SocketTimeoutException) {
//                            getMvpView().onTimeout();
//                        } else if (e instanceof IOException) {
//                            getMvpView().onNetworkError();
//                        } else {
//                            //getMvpView().showError();
//                            getMvpView().onUnknownError(e.getMessage());
//                        }
//                        // getMvpView().showError();
                        Timber.e(e, "There was an error while getProducts");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void addToBag(String ProductId) {

        getMvpView().addedToBag(true);

    }

}
