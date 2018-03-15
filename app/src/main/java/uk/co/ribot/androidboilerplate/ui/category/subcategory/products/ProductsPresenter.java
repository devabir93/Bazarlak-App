package uk.co.ribot.androidboilerplate.ui.category.subcategory.products;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import uk.co.ribot.androidboilerplate.data.DataManager;
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

}
