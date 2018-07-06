package uk.co.ribot.androidboilerplate.ui.home;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
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
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.ui.category.subcategory.products.ProductsDetailsFragment;
import uk.co.ribot.androidboilerplate.util.DialogFactory;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class HomeFragment extends BaseFragment implements HomeMvpView, EasyVideoCallback {
    @BindView(R.id.video_view)
    EasyVideoPlayer player;
    @BindView(R.id.photosGrid)
    RecyclerView photosGrid;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        homePresenter.attachView(this);
        homePresenter.getHomePage(getContext());
        photosGrid.setAdapter(homeGridViewAdapter);
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
}
