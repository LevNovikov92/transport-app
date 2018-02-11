package com.levnovikov.system_lifecycle.view;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public interface VLifecycle {

    void subscribeUntil(VLifecycleEvent event, Disposable disposable);

    void subscribeUntilDetach(Disposable disposable);

    Observable<VLifecycleEvent> getStream();
}
