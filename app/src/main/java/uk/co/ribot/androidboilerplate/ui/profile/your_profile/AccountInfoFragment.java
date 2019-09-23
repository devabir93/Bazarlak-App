package uk.co.ribot.androidboilerplate.ui.profile.your_profile;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.jaredrummler.materialspinner.MaterialSpinner;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileMvpView;
import uk.co.ribot.androidboilerplate.ui.profile.ProfilePresenter;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.ViewUtil;


public class AccountInfoFragment extends BaseActivity implements ProfileMvpView {

    @BindView(R.id.fname_editText)
    EditText fnameEditText;
    @BindView(R.id.lname_editText)
    EditText lnameEditText;
    @BindView(R.id.gender_spinner)
    MaterialSpinner genderSpinner;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @BindView(R.id.activity_name_textView)
    TextView activityNameTextView;
    ProgressBar progressBar;

    @Inject
    ProfilePresenter profilePresenter;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.code_picker)
    CountryCodePicker codePicker;
    @BindView(R.id.phone_edit)
    EditText phoneEdit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.fragment_account_info);
        ButterKnife.bind(this);
        profilePresenter.attachView(this);
        profilePresenter.setContext(AccountInfoFragment.this);
        genderSpinner.setItems();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        String[] some_array = getResources().getStringArray(R.array.gender);
        genderSpinner.setItems(some_array);
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                codePicker.setCountryForNameCode(ccp.getSelectedCountryNameCode());
            }
        });

        codePicker.registerCarrierNumberEditText(phoneEdit);
        codePicker.setNumberAutoFormattingEnabled(true);
        codePicker.setHintExampleNumberEnabled(true);
        toolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                int maxWidth = toolbar.getWidth();
                int titleWidth = activityNameTextView.getWidth();
                int iconWidth = maxWidth - titleWidth;

                if (iconWidth > 0) {
                    //icons (drawer, menu) are on left and right side
                    int width = maxWidth - iconWidth * 2;
                    activityNameTextView.setMinimumWidth(width);
                    activityNameTextView.getLayoutParams().width = width;
                }
            }
        }, 0);
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        profilePresenter.checkConnection(this);
    }

    @Override
    public void showMessage(String s) {
        if (s != null && !s.isEmpty())
            DialogFactory.createSimpleOkDialog(this, s);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(boolean b, String string, Message logging) {

    }

    @Override
    public void showProgresBar(boolean b) {
        DialogFactory.createNormailProgressBar(this, b);
    }

    @Override
    public void finishActivity(boolean b) {
        this.finish();

    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        super.hasActiveInternetConnection(b);
        if (!b) {
            ViewUtil.createSnackbar(btnSave.getRootView(), getResources().getString(R.string.no_connection));
        } else {
            UserData userData = new UserData();
            userData.setMobile(codePicker.getFullNumber());
            userData.setFname(fnameEditText.getText().toString());
            userData.setLname(lnameEditText.getText().toString());
            userData.setGender(genderSpinner.getSelectedIndex() == 0 ? "Male" : "Female");
            userData.setCountry(codePicker.getSelectedCountryName());
            profilePresenter.updateProfileInfo(userData);
        }

    }

    @Override
    public void showSnackBar(String message) {
        super.showSnackBar(message);
        ViewUtil.createSnackbar(getWindow().getDecorView().getRootView(), message);

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onUnknownError(String message) {

    }
}
