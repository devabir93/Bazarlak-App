package uk.co.ribot.androidboilerplate.ui.bag.address;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.bag.BagMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

@ConfigPersistent
public class AddressPresenter extends BasePresenter<AddressMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public AddressPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AddressMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }
    public void saveAddressData(AddressBody addressBody) {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        mDataManager.updateAddress(addressBody)
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
