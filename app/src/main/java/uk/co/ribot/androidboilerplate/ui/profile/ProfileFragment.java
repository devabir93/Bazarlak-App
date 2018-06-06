package uk.co.ribot.androidboilerplate.ui.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.data.model.CustomCategory;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.profile.login.LoginFragment;
import uk.co.ribot.androidboilerplate.ui.profile.register.RegisterFragment;

public class ProfileFragment extends BaseFragment implements ProfileMvpView {
    @BindView(R.id.profile_list_item)
    RecyclerView mRecyclerView;
    @Inject
    ProfilePresenter profilePresenter;
    @Inject
    ProfileAdapter profileAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.profile_login_button)
    public void login() {
        Fragment nextFrag = new LoginFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, nextFrag, LoginFragment.class.getName())
                .commit();
    }

    @OnClick(R.id.profile_signup_button)
    public void register() {
        Fragment nextFrag = new RegisterFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, nextFrag, RegisterFragment.class.getName())
                .addToBackStack(null)
                .commit();
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
        View view = inflater.inflate(R.layout.not_registered_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setAdapter(profileAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        profilePresenter.attachView(this);
        profileAdapter.setCategories(signedUserList());
        profileAdapter.notifyDataSetChanged();
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
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

        profilePresenter.detachView();
    }

    private List<CustomCategory> signedUserList() {
        List<CustomCategory> categories = new ArrayList<>();
        categories.add(new CustomCategory(-1, getString(R.string.your_orders)));
        categories.add(new CustomCategory(-1, getString(R.string.your_profile)));
        categories.add(new CustomCategory(-1, getString(R.string.payment_details)));
        categories.add(new CustomCategory(-1, getString(R.string.track_orders)));
        categories.add(new CustomCategory(R.drawable.change_lang, getString(R.string.change_lang)));
        categories.add(new CustomCategory(R.drawable.contactusicon, getString(R.string.contact_us)));
        categories.add(new CustomCategory(R.drawable.inviteicon, getString(R.string.invite)));

        return categories;
    }
}
