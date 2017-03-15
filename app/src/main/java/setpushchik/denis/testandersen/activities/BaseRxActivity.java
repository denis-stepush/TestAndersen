package setpushchik.denis.testandersen.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Denis Stepushchik on 13.03.17.
 */

public abstract class BaseRxActivity extends AppCompatActivity {
    private CompositeSubscription mSubscriptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    protected void onStart() {
        super.onStart();
        subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unsubscribeAll();
    }

    private void subscribe() {
        for (Subscription subscription : createSubscriptions()) {
            mSubscriptions.add(subscription);
        }
    }

    protected void unsubscribeAll() {
        if (mSubscriptions == null) {
            return;
        }
        mSubscriptions.clear();
        mSubscriptions = new CompositeSubscription();
    }

    protected void unsubscribe(Subscription subscription) {
        if (subscription == null || mSubscriptions == null) {
            return;
        }
        mSubscriptions.remove(subscription);
    }

    public Subscription addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
        return subscription;
    }

    /**
     * Subscribe to the {@link Observable}s that should be started on {@link android.support.v4.app .Fragment#onActivityCreated} and then
     * return the subscriptions that you want attached to this fragment's lifecycle. Each {@link Subscription} will have {@link
     * Subscription#unsubscribe() unsubscribe()} called when {@link android.support.v4.app.Fragment#onDetach() onDetach()} is fired.
     * <p>The default implementation returns an empty array.</p>
     */
    protected List<Subscription> createSubscriptions() {
        return new ArrayList<>(0);
    }

    // quick subscriptions

    protected <T> Subscription createAndAddSubscription(Observable<T> observable, Observer<T> observer) {
        return addSubscription(bindObservable(observable, observer));
    }

    protected <T> Subscription createAndAddSubscription(Observable<T> observable) {
        return addSubscription(bindObservable(observable));
    }

    //use only for SmoothProgressBar
    protected <T> Subscription justBindObservable(Observable<T> observable, Observer<T> observer) {
        return observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private <T> Subscription bindObservable(Observable<T> observable, Observer<T> observer) {
        return observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        doOnSubscribe();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        doOnTerminate();
                    }
                })
                .subscribe(observer);
    }

    public <T> Observable<T> bindOnNextAction(Observable<T> observable, Action1<T> onNextAction) {
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).doOnNext(onNextAction);
    }

    public <T> Subscription bindObservable(Observable<T> observable) {
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(T t) {

            }
        };
        return bindObservable(observable, observer);
    }

    public abstract void doOnSubscribe();

    public abstract void doOnTerminate();
}

