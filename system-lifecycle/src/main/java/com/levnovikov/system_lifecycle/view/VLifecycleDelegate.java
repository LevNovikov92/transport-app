package com.levnovikov.system_lifecycle.view;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

class VLifecycleDelegate {

    private final BehaviorSubject<VLifecycleEvent> lifecycleStream = BehaviorSubject.create();
    private final Map<VLifecycleEvent, CompositeDisposable> disposableMap = new HashMap<>();

    void onAttachedToWindow() {
        lifecycleStream.onNext(VLifecycleEvent.ATTACH);
    }

    void onDetachedFromWindow() {
        lifecycleStream.onNext(VLifecycleEvent.DETACH);
        dispose(VLifecycleEvent.DETACH);
    }

    void subscribeUntil(VLifecycleEvent event, Disposable disposable) {
        final CompositeDisposable compositeDisposable = disposableMap.get(event);
        if (compositeDisposable != null) {
            disposableMap.put(event, new CompositeDisposable());
        }
        disposableMap.get(event).add(disposable);
    }

    void subscribeUntilDetach(Disposable disposable) {
        final CompositeDisposable compositeDisposable = disposableMap.get(VLifecycleEvent.DETACH);
        if (compositeDisposable == null) {
            disposableMap.put(VLifecycleEvent.DETACH, new CompositeDisposable());
        }

        disposableMap.get(VLifecycleEvent.DETACH).add(disposable);
    }

    Observable<VLifecycleEvent> getStream() {
        return lifecycleStream;
    }

    private void dispose(VLifecycleEvent event) {
        final CompositeDisposable disposable = disposableMap.get(event);
        if (disposable != null) {
            disposable.dispose();
            disposableMap.remove(event);
        }
    }
}
