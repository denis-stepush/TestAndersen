package setpushchik.denis.testandersen.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.OnClick;
import setpushchik.denis.testandersen.R;
import setpushchik.denis.testandersen.utils.ViewUtil;

public class MainActivity extends BaseRxActivity {

    @BindView(R.id.recyclerView)
    private RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @OnClick(R.id.newButton)
    public void onNewButtonClick() {

    }

    @OnClick(R.id.topButton)
    public void onTopButtonClick() {

    }

    @Override
    public void doOnSubscribe() {
        ViewUtil.showViews(mProgressBar);
    }

    @Override
    public void doOnError(Throwable throwable) {
        ViewUtil.hideViews(mProgressBar);
        throwable.printStackTrace();
    }

    @Override
    public void doOnCompleted() {
        ViewUtil.hideViews(mProgressBar);
    }
}
