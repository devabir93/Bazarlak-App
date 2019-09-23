package uk.co.ribot.androidboilerplate.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import uk.co.ribot.androidboilerplate.R;


public final class DialogFactory {

    public static Dialog createSimpleOkErrorDialog(@NonNull Context context, String title, String message) {

        AlertDialog builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null).show();
        return builder;

    }

    public static Dialog createDialogWithOptions(@NonNull Context context, String title,
                                                 String[] items, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setItems(items, listener);
        return builder.create();
    }

    public static Dialog createSimpleOkDialog(@NonNull Context context, String message) {

        AlertDialog builder = new AlertDialog.Builder(context)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null).show();
        return builder;

    }

    public static Dialog createAlertDialog(@NonNull final Context context,
                                           String messageResource) {
        AlertDialog builder = new AlertDialog.Builder(context)
                .setMessage(messageResource)
                .setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

        return builder;
    }

    public static Dialog createDialogWithCustomAction(@NonNull Context context, String title, String message, DialogInterface.OnClickListener callBackAfterOkClicked) {
        AlertDialog builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, callBackAfterOkClicked)
                .setNegativeButton(android.R.string.no, callBackAfterOkClicked).show();
        return builder;
    }

    public static Dialog createSimpleOkErrorDialog(@NonNull Context context,
                                                   @StringRes int titleResource,
                                                   @StringRes int messageResource) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }

    public static Dialog createGenericErrorDialog(@NonNull Context context, String message) {

        AlertDialog builder = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null).show();
        return builder;
    }

    public static Dialog createGenericErrorDialog(@NonNull Context context, @StringRes int messageResource) {
        return createGenericErrorDialog(context, context.getString(messageResource));
    }

    @NonNull
    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    @NonNull
    public static ProgressDialog createProgressDialog(@NonNull Context context,
                                                      @StringRes int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }

    @NonNull
    public static ProgressBar createNormailProgressBar(@NonNull Context context, boolean showProgress) {
        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        if (showProgress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);

        }

        return progressBar;
    }

    public static Dialog createAlertDialog(@NonNull final Context context, @StringRes int titleResource,
                                           @StringRes int messageResource) {
        AlertDialog builder = new AlertDialog.Builder(context)
                .setTitle(titleResource)
                .setMessage(messageResource)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
        return builder;
    }


    public static Dialog createAlertDialog(@NonNull final Context context,
                                           @StringRes int messageResource) {
        AlertDialog builder = new AlertDialog.Builder(context)
                .setMessage(messageResource)
                .setPositiveButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

        return builder;
    }

    public static Dialog createAlertDialog(@NonNull final Context context,
                                           @StringRes int titleResource, @StringRes int messageResource, @NonNull final OnOkClickListener listener) {
        AlertDialog builder = new AlertDialog.Builder(context)
                .setTitle(titleResource)
                .setMessage(messageResource)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        listener.onOkClickListener();
                        dialog.dismiss();
                    }
                }).show();

        return builder;
    }


    public interface OnOkClickListener {
        void onOkClickListener();

        void onCancelClickListener();
    }
}
