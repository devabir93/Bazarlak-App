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
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.RestEmailBody;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileMvpView;
import uk.co.ribot.androidboilerplate.ui.profile.ProfilePresenter;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.ViewUtil;


public class ChangeEmailFragment extends BaseActivity implements ProfileMvpView {

    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.second_toolbar)
    Toolbar toolbar;
    @BindView(R.id.email_editText)
    EditText emailEditText;
    @BindView(R.id.confirm_email_editText)
    EditText confirmEmailEditText;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @Inject
    ProfilePresenter profilePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.fragment_change_email);
        ButterKnife.bind(this);
        profilePresenter.attachView(this);
        profilePresenter.setContext(this);
        TextView textView = toolbar.findViewById(R.id.activity_name_textView_secondary);
        textView.setText(getString(R.string.change_email_label));
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
        if (!b) {
            ViewUtil.createSnackbar(btnSave.getRootView(), getResources().getString(R.string.no_connection));
        } else {
            RestEmailBody restEmailBody = new RestEmailBody();
            restEmailBody.setOldEmail(emailEditText.getText().toString());
            restEmailBody.setEmail(confirmEmailEditText.getText().toString());
            profilePresenter.resetEmail(restEmailBody);
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
