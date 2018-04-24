package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.ProductBody;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.ui.category.CategoryMvpView;

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

    void getProducts(Context context, ProductBody productBody) {
        mDataManager.getProducts(productBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Product> productList) {
                        Timber.d("productList %s", productList);
                        if (productList.size() > 0) {
                            getMvpView().showProducts(productList);
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
                        Timber.e(e, "There was an error while getAllCategories");
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
