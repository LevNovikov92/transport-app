package com.levnovikov.transport_app.main.di;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

import com.levnovikov.system_lifecycle.activity.ALifecycle;
import com.levnovikov.system_lifecycle.activity.LifecycleActivity;
import com.levnovikov.transport_app.di.AppComponent;
import com.levnovikov.transport_app.main.MainActivity;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Author: lev.novikov
 * Date: 11/2/18.
 */

@MainScope
@Component(dependencies = AppComponent.class, modules = MainComponent.MainModule.class)
public interface MainComponent {

    @Module
    class MainModule {

        @MainScope
        @Provides
        Context provideContext(LifecycleActivity activity) {
            return activity;
        }

        @MainScope
        @Provides
        ALifecycle provideActivityLifecycle(LifecycleActivity activity) {
            return activity;
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder bindContext(LifecycleActivity activity);
        Builder appComponent(AppComponent component);
        Builder container(ViewGroup container);
        MainComponent build();
    }
}
