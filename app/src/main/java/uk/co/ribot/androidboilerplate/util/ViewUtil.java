package uk.co.ribot.androidboilerplate.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public final class ViewUtil {


    @Nullable
    private static ProgressDialog progressDialog = null;
    @Nullable
    private static Dialog dialog = null;
    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }
    public static ProgressDialog showMessage(Context context, boolean showProgress, String message, Message messageType) {
        if (messageType == Message.LOADING) {
            if (showProgress) {
                progressDialog = DialogFactory.createProgressDialog(context, message);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } else {
                progressDialog.dismiss();
                progressDialog.cancel();
            }
        } else if (messageType == Message.LOGGING) {
            if (showProgress) {
                progressDialog = DialogFactory.createProgressDialog(context, message);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } else {
                progressDialog.dismiss();
                progressDialog.cancel();
            }
        } else if (messageType == Message.WARNING) {
        } else if (messageType == Message.FAIL) {
            dialog = DialogFactory.createAlertDialog(context, message);
            dialog.setCanceledOnTouchOutside(false);
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog.cancel();
            }
        }
        return progressDialog;
    }
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static Snackbar createSnackbar(@NonNull View view, @NonNull String message) {
        return Snackbar.make(view, message, Snackbar.LENGTH_LONG);
    }
}
