package uk.co.ribot.androidboilerplate.ui.bag.billing;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

@ConfigPersistent
public class BillingAndShippingPresenter extends BasePresenter<BillingAndShippingMvpView> {

    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public BillingAndShippingPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(BillingAndShippingMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

}
