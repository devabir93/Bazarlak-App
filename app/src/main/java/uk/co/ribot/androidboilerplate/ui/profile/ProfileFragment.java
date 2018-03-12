package uk.co.ribot.androidboilerplate.ui.profile;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Category;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

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
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        profilePresenter.detachView();
    }

    private List<Category> signedUserList(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(-1,getString(R.string.your_orders)));
        categories.add(new Category(-1,getString(R.string.your_profile)));
        categories.add(new Category(-1,getString(R.string.payment_details)));
        categories.add(new Category(-1,getString(R.string.track_orders)));
        categories.add(new Category(R.drawable.change_lang,getString(R.string.change_lang)));
        categories.add(new Category(R.drawable.contactusicon,getString(R.string.contact_us)));
        categories.add(new Category(R.drawable.inviteicon,getString(R.string.invite)));

        return categories;
    }
}
