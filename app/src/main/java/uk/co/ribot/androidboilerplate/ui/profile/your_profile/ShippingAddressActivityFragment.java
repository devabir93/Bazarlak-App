package uk.co.ribot.androidboilerplate.ui.profile.your_profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.RestEmailBody;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileMvpView;
import uk.co.ribot.androidboilerplate.ui.profile.ProfilePresenter;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShippingAddressActivityFragment extends BaseFragment implements ProfileMvpView {

    @BindView(R.id.btn_save)
    Button btnSave;

    @Inject
    ProfilePresenter profilePresenter;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_mobile)
    EditText inputMobile;
    @BindView(R.id.input_country)
    EditText inputCountry;
    @BindView(R.id.input_city)
    EditText inputCity;
    @BindView(R.id.input_state)
    EditText inputState;
    @BindView(R.id.input_address1)
    EditText inputAddress1;
    @BindView(R.id.input_address2)
    EditText inputAddress2;
    @BindView(R.id.input_post_code)
    EditText inputPostCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);
        profilePresenter.attachView(this);
        profilePresenter.setContext(getContext());

        return view;
    }


    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        profilePresenter.checkConnection(getContext());


    }

    @Override
    public void showMessage(String s) {
        if (s != null && !s.isEmpty())
            DialogFactory.createSimpleOkDialog(getContext(), s);

    }

    @Override
    public void showMessage(boolean b, String string, Message logging) {

    }

    @Override
    public void showProgresBar(boolean b) {
        DialogFactory.createNormailProgressBar(getContext(), b);
    }

    @Override
    public void finishActivity(boolean b) {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(ShippingAddressActivityFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();
        getActivity().finish();
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        super.hasActiveInternetConnection(b);
        if (!b) {
            ViewUtil.createSnackbar(btnSave.getRootView(), getResources().getString(R.string.no_connection)).show();
        } else {
            AddressBody addressBody = new AddressBody();
            addressBody.setFullname(inputName.getText().toString());
            addressBody.setMobile(inputMobile.getText().toString());
            addressBody.setCountry(inputCountry.getText().toString());
            addressBody.setCity(inputCity.getText().toString());
            addressBody.setState(inputState.getText().toString());
            addressBody.setAddress1(inputAddress1.getText().toString());
            addressBody.setAddress2(inputAddress2.getText().toString());
            addressBody.setPostcode(inputPostCode.getText().toString());
            profilePresenter.updateAddress(addressBody);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
