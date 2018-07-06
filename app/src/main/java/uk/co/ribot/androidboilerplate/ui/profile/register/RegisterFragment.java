package uk.co.ribot.androidboilerplate.ui.profile.register;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.data.model.RegisterResponse;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class RegisterFragment extends BaseFragment implements RegisterMvpView {

    @Inject
    RegisterPresenter registerPresenter;
    @BindView(R.id.fname_signup_editText)
    EditText fnameSignupEditText;
    @BindView(R.id.lname_signup_editText)
    EditText lnameSignupEditText;
    @BindView(R.id.gender_signup_editText)
    EditText genderSignupEditText;
    @BindView(R.id.email_Login_editText)
    EditText emailLoginEditText;
    @BindView(R.id.password_Login_editText)
    EditText passwordLoginEditText;
    @BindView(R.id.mobile_signup_editText)
    EditText mobileEditText;
    @BindView(R.id.checkbox_register)
    CheckBox checkboxRegister;
    @BindView(R.id.register_button)
    Button registerButton;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
        setHasOptionsMenu(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.signup_profile, container, false);
        ButterKnife.bind(this, view);
        registerPresenter.attachView(this);
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
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(RegisterFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem backAction = menu.findItem(android.R.id.home);
        if (backAction != null)
            backAction.setVisible(false); // Display clear filters
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        registerPresenter.detachView();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void isSuccess(RegisterResponse loginResponse) {
        if (loginResponse.getStatus()) {
            ViewUtil.createSnackbar(registerButton.getRootView(), getResources().getString(R.string.signup_success_message)).show();
            onBackPressed();
        } else {
            showMessage(true, loginResponse.getMessage(), Message.FAIL);
        }
    }

    @Override
    public void showMessage(boolean showProgress, String Message, Message messageType) {
        ViewUtil.showMessage(getActivity(), showProgress, Message, messageType);
    }

    @Override
    public void showError() {
        showMessage(true, getResources().getString(R.string.error_logging_message), Message.FAIL);
    }

    @OnClick(R.id.register_button)
    public void onViewClicked() {
        UserData userData = new UserData();
        userData.setFname(fnameSignupEditText.getText().toString());
        userData.setLname(lnameSignupEditText.getText().toString());
        userData.setEmail(emailLoginEditText.getText().toString());
        userData.setMobile(mobileEditText.getText().toString());
        userData.setGender(genderSignupEditText.getText().toString());
        userData.setPassword(passwordLoginEditText.getText().toString());

//        userData.setName("Dev abir");
//        userData.setEmail("devabir9@gmail.com");
//        userData.setMobile("0599447279");
//        userData.setGender("female");
//        userData.setPassword("123456");
        registerPresenter.register(getContext(), userData);
        /// /registerPresenter.checkConnection(getContext());
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        if (b) {
            UserData userData = new UserData();
            userData.setFname(fnameSignupEditText.getText().toString());
            userData.setLname(lnameSignupEditText.getText().toString());
            userData.setEmail(emailLoginEditText.getText().toString());
            userData.setMobile(mobileEditText.getText().toString());
            userData.setGender(genderSignupEditText.getText().toString());
            userData.setPassword(passwordLoginEditText.getText().toString());
//
//            userData.setName("Dev abir");
//            userData.setEmail("devabir9@gmail.com");
//            userData.setMobile("0599447279");
//            userData.setGender("female");
//            userData.setPassword("123456");
            registerPresenter.register(getContext(), userData);
        } else
            ViewUtil.createSnackbar(registerButton.getRootView(), getResources().getString(R.string.no_connection)).show();
    }

    @Override
    public void onTimeout() {
        ViewUtil.createSnackbar(registerButton.getRootView(), getResources().getString(R.string.no_connection)).show();

    }

    @Override
    public void onNetworkError() {
        ViewUtil.createSnackbar(registerButton.getRootView(), getResources().getString(R.string.no_connection)).show();

    }

    @Override
    public void onUnknownError(String message) {
        ViewUtil.createSnackbar(registerButton.getRootView(), message).show();

    }
}
