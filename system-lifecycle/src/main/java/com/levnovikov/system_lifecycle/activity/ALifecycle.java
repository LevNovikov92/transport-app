package com.levnovikov.system_lifecycle.activity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public interface ALifecycle {

    void subscribeUntil(ALifecycleEvent event, Disposable disposable);

    void subscribeUntilDestroy(Disposable disposable);

    Observable<ALifecycleEvent> getStream();
}
