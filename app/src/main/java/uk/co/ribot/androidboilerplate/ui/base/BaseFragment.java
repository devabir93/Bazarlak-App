package uk.co.ribot.androidboilerplate.ui.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.ribot.androidboilerplate.BazarlakeApplication;

public class BaseFragment extends Fragment implements MvpView {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BazarlakeApplication.get(getActivity()).getComponent().inject(this);
    }

}
