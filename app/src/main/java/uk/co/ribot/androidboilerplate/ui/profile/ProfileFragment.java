package uk.co.ribot.androidboilerplate.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.CustomCategory;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.main_activity.SplashActivity;
import uk.co.ribot.androidboilerplate.ui.profile.login.LoginFragment;
import uk.co.ribot.androidboilerplate.ui.profile.register.RegisterFragment;
import uk.co.ribot.androidboilerplate.ui.profile.your_order.YourOrderActivity;
import uk.co.ribot.androidboilerplate.ui.profile.your_profile.YourProfileFragment;
import uk.co.ribot.androidboilerplate.util.Language;
import uk.co.ribot.androidboilerplate.util.Message;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class ProfileFragment extends BaseFragment implements ProfileMvpView {
    @BindView(R.id.profile_list_item)
    RecyclerView mRecyclerView;
    @Inject
    ProfilePresenter profilePresenter;
    @Inject
    ProfileAdapter profileAdapter;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.profile_wlc_textView)
    TextView profileWlcTextView;
    @BindView(R.id.profile_login_button)
    Button profileLoginButton;
    @BindView(R.id.profile_signup_button)
    Button profileSignupButton;
    @BindView(R.id.login_buttons)
    LinearLayout loginButtons;
    @BindView(R.id.profile_container)
    LinearLayout profileContainer;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.profile_login_button)
    public void login() {
        Fragment nextFrag = new LoginFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.profile_container, nextFrag, LoginFragment.class.getName())
                .addToBackStack(null)
                .commit();
    }

    @OnClick(R.id.profile_signup_button)
    public void register() {
        Fragment nextFrag = new RegisterFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.profile_container, nextFrag, RegisterFragment.class.getName())
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
        if (preferencesHelper.getCurrentUser() != null) {
            loginButtons.setVisibility(View.GONE);
            profileWlcTextView.setText(this.getString(R.string.wlc_label) + "(" + preferencesHelper.getCurrentUser().getFname() +
                    preferencesHelper.getCurrentUser().getLname() + ")");
        } else
            loginButtons.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapter(profileAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever

                        openFragment(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        profilePresenter.attachView(this);
        profileAdapter.setCategories(signedUserList());
        profileAdapter.isLogged(mUserSession.hasActiveSession());
        profileAdapter.notifyDataSetChanged();

        return view;
    }

    private void openFragment(int fragmentClass) {
        switch (fragmentClass) {
            case 0:
                startActivity(new Intent(getActivity(), YourOrderActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(), YourProfileFragment.class));

            case 2:
                changeLang();
                break;
            case 5:
                shareApp();
                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
//        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }
//
//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        menu.clear();
//        MenuItem filter = menu.findItem(R.id.ic_filter_action);
//        MenuItem bag = menu.findItem(R.id.ic_delete_action);
//        if (filter != null)
//            filter.setVisible(false); // Display clear filters
//        if (bag != null)
//            bag.setVisible(false); // Display clear filters
//        MenuItem backAction = menu.findItem(android.R.id.home);
//        if (backAction != null)
//            backAction.setVisible(false); // Display clear filters
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        profilePresenter.detachView();
    }

    private List<CustomCategory> signedUserList() {
        List<CustomCategory> categories = new ArrayList<>();
        if (preferencesHelper.getCurrentUser() != null) {
            categories.add(new CustomCategory(-1, getString(R.string.your_orders)));
            categories.add(new CustomCategory(-1, getString(R.string.your_profile)));
        }
        categories.add(new CustomCategory(R.drawable.change_lang, getString(R.string.change_lang)));
        categories.add(new CustomCategory(R.drawable.help_center, getString(R.string.help_center)));
        categories.add(new CustomCategory(R.drawable.contactusicon, getString(R.string.contact_us)));
        categories.add(new CustomCategory(R.drawable.inviteicon, getString(R.string.invite)));
        return categories;
    }

    @Override
    public void showMessage(String s) {

    }

    @Override
    public void showMessage(boolean b, String string, Message logging) {

    }

    @Override
    public void showProgresBar(boolean b) {

    }

    @Override
    public void finishActivity(boolean b) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        String extraText = getString(R.string.i_recommend, "http://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        startActivity(Intent.createChooser(intent, getString(R.string.action_share_app)));
    }

    private void changeLang() {
        int currentSelectedLang = 1;
        if (languageUtils.getCurrentLang().equals(Language.ARABIC.toString())) {
            currentSelectedLang = 0;
        } else if (languageUtils.getCurrentLang().equals(Language.ENGLISH.toString())) {
            currentSelectedLang = 1;
        }
        MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                .title(R.string.change_lang)
                .items(getString(R.string.arabic), getString(R.string.english))
                .itemsCallbackSingleChoice(currentSelectedLang, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        String newLang = which == 1 ? Language.ENGLISH.toString() : Language.ARABIC.toString();
                        languageUtils.setLocale(newLang);
                        reOpenApp();
                        return false;
                    }
                })
                .negativeText(R.string.dialog_action_cancel)
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                })
                .build();
        materialDialog.show();
    }

    protected void reOpenApp() {
        Intent intent = new Intent(getContext(), SplashActivity.class);
        intent.putExtra("finish", true); // if you are checking for this in your other Activities
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
