package uk.co.ribot.androidboilerplate.ui.category;

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
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class CategoryPresenter extends BasePresenter<CategoryMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public CategoryPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CategoryMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    void getAllCategories(Context context) {
        getMvpView().showProgresBar(true);
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
                            getMvpView().showAllCategories(categoryResponse);
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
                        getMvpView().showProgresBar(false);

                    }

                    @Override
                    public void onComplete() {
                        getMvpView().showProgresBar(false);

                    }
                });
    }
}
