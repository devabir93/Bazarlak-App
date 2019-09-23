package uk.co.ribot.androidboilerplate.ui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.ProgressView;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.ribot.androidboilerplate.BazarlakeApplication;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.local.UserSession;
import uk.co.ribot.androidboilerplate.injection.component.ActivityComponent;
import uk.co.ribot.androidboilerplate.injection.component.ConfigPersistentComponent;
import uk.co.ribot.androidboilerplate.injection.component.DaggerConfigPersistentComponent;
import uk.co.ribot.androidboilerplate.injection.module.ActivityModule;
import uk.co.ribot.androidboilerplate.ui.main_activity.SplashActivity;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.LanguageUtils;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 */
public class BaseActivity extends AppCompatActivity implements MvpView {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent>
            sComponentsMap = new LongSparseArray<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;
    @Inject
    public LanguageUtils languageUtils;
    @Inject
    public
    UserSession mUserSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();

        ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

        if (configPersistentComponent == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(BazarlakeApplication.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
        mActivityComponent.inject(this);
        languageUtils.initLocal();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext();
//        setFont()
        PreferencesHelper sharedPreferencesHelper = new PreferencesHelper(newBase);
        LanguageUtils utils = new LanguageUtils(newBase, sharedPreferencesHelper);
//        super.attachBaseContext(utils.setLocale(utils.getCurrentLang()));
        Context base = utils.setLocale(utils.getCurrentLang());
        super.attachBaseContext(base);
    }
    public void showLogoutConfirmation(final Context context) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(context)
                .title(R.string.signout)
                .content(R.string.signout_confrimation)
                .positiveText(R.string.signout)
                .negativeText(R.string.dialog_action_cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        logout();
                        reOpenApp(context);
                    }
                })
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                }).build();
        materialDialog.show();
    }

    public void reOpenApp(final Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.putExtra("finish", true); // if you are checking for this in your other Activities
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    protected void logout() {
        mUserSession.destroySession();
/*        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("finish", true); // if you are checking for this in your other Activities
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
    }
    public boolean isArabic() {
        return languageUtils.isArabic();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        if (!b) {
            ViewUtil.createSnackbar(getWindow().getDecorView().getRootView(), getResources().getString(R.string.no_connection)).show();
        }
    }


    public boolean isUserLogged() {
        return mUserSession.hasActiveSession();
    }

    @Override
    public void showMessageDialog(String s) {
        if (s != null && !s.isEmpty()) {
            MaterialDialog dialog = new MaterialDialog.Builder(this)
                    .content(s)
                    .contentGravity(GravityEnum.START)
                    .positiveText(R.string.dialog_action_ok)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .build();
            dialog.show();
        }
    }
    @Override
    public void onTimeout() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onUnknownError(String message) {

    }

    @Override
    public void showProgresBar(boolean b) {
        //DialogFactory.createNormailProgressBar(this, b);
    }

    @Override
    public void showProgresBar(boolean b, ProgressView progressView) {
        if (b) {
            progressView.setVisibility(View.VISIBLE);
        } else
            progressView.setVisibility(View.GONE);
    }

    @Override
    public void showSnackBar(String message) {
        ViewUtil.createSnackbar(getWindow().getDecorView().getRootView(),message).show();

    }

    @Override
    public void showEmpty() {

    }
}
