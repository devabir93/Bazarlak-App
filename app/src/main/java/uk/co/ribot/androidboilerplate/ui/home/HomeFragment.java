package uk.co.ribot.androidboilerplate.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.GetProductByIdResponseBody;
import uk.co.ribot.androidboilerplate.data.model.Mainvideo;
import uk.co.ribot.androidboilerplate.data.model.Offerproduct;
import uk.co.ribot.androidboilerplate.data.model.Product;
import uk.co.ribot.androidboilerplate.data.model.UserData;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.filter.FilterActivity;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsDetailsFragment;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;
import uk.co.ribot.androidboilerplate.util.ViewUtil;

public class HomeFragment extends BaseFragment implements HomeMvpView, EasyVideoCallback {
    @BindView(R.id.video_view)
    EasyVideoPlayer player;
    @BindView(R.id.photosGrid)
    RecyclerView photosGrid;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @Inject
    HomeGridViewAdapter homeGridViewAdapter;
    @Inject
    HomePresenter homePresenter;
    private List<Offerproduct> mOfferproducts;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        homePresenter.attachView(this);
        homePresenter.checkConnection(getContext());
        photosGrid.setAdapter(homeGridViewAdapter);
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        photosGrid.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        photosGrid.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), photosGrid, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        homePresenter.getProductById(String.valueOf(mOfferproducts.get(position).getId()));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        assert player != null;
        player.setCallback(this);
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        //inflater.inflate(R.menu.menu_second_toolbar, menu);
//        menu.clear();
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        return super.onOptionsItemSelected(item);
//    }

    private void showProductDetails(Product offerproduct) {
        Fragment nextFrag = ProductsDetailsFragment.newInstance(offerproduct);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, nextFrag, ProductsDetailsFragment.class.getName())
                .commit();
    }

    @Override
    public void showVideo(Mainvideo mainvideo) {
        Timber.d("mainvideo %s", mainvideo.getVideo());

        if (mainvideo.getVideo() != null && !mainvideo.getVideo().isEmpty()) {
            player.setSource(Uri.parse(mainvideo.getVideo()));

        }

    }

    @Override
    public void showOffers(List<Offerproduct> offerproducts) {

        mOfferproducts = offerproducts;
        homeGridViewAdapter.setOfferProductData(getContext(), offerproducts);
        homeGridViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void showOfferProduct(GetProductByIdResponseBody productByIdResponseBody) {
        player.pause();
        showProductDetails(productByIdResponseBody.getByIdData().getProduct().get(0)
        );
    }

    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {

    }

    @Override
    public void onPaused(EasyVideoPlayer player) {

    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        Log.d("EVP-Sample", "onPreparing()");
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        Log.d("EVP-Sample", "onPrepared()");
    }

    @Override
    public void onBuffering(int percent) {
        Log.d("EVP-Sample", "onBuffering(): " + percent + "%");
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        Log.d("EVP-Sample", "onError(): " + e.getMessage());
        DialogFactory.createGenericErrorDialog(getContext(), e.getMessage());
//        new MaterialDialog.Builder(this)
//                .title(R.string.error)
//                .content(e.getMessage())
//                .positiveText(android.R.string.ok)
//                .show();
    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        Log.d("EVP-Sample", "onCompletion()");
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        Toast.makeText(getContext(), "Retry", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void hasActiveInternetConnection(boolean b) {
        super.hasActiveInternetConnection(b);
        if (!b) {
            ViewUtil.createSnackbar(photosGrid.getRootView(), getResources().getString(R.string.no_connection)).show();
        } else {
            homePresenter.getHomePage(getContext());
        }

    }

    @Override
    public void showSnackBar(String message) {
        super.showSnackBar(message);
        ViewUtil.createSnackbar(photosGrid.getRootView(), message).show();

    }

    @Override
    public void showProgresBar(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);

        } else
            progressBar.setVisibility(View.GONE);
    }
}
