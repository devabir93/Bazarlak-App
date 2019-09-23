package uk.co.ribot.androidboilerplate;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.orm.SugarContext;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.injection.component.ApplicationComponent;
import uk.co.ribot.androidboilerplate.injection.component.DaggerApplicationComponent;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationModule;
import uk.co.ribot.androidboilerplate.util.Language;
import uk.co.ribot.androidboilerplate.util.LanguageUtils;

public class BazarlakeApplication extends Application {

    ApplicationComponent mApplicationComponent;
    private PreferencesHelper sharedPreferences;
    private LanguageUtils mLanguageUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            // Fabric.with(this, new Crashlytics());
        }
        SugarContext.init(this);
        // Install Calligraphy library
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/din_regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
//        SqlScoutServer.create(this, getPackageName());
        sharedPreferences = new PreferencesHelper(this);
        mLanguageUtils = new LanguageUtils(this, sharedPreferences);
        setLang();
        Stetho.initializeWithDefaults(this);
    }

    private Locale locale = null;

    private Context setLang() {
        String currnetLang = sharedPreferences.getWithKey(Language.KEY());
        if (currnetLang.equals("")) {
            // currnetLang = Language.ARABIC();
//            to get device lange
            locale = getResources().getConfiguration().locale;
            currnetLang = locale.toString().substring(0, 2);
        }

        return mLanguageUtils.setLocale(currnetLang);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLang();
    }

    public static BazarlakeApplication get(Context context) {
        return (BazarlakeApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
