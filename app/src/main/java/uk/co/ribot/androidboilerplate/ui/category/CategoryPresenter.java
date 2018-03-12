package uk.co.ribot.androidboilerplate.ui.category;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

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

}
