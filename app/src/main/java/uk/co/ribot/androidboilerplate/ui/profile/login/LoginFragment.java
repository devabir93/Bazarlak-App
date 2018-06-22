package uk.co.ribot.androidboilerplate.ui.profile.login;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.data.model.LoginResponse;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class LoginFragment extends BaseFragment implements LoginMvpView {

    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.email_Login_editText)
    EditText emailLoginEditText;
    @BindView(R.id.password_Login_editText)
    EditText passwordLoginEditText;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.forgot_password_button)
    Button forgotPasswordButton;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_profile, container, false);
        ButterKnife.bind(this, view);
        loginPresenter.attachView(this);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onBackPressed() {
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(LoginFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();

    }

    @OnClick(R.id.login_button)
    public void onLoginClick() {
        //loginPresenter.checkConnection(getContext());
        UserData userData = new UserData();
//        userData.setName(fnameSignupEditText.getText().toString() + "" + lnameSignupEditText.getText().toString());
        userData.setEmail(emailLoginEditText.getText().toString());
//        userData.setMobile(mobileEditText.getText().toString());
//        userData.setGender(genderSignupEditText.getText().toString());
        userData.setPassword(passwordLoginEditText.getText().toString());

//        userData.setEmail("devabir9@gmail.com");
//        userData.setPassword("123456");
        loginPresenter.login(getContext(), userData);

    }

    @OnClick(R.id.forgot_password_button)
    public void onForgetPasswordClick() {
        showforgetPasswordDialog();
    }

    void showforgetPasswordDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.full_screen_dialog));
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View content = factory.inflate(R.layout.forget_password_dialog_layout, null);
        Button sendButton = (Button) content.findViewById(R.id.send_button);
        ImageView closeButton = (ImageView) content.findViewById(R.id.close_imageView);
        EditText emailEditText = content.findViewById(R.id.email_forget_password_EditText);
        alertDialog.setView(content);
        final AlertDialog alertDialogObject = alertDialog.create();
        // Here you can change the layout direction via setLayoutDirection()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            alertDialogObject.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        }
        int width, height;
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        WindowManager manager = (WindowManager) getActivity().getSystemService(Activity.WINDOW_SERVICE);

        Point point = new Point();
        manager.getDefaultDisplay().getSize(point);
        width = point.x;
        height = point.y;
        lp.copyFrom(alertDialogObject.getWindow().getAttributes());
        lp.width = width;
        lp.height = height;
        alertDialogObject.getWindow().setAttributes(lp);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogObject.dismiss();
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogObject.dismiss();

            }
        });
        alertDialogObject.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void isSuccess(LoginResponse loginResponse) {
        Timber.d("status %s", loginResponse.getStatus());
        if (loginResponse.getStatus()) {

            ViewUtil.createSnackbar(loginButton.getRootView(), getResources().getString(R.string.login_success_message)).show();

        } else {
            showMessage(true, loginResponse.getMessage(), Message.FAIL);
        }
    }

    @Override
    public void showMessage(boolean showProgress, String Message, Message messageType) {
        ViewUtil.showMessage(getActivity(), showProgress, Message, messageType).setCancelable(false);
    }

    @Override
    public void showError() {
        showMessage(true, getResources().getString(R.string.error_logging_message), Message.FAIL);
    }

    @Override
    public void onTimeout() {
        ViewUtil.createSnackbar(loginButton.getRootView(), getResources().getString(R.string.no_connection)).show();

    }

    @Override
    public void onNetworkError() {
        ViewUtil.createSnackbar(loginButton.getRootView(), getResources().getString(R.string.no_connection)).show();

    }

    @Override
    public void onUnknownError(String message) {
        ViewUtil.createSnackbar(loginButton.getRootView(), message).show();

    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        super.hasActiveInternetConnection(b);
        if (!b) {
            ViewUtil.createSnackbar(loginButton.getRootView(), getResources().getString(R.string.no_connection)).show();
        } else {

            UserData userData = new UserData();
//        userData.setName(fnameSignupEditText.getText().toString() + "" + lnameSignupEditText.getText().toString());
//        userData.setEmail(emailLoginEditText.getText().toString());
//        userData.setMobile(mobileEditText.getText().toString());
//        userData.setGender(genderSignupEditText.getText().toString());
//        userData.setPassword(passwordLoginEditText.getText().toString());

            userData.setEmail("devabir9@gmail.com");
            userData.setPassword("123456");
            loginPresenter.login(getContext(), userData);
        }
    }
}
