package uk.co.ribot.androidboilerplate.ui.bag.address;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.AddressBody;
import uk.co.ribot.androidboilerplate.data.model.RestResponse;
import uk.co.ribot.androidboilerplate.ui.bag.billing.BillingAndShippingFragment;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;

import static uk.co.ribot.androidboilerplate.util.ViewUtil.setError;

public class AddressFragment extends BaseFragment implements AddressMvpView {

    @Inject
    AddressPresenter addressPresenter;
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
    @BindView(R.id.btn_save)
    Button btnSave;
    AddressBody addressBody;

    String name = "", mobile = "", country = "", city = "", state = "", address1 = "", address2 = "", postCode = "";

    public AddressFragment() {
        // Required empty public constructor
    }

    public static AddressFragment newInstance() {
        AddressFragment fragment = new AddressFragment();
        return fragment;
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
        final View view = inflater.inflate(R.layout.fragment_address, container, false);
        ButterKnife.bind(this, view);
        addressPresenter.attachView(this);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        inputName.addTextChangedListener(mTextWatcher);
        inputMobile.addTextChangedListener(mTextWatcher);
        inputCountry.addTextChangedListener(mTextWatcher);
        inputCity.addTextChangedListener(mTextWatcher);
        inputState.addTextChangedListener(mTextWatcher);
        inputAddress1.addTextChangedListener(mTextWatcher);
        inputAddress2.addTextChangedListener(mTextWatcher);
        inputPostCode.addTextChangedListener(mTextWatcher);
        return view;
    }


    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
        }
    };

    private void checkFieldsForEmptyValues() {

        name = inputName.getText().toString();
        mobile = inputMobile.getText().toString();
        country = inputCountry.getText().toString();
        city = inputCity.getText().toString();
        state = inputState.getText().toString();
        address1 = inputAddress1.getText().toString();
        address2 = inputAddress2.getText().toString();
        postCode = inputPostCode.getText().toString();

        if (!name.isEmpty() && !mobile.isEmpty() && !country.isEmpty() && !city.isEmpty()
                && !state.isEmpty() && !address1.isEmpty() && !address2.isEmpty() && !postCode.isEmpty()) {
            //   btnSave.setEnabled(true);
            btnSave.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            btnSave.setTextColor(getContext().getResources().getColor(R.color.white));
//            addressBody = new AddressBody("hjkj", "09876", "palestine", "gaza", "gaza", "jabalia"
//                    , "faooja", "00970");
            addressBody = new AddressBody(name, mobile, country, city, state, address1, address2, postCode);
            addressPresenter.saveAddressData(addressBody);

        } else {
            showSnackBar(getResources().getString(R.string.empty_inputs));
//            btnSave.setEnabled(false);
//            btnSave.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));

        }

        setError(inputAddress1);
        setError(inputAddress2);
        setError(inputCity);
        setError(inputCountry);
        setError(inputMobile);
        setError(inputName);
        setError(inputPostCode);
        setError(inputState);

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
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(BillingAndShippingFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        addressPresenter.detachView();

    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        checkFieldsForEmptyValues();

    }

    @Override
    public void isSuccess(RestResponse restResponse) {
//        if (restResponse.getStatus())
//            onBackPressed();
//        else
        showSnackBar(restResponse.getMessage());
    }
}
