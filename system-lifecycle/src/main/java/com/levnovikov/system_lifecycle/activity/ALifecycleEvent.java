package com.levnovikov.system_lifecycle.activity;

import android.os.Bundle;

import io.reactivex.annotations.Nullable;

/**
 * Author: lev.novikov
 * Date: 2/1/18.
 */

public enum ALifecycleEvent {
    CREATE,
    START,
    RESUME,
    PAUSE,
    STOP,
    DESTROY;

    @Nullable
    Bundle bundle = null;

    @Nullable
    public Bundle getBundle() {
        return bundle;
    }

    void setBundle(@Nullable Bundle bundle) {
        this.bundle = bundle;
    }
}
