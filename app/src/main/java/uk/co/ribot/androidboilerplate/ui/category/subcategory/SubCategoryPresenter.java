package uk.co.ribot.androidboilerplate.ui.category.subcategory;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Extrasubcategory;
import uk.co.ribot.androidboilerplate.data.model.Subcategory;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

@ConfigPersistent
public class SubCategoryPresenter extends BasePresenter<SubCategoryMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public SubCategoryPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SubCategoryMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    void getAllSubCategories(Context context, Integer parentCategoryId) {
        mDataManager.getSubCategories(parentCategoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Subcategory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Subcategory> subcategoryList) {
                        Timber.d("subcategoryList %s", subcategoryList);
                        if (subcategoryList.size() > 0) {
                            getMvpView().showAllCategories(subcategoryList);
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

    void getExtraSubCategories(Context context, String subCategoryId) {
        mDataManager.getExtraSubCategories(subCategoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Extrasubcategory>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Extrasubcategory> extrasubcategories) {
                        Timber.d("extrasubcategories %s", extrasubcategories);
                        if (extrasubcategories.size() > 0) {
                            getMvpView().showExtraSubCategories(extrasubcategories);
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

}
