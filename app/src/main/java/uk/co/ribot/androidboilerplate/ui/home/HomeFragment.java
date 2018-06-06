package uk.co.ribot.androidboilerplate.ui.home;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Mainvideo;
import uk.co.ribot.androidboilerplate.data.model.Offerproduct;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;
import uk.co.ribot.androidboilerplate.util.RecyclerItemClickListener;

public class HomeFragment extends BaseFragment implements HomeMvpView {
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.photosGrid)
    RecyclerView photosGrid;
    @Inject HomeGridViewAdapter homeGridViewAdapter;
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
                        showProductDetails(mOfferproducts.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        return view;
    }

    private void showProductDetails(Offerproduct offerproduct) {
    }

    @Override
    public void showVideo(Mainvideo mainvideo) {
        Timber.d("mainvideo %s", mainvideo.getVideo());
/*        if (mainvideo.getVideo() != null && !mainvideo.getVideo().isEmpty()) {

            // Uri vidUri = Uri.parse(mainvideo.getVideo());
            //vidView.setVideoURI(vidUri);

            MediaController mc = new MediaController(getContext());
//            mc.setAnchorView(videoView);
           // mc.setMediaPlayer(videoView);
            Uri video = Uri.parse(mainvideo.getVideo());
            videoView.setMediaController(mc);
            //videoView.setVideoURI(video);
            videoView.setVideoPath("http://bazarlak.com/uploads/videos/152556060143.MP4");
            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    videoView.start();
                }
            });
            //videoView.start();
        }*/

    }

    @Override
    public void showOffers(List<Offerproduct> offerproducts) {
    
        mOfferproducts= offerproducts;
        homeGridViewAdapter.setOfferProductData(getContext(),offerproducts);
        homeGridViewAdapter.notifyDataSetChanged();
        
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
