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
import uk.co.ribot.androidboilerplate.data.model.FilterDataResponse;
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

    void getFiltersData() {
        mDataManager.getFiltersData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<FilterDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FilterDataResponse filterDataResponse) {
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

    void getFiltersCategory() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(List<Category> categoryResponse) {
                        Timber.d("categories %s",categoryResponse);
                        if (categoryResponse.size()>0) {
                            getMvpView().showCategories(categoryResponse);
                        } else
                            getMvpView().showEmpty();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error while getAllCategories");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
