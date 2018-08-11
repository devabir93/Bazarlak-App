package uk.co.ribot.androidboilerplate.ui.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.ribot.androidboilerplate.BazarlakeApplication;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class BaseFragment extends Fragment implements MvpView {

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BazarlakeApplication.get(getActivity()).getComponent().inject(this);
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        if (!b) {
            Timber.d("no connection");
            if (getActivity() != null)
                ViewUtil.createSnackbar(getActivity().getWindow().getDecorView().getRootView(), getResources().getString(R.string.no_connection)).show();
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
        if (getActivity() != null) {
            ProgressBar progressBar = DialogFactory.createNormailProgressBar(getActivity(), b);
            if (b) {
                progressBar.setVisibility(View.VISIBLE);
            } else
                progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSnackBar(String message) {
        if (getActivity() != null) {
            ViewUtil.createSnackbar(getActivity().getWindow().getDecorView().getRootView(), message).show();
        }

    }
}
