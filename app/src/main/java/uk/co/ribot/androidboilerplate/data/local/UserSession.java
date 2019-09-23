package uk.co.ribot.androidboilerplate.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.UserData;

/**
 * Created by HKassab on 12/05/2016.
 */
@Singleton
public class UserSession {

    private final DataManager mDataManager;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public UserSession(DataManager mDataManager, PreferencesHelper mPreferencesHelper) {
        this.mDataManager = mDataManager;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    public UserData getCurrentUser() {
        return mPreferencesHelper.getCurrentUser();
    }

    public boolean hasActiveSession() {
        return mPreferencesHelper.getCurrentUser() != null;
    }

    public void createSession(UserData user) {
        mPreferencesHelper.addUserSession(user);
    }

    public void destroySession() {
        mPreferencesHelper.removeUserSession();
    }
}
