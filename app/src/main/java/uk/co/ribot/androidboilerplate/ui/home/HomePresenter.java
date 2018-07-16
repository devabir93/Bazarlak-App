package uk.co.ribot.androidboilerplate.ui.home;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.GetProductByIdResponseBody;
import uk.co.ribot.androidboilerplate.data.model.HomePageData;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class HomePresenter extends BasePresenter<HomeMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public HomePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(HomeMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getHomePage(Context context) {
        getMvpView().showProgresBar(true);
        Timber.d("hom prese");
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getHomePage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomePageData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomePageData homePageData) {

                        getMvpView().showVideo(homePageData.getMainvideo());
                        getMvpView().showOffers(homePageData.getOfferproducts());
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

    public void getProductById(String productId) {
        Timber.d("getProductById");
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.getProductById(productId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetProductByIdResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetProductByIdResponseBody productByIdResponseBody) {
                        Timber.d("productByIdResponseBody %s",productByIdResponseBody);

                        if (productByIdResponseBody.getStatus())
                            getMvpView().showOfferProduct(productByIdResponseBody);

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
