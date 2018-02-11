package com.levnovikov.system_lifecycle.view;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.FrameLayout;

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

public class LifecycleFrameLayout extends FrameLayout implements VLifecycle {

    public LifecycleFrameLayout(@NonNull Context context) {
        super(context);
    }

    public LifecycleFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LifecycleFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final VLifecycleDelegate delegate = new VLifecycleDelegate();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        delegate.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        delegate.onDetachedFromWindow();
    }


    @Override
    public void subscribeUntil(VLifecycleEvent event, Disposable disposable) {
        delegate.subscribeUntil(event, disposable);
    }

    @Override
    public void subscribeUntilDetach(Disposable disposable) {
        delegate.subscribeUntilDetach(disposable);
    }

    @Override
    public Observable<VLifecycleEvent> getStream() {
        return delegate.getStream();
    }
}
