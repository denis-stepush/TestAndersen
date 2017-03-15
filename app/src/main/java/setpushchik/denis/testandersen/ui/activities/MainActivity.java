package setpushchik.denis.testandersen.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import setpushchik.denis.testandersen.ui.ImageGridLayoutManager;
import setpushchik.denis.testandersen.R;
import setpushchik.denis.testandersen.adapters.PlaceImageAdapter;
import setpushchik.denis.testandersen.network.RedditApi;
import setpushchik.denis.testandersen.network.model.Image;
import setpushchik.denis.testandersen.network.model.PlacesResponse;
import setpushchik.denis.testandersen.utils.ItemOffsetDecoration;
import setpushchik.denis.testandersen.utils.ViewUtil;

public class MainActivity extends BaseRxActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private PlaceImageAdapter mPlaceImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initAdapterAndRecyclerView();
    }

    private void initAdapterAndRecyclerView() {
        mPlaceImageAdapter = new PlaceImageAdapter();

        mRecyclerView.setLayoutManager(new ImageGridLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(1));
        mRecyclerView.setAdapter(mPlaceImageAdapter);
    }

    @OnClick({R.id.newButton, R.id.topButton})
    public void onLoadImageButtonsClick(View button) {
        clearList();

        Observable<PlacesResponse> placeResponseObservable = null;
        switch (button.getId()) {
            case R.id.newButton:
                placeResponseObservable = RedditApi.getRedditService().getNew();
                break;
            case R.id.topButton:
                placeResponseObservable = RedditApi.getRedditService().getTop();
                break;
        }

        if (placeResponseObservable != null) {
            createAndAddSubscription(bindOnNextAction(placeResponseObservable, new Action1<PlacesResponse>() {
                @Override
                public void call(PlacesResponse placesResponse) {
                    onPlacesLoaded(placesResponse);
                }
            }));
        }
    }

    private void clearList() {
        mPlaceImageAdapter.clear();
    }

    private void onPlacesLoaded(PlacesResponse placeResponse) {
        List<Image> images = placeResponse.getImages();
        mPlaceImageAdapter.setImages(images);
    }

    @Override
    public void doOnSubscribe() {
        ViewUtil.showViews(mProgressBar);
    }

    @Override
    public void doOnTerminate() {
        ViewUtil.hideViews(mProgressBar);
    }
}
