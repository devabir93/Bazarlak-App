package uk.co.ribot.androidboilerplate.ui.profile.your_profile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
public class PaymentDetailsActivityFragment extends BaseFragment implements ProfileMvpView {


    @Inject
    ProfilePresenter profilePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_details, container, false);
        ButterKnife.bind(this, view);
        profilePresenter.attachView(this);
        profilePresenter.setContext(getContext());
//        TextView textView = toolbar.findViewById(R.id.activity_name_textView_secondary);
//        textView.setText(getString(R.string.change_email_label));
//        toolbar.setNavigationIcon(R.drawable.ic_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(PaymentDetailsActivityFragment.class.getName());
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.remove(fragment).commit();
//            }
//        });
        return view;
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
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(PaymentDetailsActivityFragment.class.getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment).commit();
        getActivity().finish();
    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        if (!b) {
            ViewUtil.createSnackbar(getView(), getResources().getString(R.string.no_connection));
        }
    }

    @Override
    public void showSnackBar(String message) {
        ViewUtil.createSnackbar(getView(),message);

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
