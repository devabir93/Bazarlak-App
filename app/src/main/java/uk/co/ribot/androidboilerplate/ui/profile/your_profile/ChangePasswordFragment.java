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
import uk.co.ribot.androidboilerplate.data.model.RestPasswordBody;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileMvpView;
import uk.co.ribot.androidboilerplate.ui.profile.ProfilePresenter;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.ViewUtil;


public class ChangePasswordFragment extends BaseActivity implements ProfileMvpView {

    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.second_toolbar)
    Toolbar toolbar;
    @BindView(R.id.old_password_editText)
    EditText oldPasswordEditText;
    @BindView(R.id.new_password_editText)
    EditText newPasswordEditText;
    @BindView(R.id.confirm_password_editText)
    EditText confirmPasswordEditText;
    @Inject
    ProfilePresenter profilePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.fragment_change_password);
        ButterKnife.bind(this);
        profilePresenter.attachView(this);
        profilePresenter.setContext(this);

        TextView textView = toolbar.findViewById(R.id.activity_name_textView_secondary);
        textView.setText(getString(R.string.change_password_label));
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
        }else {
            RestPasswordBody restPasswordBody = new RestPasswordBody();
            restPasswordBody.setOldPassword(oldPasswordEditText.getText().toString());
            restPasswordBody.setNewPassword(newPasswordEditText.getText().toString());
            restPasswordBody.setConfirmPassword(confirmPasswordEditText.getText().toString());
            profilePresenter.resetPassword(restPasswordBody);
        }

    }


    @Override
    public void showSnackBar(String message) {
        ViewUtil.createSnackbar(btnSave.getRootView(),message);

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
