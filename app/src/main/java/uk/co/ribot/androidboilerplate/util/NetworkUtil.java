package uk.co.ribot.androidboilerplate.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.HttpException;


public class NetworkUtil {

    /**
     * Returns true if the Throwable is an instance of RetrofitError with an
     * http status code equals to the given one.
     */
    public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
        return throwable instanceof HttpException
                && ((HttpException) throwable).code() == statusCode;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    public static Observable<Boolean> hasActiveInternetConnection(@NonNull final Context context) {

        return Observable.create(new ObservableOnSubscribe<Boolean>() {

            @Override
            public void subscribe(ObservableEmitter<Boolean> subscriber) throws Exception {
                if (isNetworkConnected(context)) {
                    try {
                        HttpURLConnection urlc = (HttpURLConnection)
                                (new URL("http://clients3.google.com/generate_204")
                                        .openConnection());
                        urlc.setRequestProperty("User-Agent", "Android");
                        urlc.setRequestProperty("Connection", "close");
                        urlc.setConnectTimeout(30000);
                        urlc.connect();
                        subscriber.onNext(urlc.getResponseCode() == 204 &&
                                urlc.getContentLength() == 0);
                    } catch (IOException e) {
                        Log.e("net", "Error checking internet connection", e);
                        subscriber.onNext(false);
                        subscriber.onError(e);
                    }
                } else {
                    Log.d("net", "No network available!");
                    subscriber.onNext(false);
                }
                subscriber.onComplete();
            }
        });
//
    }
}