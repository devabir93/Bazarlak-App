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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.co.ribot.androidboilerplate.R;

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

    public static void setError(EditText editText) {
        if (editText.getText().toString().isEmpty())
            editText.setError(editText.getContext().getResources().getString(R.string.empty_label));
        else
            editText.setError(null);
    }

    public static  void expand(final View v) {
        v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? WindowManager.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static  void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
    public static String convertobjToJson(Object objectList, Class<?> aClass) {
        SuperclassExclusionStrategy ex = new SuperclassExclusionStrategy();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        return gson.toJson(objectList);
    }
}
