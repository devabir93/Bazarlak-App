package uk.co.ribot.androidboilerplate.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";

    private final SharedPreferences mPref;
    private final String currentUserKey = "userSession";
    @NonNull
    Gson gson = new GsonBuilder().create();
    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }
    public void addUserSession(UserData user) {
        String jsonUser = gson.toJson(user);
        mPref.edit().putString(currentUserKey, jsonUser).apply();
    }

    public UserData getCurrentUser() {
        String jsonUser = mPref.getString(currentUserKey, null);
        return gson.fromJson(jsonUser, UserData.class);
    }

    public void removeUserSession() {
        mPref.edit().putString(currentUserKey, "").apply();
    }
}
