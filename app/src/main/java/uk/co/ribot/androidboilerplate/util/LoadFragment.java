package uk.co.ribot.androidboilerplate.util;

import android.support.v4.app.Fragment;

public class LoadFragment {

    Fragment fragment;

    public LoadFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
