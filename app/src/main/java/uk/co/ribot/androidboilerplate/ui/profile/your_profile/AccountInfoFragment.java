package uk.co.ribot.androidboilerplate.ui.profile.your_profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.activity_name_textView_secondary)
    TextView activityNameTextViewSecondary;
    @BindView(R.id.second_toolbar)
    Toolbar toolbar;
    @BindView(R.id.fname_editText)
    EditText fnameEditText;
    @BindView(R.id.lname_editText)
    EditText lnameEditText;
    @BindView(R.id.gender_spinner)
    MaterialSpinner genderSpinner;
    @BindView(R.id.country_spinner)
    MaterialSpinner countrySpinner;
    @BindView(R.id.btn_save)
    Button btnSave;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ProgressBar progressBar;

    @Inject
    ProfilePresenter profilePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.fragment_account_info);
        ButterKnife.bind(this);
        profilePresenter.attachView(this);
        profilePresenter.setContext(AccountInfoFragment.this);


        TextView textView = toolbar.findViewById(R.id.activity_name_textView_secondary);
        textView.setText(getString(R.string.account_info_label));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        if (b) {
            UserData userData = new UserData();
            //userData.setGender(genderSpinner.getSe);
            profilePresenter.updateProfileInfo(userData);
        }

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
