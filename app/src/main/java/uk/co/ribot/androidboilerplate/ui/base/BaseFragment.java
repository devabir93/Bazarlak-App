package uk.co.ribot.androidboilerplate.ui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.ProgressView;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.ribot.androidboilerplate.BazarlakeApplication;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.local.UserSession;
import uk.co.ribot.androidboilerplate.ui.main_activity.SplashActivity;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.LanguageUtils;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class BaseFragment extends Fragment implements MvpView {
    @Inject
    public LanguageUtils languageUtils;
    @Inject
    public PreferencesHelper preferencesHelper;
    @Inject
    public
    UserSession mUserSession;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BazarlakeApplication.get(getActivity()).getComponent().inject(this);
        languageUtils.initLocal();
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        if (!b) {
            Timber.d("no connection");
            if (getActivity() != null)
                ViewUtil.createSnackbar(getActivity().getWindow().getDecorView().getRootView(), getResources().getString(R.string.no_connection)).show();
        }
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
        if (getActivity() != null) {
            ViewUtil.createSnackbar(getActivity().getWindow().getDecorView().getRootView(), message).show();
        }

    }

    @Override
    public void showMessageDialog(String s) {
        if (s != null && !s.isEmpty()) {
            MaterialDialog dialog = new MaterialDialog.Builder(getContext())
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
    public void showEmpty() {

    }

}
