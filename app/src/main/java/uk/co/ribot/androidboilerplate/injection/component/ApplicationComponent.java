package uk.co.ribot.androidboilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.SyncService;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.local.UserSession;
import uk.co.ribot.androidboilerplate.data.remote.BazarlakService;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationModule;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.LanguageUtils;
import uk.co.ribot.androidboilerplate.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);
    void inject(BaseFragment baseFragment);

    @ApplicationContext
    Context context();
    Application application();
    BazarlakService ribotsService();
    PreferencesHelper preferencesHelper();
    DataManager dataManager();
    RxEventBus eventBus();
    LanguageUtils language_utils();
    UserSession USER_SESSION();

}
