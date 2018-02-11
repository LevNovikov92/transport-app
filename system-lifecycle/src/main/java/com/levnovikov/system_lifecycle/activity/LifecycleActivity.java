package com.levnovikov.system_lifecycle.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author: lev.novikov
 * Date: 7/1/18.
 */

public class LifecycleActivity extends AppCompatActivity implements ALifecycle {

    private final BehaviorSubject<ALifecycleEvent> lifecycleStream = BehaviorSubject.create();
    private final Map<ALifecycleEvent, CompositeDisposable> disposableMap = new HashMap<>();

    @Override
    public void subscribeUntil(ALifecycleEvent event, Disposable disposable) {
        final CompositeDisposable compositeDisposable = disposableMap.get(event);
        if (compositeDisposable != null) {
            disposableMap.put(event, new CompositeDisposable());
        }

        disposableMap.get(event).add(disposable);
    }

    @Override
    public void subscribeUntilDestroy(Disposable disposable) {
        final CompositeDisposable compositeDisposable = disposableMap.get(ALifecycleEvent.DESTROY);
        if (compositeDisposable == null) {
            disposableMap.put(ALifecycleEvent.DESTROY, new CompositeDisposable());
        }

        disposableMap.get(ALifecycleEvent.DESTROY).add(disposable);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ALifecycleEvent event = ALifecycleEvent.CREATE;
        event.setBundle(savedInstanceState);
        lifecycleStream.onNext(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleStream.onNext(ALifecycleEvent.START);
        dispose(ALifecycleEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleStream.onNext(ALifecycleEvent.RESUME);
        dispose(ALifecycleEvent.RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleStream.onNext(ALifecycleEvent.PAUSE);
        dispose(ALifecycleEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleStream.onNext(ALifecycleEvent.STOP);
        dispose(ALifecycleEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        lifecycleStream.onNext(ALifecycleEvent.DESTROY);
        dispose(ALifecycleEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public Observable<ALifecycleEvent> getStream() {
        return lifecycleStream;
    }

    private void dispose(ALifecycleEvent event) {
        final CompositeDisposable disposable = disposableMap.get(event);
        if (disposable != null) {
            disposable.dispose();
            disposableMap.remove(event);
        }
    }
}
