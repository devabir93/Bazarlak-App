package uk.co.ribot.androidboilerplate.ui.category.subcategory.filter;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.FilterBody;
import uk.co.ribot.androidboilerplate.data.model.FilterDataResponse;
import uk.co.ribot.androidboilerplate.data.model.FilterProductResponse;
import uk.co.ribot.androidboilerplate.data.model.Products;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class FiltersDataPresenter extends BasePresenter<FiltersDataMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public FiltersDataPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(FiltersDataMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    void getFiltersData(String subCategory, String extraSubCategory) {
        mDataManager.getFiltersData(subCategory, extraSubCategory)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FilterDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FilterDataResponse filterDataResponse) {
                        Timber.d("filterDataResponse %s",filterDataResponse);
                        if (filterDataResponse != null) {
                            getMvpView().showBrands(filterDataResponse.getItems().getBrand());
                            getMvpView().showSizes(filterDataResponse.getItems().getFilterSize());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void getFilteredProducts(FilterBody filterBody) {
        Timber.d("getFilteredProducts");
        mDataManager.getFilteredProducts(filterBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FilterProductResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FilterProductResponse filterProductResponse) {
                        if (filterProductResponse.getStatus()) {
                            getMvpView().showFilterdProduct(filterProductResponse.getItems().get(0).getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
