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
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.profile.ProfileMvpView;

public class YourProfileFragment extends BaseFragment implements ProfileMvpView {

    @BindView(R.id.second_toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_info_layout)
    RelativeLayout editInfoLayout;
    @BindView(R.id.password_layout)
    RelativeLayout passwordLayout;
    @BindView(R.id.email_layout)
    RelativeLayout emailLayout;
    @BindView(R.id.payment_details_layout)
    RelativeLayout paymentDetailsLayout;
    @BindView(R.id.shipping_address_layout)
    RelativeLayout shippingAddressLayout;
    @BindView(R.id.signout_button)
    Button signoutButton;

    public YourProfileFragment() {
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
        View view = inflater.inflate(R.layout.your_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView textView = toolbar.findViewById(R.id.activity_name_textView_secondary);
        textView.setText(getString(R.string.your_profile));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(YourProfileFragment.class.getName());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(fragment).commit();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.signout_button, R.id.edit_info_layout, R.id.password_layout, R.id.email_layout, R.id.payment_details_layout, R.id.shipping_address_layout})
    public void onViewClicked(View view) {
        Fragment nextFrag = null;
        switch (view.getId()) {
            case R.id.signout_button:
                break;
            case R.id.edit_info_layout:
                nextFrag = new AccountInfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, AccountInfoFragment.class.getName())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.password_layout:
                nextFrag = new ChangeEmailFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, ChangePasswordFragment.class.getName())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.email_layout:
                nextFrag = new ChangeEmailFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, nextFrag, ChangeEmailFragment.class.getName())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.payment_details_layout:
                break;
            case R.id.shipping_address_layout:
                break;
        }
    }

}
